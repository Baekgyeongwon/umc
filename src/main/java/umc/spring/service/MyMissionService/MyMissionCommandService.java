package umc.spring.service.MyMissionService;

import umc.spring.domain.MyMission;
import umc.spring.domain.User;
import umc.spring.web.dto.MyMissionRequestDTO;
import umc.spring.web.dto.UserRequestDTO;

public interface MyMissionCommandService {
    MyMission addMyMission(MyMissionRequestDTO.MyMissionDto request);

    MyMission patchMissionComplete(Long userId, Long missionId);
}
