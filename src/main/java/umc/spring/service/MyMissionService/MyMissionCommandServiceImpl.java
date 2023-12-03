package umc.spring.service.MyMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.apiPayload.exception.handler.MyMissionHandler;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.converter.MyMissionConverter;
import umc.spring.domain.*;
import umc.spring.domain.enums.Check;
import umc.spring.repository.*;
import umc.spring.web.dto.MyMissionRequestDTO;

import static umc.spring.domain.enums.MissionStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class MyMissionCommandServiceImpl implements MyMissionCommandService {

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final MyMissionRepository myMissionRepository;

    @Override
    public MyMission addMyMission(MyMissionRequestDTO.MyMissionDto request) {
        User user = userRepository.findByUserIdAndStatus(request.getUserId(), ACTIVE)
                .orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findByMissionIdAndStatus(request.getMissionId(), ACTIVE)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MyMission newMyMission = MyMissionConverter.toMyMission(user, mission, request.getCheckNum());

        return myMissionRepository.save(newMyMission);
    }

    @Override
    public MyMission patchMissionComplete(Long userId, Long missionId) {
        User user = userRepository.findByUserIdAndStatus(userId, ACTIVE)
                .orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findByMissionIdAndStatus(missionId, ACTIVE)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MyMission myMission = myMissionRepository.findByUserIdAndMissionIdAndIsCompleteAndStatus(user, mission, Check.NO, ACTIVE)
                .orElseThrow(() -> new MyMissionHandler(ErrorStatus.MY_MISSION_NOT_FOUND));

        myMission.updateIsComplete(Check.YES);
        myMissionRepository.save(myMission);
        return myMission;
    }
}
