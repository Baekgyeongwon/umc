package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Restaurant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regionId")
    private Region regionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantCategoryId")
    private RestaurantCategory restaurantCategoryId;

    @Column(nullable = false, length = 50)
    private String name;

}
