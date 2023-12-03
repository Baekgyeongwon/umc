package umc.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Check;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @Column(columnDefinition = "VARCHAR(5)")
    private Check isConfirmed;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "alarmId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewAlarm> reviewAlarmList = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "alarmId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QnaAnswerAlarm> qnaAnswerAlarmList = new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "alarmId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdvAlarm> advAlarmList = new ArrayList<>();

}
