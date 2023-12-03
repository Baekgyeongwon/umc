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
public class Qna extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User userId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(columnDefinition = "VARCHAR(5)")
    private Check answer;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "qnaId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QnaImage> qnaImageList = new ArrayList<>();
}
