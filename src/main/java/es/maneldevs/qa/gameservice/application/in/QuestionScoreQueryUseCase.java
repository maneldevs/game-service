package es.maneldevs.qa.gameservice.application.in;

import java.util.List;

import es.maneldevs.qa.gameservice.application.model.response.ScoreResponse;

public interface QuestionScoreQueryUseCase {
    List<ScoreResponse> getQuestionScores(List<String> questionsCode);
}
