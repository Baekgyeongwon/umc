package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import umc.spring.domain.*;
import umc.spring.web.dto.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class MyMissionConverter {

    public static MyMissionResponseDTO.MyMissionResultDTO toMyMissionResultDTO(MyMission myMission){
        return MyMissionResponseDTO.MyMissionResultDTO.builder()
                .myMissionId(myMission.getMyMissionId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MyMission toMyMission(User user, Mission mission, String checkNum){

        return MyMission.builder()
                .missionId(mission)
                .userId(user)
                .checkNumber(checkNum)
                .build();
    }

    public static MyMissionResponseDTO.MyMissionPreViewDTO myMissionPreViewDTO(MyMission myMission){
        return MyMissionResponseDTO.MyMissionPreViewDTO.builder()
                .point(myMission.getMissionId().getPoint())
                .restaurantName(myMission.getMissionId().getRestaurantId().getName())
                .content(myMission.getMissionId().getContent())
                .isCompete(myMission.getIsComplete())
                .deadline(myMission.getMissionId().getDeadline())
                .build();
    }
    public static MyMissionResponseDTO.MyMissionPreViewListDTO toMyMissionPreViewListDTO(Page<MyMission> myMissionList){

        List<MyMissionResponseDTO.MyMissionPreViewDTO> myMissionPreViewDTOList = myMissionList.stream()
                .map(MyMissionConverter::myMissionPreViewDTO).collect(Collectors.toList());

        return MyMissionResponseDTO.MyMissionPreViewListDTO.builder()
                .isLast(myMissionList.isLast())
                .isFirst(myMissionList.isFirst())
                .totalPage(myMissionList.getTotalPages())
                .totalElements(myMissionList.getTotalElements())
                .listSize(myMissionPreViewDTOList.size())
                .reviewList(myMissionPreViewDTOList)
                .build();
    }
}
