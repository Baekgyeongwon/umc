package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.ExistCategory;
import umc.spring.validation.annotation.ExistRegion;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RestaurantRequestDTO {
    @Getter
    public static class RestaurantDto{

        @ExistCategory
        Long restaurantCategoryId;
        @ExistRegion
        Long regionId;

        @NotBlank
        @NotNull
        String name;
    }
}
