package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.ReviewImage;
import umc.spring.validation.annotation.ExistCategories;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistRestaurant;
import umc.spring.validation.annotation.ExistUser;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewDto{

        @ExistUser
        Long userId;
        @ExistRestaurant
        Long restaurantId;
        @Size(min = 2, max = 500)
        String content;
        @NotNull
        Float star_rate;

        List<String> reviewImage;
    }


}
