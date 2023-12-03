package umc.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewId")
    private Review reviewId;

    @Column(nullable = false, length = 1000)
    private String image;

    public void setReviewId(Review review){
        if(this.reviewId != null)
            review.getReviewImageList().remove(this);
        this.reviewId = review;
        review.getReviewImageList().add(this);
    }

}
