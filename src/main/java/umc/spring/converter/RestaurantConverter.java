package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import umc.spring.domain.*;
import umc.spring.web.dto.MyMissionResponseDTO;
import umc.spring.web.dto.RestaurantResponseDTO;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class RestaurantConverter {

    public static RestaurantResponseDTO.RestaurantResultDTO toRestaurantResultDTO(Restaurant restaurant){
        return RestaurantResponseDTO.RestaurantResultDTO.builder()
                .restaurantId(restaurant.getRestaurantId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Restaurant toRestaurant(RestaurantCategory restaurantCategory, Region region, String name){

        return Restaurant.builder()
                .restaurantCategoryId(restaurantCategory)
                .regionId(region)
                .name(name)
                .build();
    }
}
