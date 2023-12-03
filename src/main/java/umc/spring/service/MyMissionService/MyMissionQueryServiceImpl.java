package umc.spring.service.MyMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.domain.MyMission;
import umc.spring.domain.User;
import umc.spring.repository.MyMissionRepository;
import umc.spring.repository.UserRepository;

import static umc.spring.domain.enums.MissionStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class MyMissionQueryServiceImpl implements MyMissionQueryService {
    private final UserRepository userRepository;
    private final MyMissionRepository myMissionRepository;
    @Override
    public Page<MyMission> getProceedingList(Long userId, Integer page) {
        User user = userRepository.findByUserIdAndStatus(userId, ACTIVE)
                .orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<MyMission> proceedingList = myMissionRepository.findByUserIdAndStatus(user, ACTIVE, PageRequest.of(page, 10));
        return proceedingList;
    }
}
