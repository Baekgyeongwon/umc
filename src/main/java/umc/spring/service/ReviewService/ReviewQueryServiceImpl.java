package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RestaurantHandler;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.domain.Restaurant;
import umc.spring.domain.Review;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.User;
import umc.spring.repository.RestaurantRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.UserRepository;

import java.util.Optional;

import static umc.spring.domain.enums.MissionStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    @Override
    public Optional<Restaurant> findRestaurant(Long id) {
        return restaurantRepository.findByRestaurantIdAndStatus(id, MissionStatus.ACTIVE);
    }

    @Override
    public Page<Review> getReviewList(Long restaurantId, Integer page) {
        Restaurant restaurant = restaurantRepository.findByRestaurantIdAndStatus(restaurantId, ACTIVE)
                .orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        Page<Review> restaurantPage = reviewRepository.findAllByRestaurantId(restaurant, PageRequest.of(page, 10));
        return restaurantPage;
    }

    @Override
    public Page<Review> getMyReviewList(Long userId, Integer page) {
        User user = userRepository.findByUserIdAndStatus(userId, ACTIVE)
                .orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<Review> restaurantPage = reviewRepository.findAllByUserIdAndStatus(user, ACTIVE, PageRequest.of(page, 10));
        return restaurantPage;
    }
}
