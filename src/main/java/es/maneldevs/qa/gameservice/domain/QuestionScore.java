package es.maneldevs.qa.gameservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question_scores", indexes = { @Index(name = "questioon_scores_uk", unique = true, columnList = "code") })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;
    @Column(name = "code")
    private String code;
    @Column(name = "points")
    private Integer pointSum;
}
