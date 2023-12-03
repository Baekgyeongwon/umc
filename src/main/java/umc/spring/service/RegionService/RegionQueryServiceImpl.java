package umc.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.RestaurantRepository;
import umc.spring.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class RegionQueryServiceImpl implements RegionQueryService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

//    @Override
//    public Optional<Restaurant> findRestaurant(Long id) {
//        return restaurantRepository.findByRestaurantIdAndStatus(id, Status.ACTIVE);
//    }
//
//    @Override
//    public Page<Review> getReviewList(Long restaurantId, Integer page) {
//        Restaurant restaurant = restaurantRepository.findByRestaurantIdAndStatus(restaurantId, ACTIVE)
//                .orElseThrow(() -> new RestaurantHandler(ErrorStatus.RESTAURANT_NOT_FOUND));
//
//        Page<Review> restaurantPage = reviewRepository.findAllByRestaurantId(restaurant, PageRequest.of(page, 10));
//        return restaurantPage;
//    }
}
