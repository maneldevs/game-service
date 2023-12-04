package es.maneldevs.qa.gameservice.application.out;

import java.util.List;

import es.maneldevs.qa.gameservice.domain.QuestionScore;

public interface QuestionScorePort {
    List<QuestionScore> findByCodeIn(List<String> codes);
    Long deleteByCode(String code);
}
