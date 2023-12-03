package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RestaurantHandler;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.*;
import umc.spring.repository.*;
import umc.spring.web.dto.ReviewRequestDTO;

import java.util.List;

import static umc.spring.domain.enums.MissionStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;



    @Override
    public Review addReview(ReviewRequestDTO.ReviewDto request) {
        User user = userRepository.findByUserIdAndStatus(request.getUserId(), ACTIVE)
                .orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findByRestaurantIdAndStatus(request.getRestaurantId(), ACTIVE)
                .orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));

        List<ReviewImage> reviewImageList = ReviewConverter.toReviewImage(request.getReviewImage());

        Review newReview = ReviewConverter.toReview(user, restaurant, request.getContent(), request.getStar_rate());

        reviewImageList.forEach(reviewImage -> {reviewImage.setReviewId(newReview);});

        return reviewRepository.save(newReview);
    }
}
