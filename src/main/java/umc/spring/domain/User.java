package umc.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Check;
import umc.spring.domain.enums.Gender;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = true, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = true, length = 1000)
    private String image;

    @Column(nullable = false)
    private String birth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regionId")
    private Region address;

    @Column(nullable = true, length = 20)
    private String phoneNumber;

    @Column(nullable = true, length = 255)
    private String detailAddress;

    @Column(nullable = true, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Check gpsCheck = Check.NO;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Check advCheck = Check.NO;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Check alarmCheck = Check.NO;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "preferId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PreferCategory> preferCategoryList = new ArrayList<>();

//    @JsonIgnore
//    @Builder.Default
//    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<MyMission> myMissionList = new ArrayList<>();

}
