package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Region;
import umc.spring.domain.enums.MissionStatus;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByName(String name);

    Optional<Region> findByRegionIdAndStatus(Long regionId, MissionStatus missionStatus);
}
