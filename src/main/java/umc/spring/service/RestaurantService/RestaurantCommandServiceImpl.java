package umc.spring.service.RestaurantService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.converter.RestaurantConverter;
import umc.spring.domain.*;
import umc.spring.repository.*;
import umc.spring.web.dto.RestaurantRequestDTO;

import static umc.spring.domain.enums.MissionStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class RestaurantCommandServiceImpl implements RestaurantCommandService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantCategoryRepository restaurantCategoryRepository;
    private final RegionRepository regionRepository;

    @Override
    public Restaurant addRestaurant(RestaurantRequestDTO.RestaurantDto request) {
        Region region = regionRepository.findByRegionIdAndStatus(request.getRegionId(), ACTIVE)
                .orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));
        RestaurantCategory restaurantCategory = restaurantCategoryRepository.findByRestaurantCategoryIdAndStatus(request.getRestaurantCategoryId(), ACTIVE)
                .orElseThrow(() -> new RegionHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));

        Restaurant newRestaurant = RestaurantConverter.toRestaurant(restaurantCategory, region, request.getName());

        return restaurantRepository.save(newRestaurant);
    }
}
