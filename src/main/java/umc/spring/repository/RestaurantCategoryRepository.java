package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.RestaurantCategory;
import umc.spring.domain.enums.MissionStatus;

import java.util.Optional;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {
    Optional<RestaurantCategory> findByRestaurantCategoryIdAndStatus(Long restaurantCategoryId, MissionStatus missionStatus);
}
