    package umc.spring.domain;

import lombok.*;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;

    @Entity
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public class QnaAnswerAlarm extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long qnaAnswerAlarmId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "alarmId")
        private Alarm alarmId;

        @Column(nullable = false, length = 50)
        private String title;

        @Column(nullable = false, length = 500)
        private String content;

    }
