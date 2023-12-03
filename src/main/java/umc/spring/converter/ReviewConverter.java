package umc.spring.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import umc.spring.domain.*;
import umc.spring.web.dto.RestaurantResponseDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReviewConverter {

    public static ReviewResponseDTO.ReviewResultDTO toReviewResultDTO(Review review){
        return ReviewResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getReviewId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(User user, Restaurant restaurant, String content, Float starRate){

        return Review.builder()
                .userId(user)
                .restaurantId(restaurant)
                .content(content)
                .starRate(starRate)
                .build();
    }

    public static List<ReviewImage> toReviewImage(List<String> reviewImageList){

        return reviewImageList.stream()
                .map(image ->
                        ReviewImage.builder()
                                .image(image)
                                .build()
                ).collect(Collectors.toList());
    }

    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getUserId().getName())
                .starRate(review.getStarRate())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .reviewImageList(review.getReviewImageList().stream().map(ReviewImage::getImage).collect(Collectors.toList()))
                .build();
    }
    public static ReviewResponseDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewList){

        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

}
