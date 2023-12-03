package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PreferCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurantCategoryId")
    private RestaurantCategory restaurantCategory;

    public void setUser(User user){
        if(this.user != null)
            user.getPreferCategoryList().remove(this);
        this.user = user;
        user.getPreferCategoryList().add(this);
    }

    public void setRestaurantCategory(RestaurantCategory restaurantCategory){
        this.restaurantCategory = restaurantCategory;
    }

}
