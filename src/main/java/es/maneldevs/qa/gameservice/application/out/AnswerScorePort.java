package es.maneldevs.qa.gameservice.application.out;

import java.util.List;

import es.maneldevs.qa.gameservice.domain.AnswerScore;

public interface AnswerScorePort {
    List<AnswerScore> findByCodeIn(List<String> codes);
    Long deleteByCode(String code);
}
