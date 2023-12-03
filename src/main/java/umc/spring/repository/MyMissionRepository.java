package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.*;
import umc.spring.domain.enums.Check;
import umc.spring.domain.enums.MissionStatus;

import java.util.Optional;

public interface MyMissionRepository extends JpaRepository<MyMission, Long> {
    Page<MyMission> findByUserIdAndStatus(User userId, MissionStatus missionStatus, PageRequest page);

    Optional<MyMission> findByUserIdAndMissionIdAndIsCompleteAndStatus(User userId, Mission missionId, Check isComplete, MissionStatus missionStatus);
}
