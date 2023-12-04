package es.maneldevs.qa.gameservice.application.model.response;

import es.maneldevs.qa.gameservice.domain.AnswerScore;
import es.maneldevs.qa.gameservice.domain.QuestionScore;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@Builder
public class ScoreResponse {
    private final String code;
    private final Integer pointSum;

    public ScoreResponse(QuestionScore score) {
        this.code = score.getCode();
        this.pointSum = score.getPointSum();
    }

    public ScoreResponse(AnswerScore score) {
        this.code = score.getCode();
        this.pointSum = score.getPointSum();
    }
}
