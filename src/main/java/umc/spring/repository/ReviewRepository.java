package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.*;
import umc.spring.domain.enums.MissionStatus;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReviewIdAndStatus(Long reviewId, MissionStatus missionStatus);

    Page<Review> findAllByRestaurantId(Restaurant restaurantId, PageRequest pageRequest);

    Page<Review> findAllByUserIdAndStatus(User userId, MissionStatus missionStatus, PageRequest pageRequest);
}