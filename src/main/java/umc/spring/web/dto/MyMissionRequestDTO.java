package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistUser;

import javax.validation.constraints.Size;

public class MyMissionRequestDTO {

    @Getter
    public static class MyMissionDto{
        @ExistMission
        Long missionId;
        @ExistUser
        Long userId;
        @Size(min = 3, max = 50)
        String checkNum;
    }


}
