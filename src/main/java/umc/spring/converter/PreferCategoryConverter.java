package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import umc.spring.domain.PreferCategory;
import umc.spring.domain.RestaurantCategory;
import umc.spring.domain.User;
import umc.spring.web.dto.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PreferCategoryConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return UserResponseDTO.JoinResultDTO.builder()
                .memberId(user.getUserId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static List<PreferCategory> toUserPreferList(User user, List<RestaurantCategory> restaurantCategoryList){

        return restaurantCategoryList.stream()
                .map(foodCategory ->
                        PreferCategory.builder()
                                .restaurantCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
