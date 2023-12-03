package umc.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.RestaurantRepository;
import umc.spring.repository.ReviewImageRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RegionCommandServiceImpl implements RegionCommandService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;



//    @Override
//    public Review addReview(ReviewRequestDTO.ReviewDto request) {
//        User user = userRepository.findByUserIdAndStatus(request.getUserId(), ACTIVE)
//                .orElseThrow(() -> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));
//        Restaurant restaurant = restaurantRepository.findByRestaurantIdAndStatus(request.getRestaurantId(), ACTIVE)
//                .orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));
//
//        List<ReviewImage> reviewImageList = ReviewConverter.toReviewImage(request.getReviewImage());
//
//        Review newReview = ReviewConverter.toReview(user, restaurant, request.getContent(), request.getStar_rate());
//
//        reviewImageList.forEach(reviewImage -> {reviewImage.setReviewId(newReview);});
//
//        return reviewRepository.save(newReview);
//    }
}
