package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MyMissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.UserConverter;
import umc.spring.domain.MyMission;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.service.MyMissionService.MyMissionCommandService;
import umc.spring.service.MyMissionService.MyMissionQueryService;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-missions")
public class MyMissionRestController {

    private final MyMissionCommandService myMissionCommandService;
    private final MyMissionQueryService myMissionQueryService;

    @PostMapping("/challenge")
    public ApiResponse<MyMissionResponseDTO.MyMissionResultDTO> join(@RequestBody @Valid MyMissionRequestDTO.MyMissionDto request){
        MyMission myMission = myMissionCommandService.addMyMission(request);
        return ApiResponse.onSuccess(MyMissionConverter.toMyMissionResultDTO(myMission));
    }

    @GetMapping("/{userId}/proceed")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API",description = "내가 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다. Query String 입니다."),
    })
    public ApiResponse<MyMissionResponseDTO.MyMissionPreViewListDTO> getProceedingList(@ExistUser @PathVariable(name = "userId") Long restaurantId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page){
        Page<MyMission> proceedingList = myMissionQueryService.getProceedingList(restaurantId,page);
        return ApiResponse.onSuccess(MyMissionConverter.toMyMissionPreViewListDTO(proceedingList));
    }

    @PatchMapping("/{userId}/{missionId}/success")
    @Operation(summary = "진행중인 미션 진행 완료로 바꾸기 API",description = "진행중인 미션을 진행 완료로 바꾸는 API입니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디, path variable 입니다!"),
            @Parameter(name = "missionId", description = "미션 아이디, path variable 입니다!"),
    })
    public ApiResponse<MyMissionResponseDTO.MyMissionResultDTO> patchMissionComplete(@ExistUser @PathVariable(name = "userId") Long userId,
                                                                                       @ExistMission @PathVariable(name = "missionId") Long missionId){
        MyMission myMission = myMissionCommandService.patchMissionComplete(userId,missionId);
        return ApiResponse.onSuccess(MyMissionConverter.toMyMissionResultDTO(myMission));
    }
}
