package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MyMissionConverter;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.MyMission;
import umc.spring.domain.Restaurant;
import umc.spring.service.MyMissionService.MyMissionCommandService;
import umc.spring.service.RestaurantService.RestaurantCommandService;
import umc.spring.web.dto.MyMissionRequestDTO;
import umc.spring.web.dto.MyMissionResponseDTO;
import umc.spring.web.dto.RestaurantRequestDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private final RestaurantCommandService restaurantCommandService;

    @PostMapping("/")
    public ApiResponse<RestaurantResponseDTO.RestaurantResultDTO> join(@RequestBody @Valid RestaurantRequestDTO.RestaurantDto request){
        Restaurant restaurant = restaurantCommandService.addRestaurant(request);
        return ApiResponse.onSuccess(RestaurantConverter.toRestaurantResultDTO(restaurant));
    }
}
