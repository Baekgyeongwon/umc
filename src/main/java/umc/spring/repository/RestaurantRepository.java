package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Restaurant;
import umc.spring.domain.enums.MissionStatus;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByRestaurantIdAndStatus(Long restaurantId, MissionStatus missionStatus);
}
