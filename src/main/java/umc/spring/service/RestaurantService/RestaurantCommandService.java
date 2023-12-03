package umc.spring.service.RestaurantService;

import umc.spring.domain.Restaurant;
import umc.spring.web.dto.RestaurantRequestDTO;

public interface RestaurantCommandService {
    Restaurant addRestaurant(RestaurantRequestDTO.RestaurantDto request);
}
