package umc.spring.service.MyMissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.MyMission;

public interface MyMissionQueryService {
    Page<MyMission> getProceedingList(Long userId, Integer page);
}
