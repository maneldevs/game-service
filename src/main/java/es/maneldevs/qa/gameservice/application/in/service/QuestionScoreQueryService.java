package es.maneldevs.qa.gameservice.application.in.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.maneldevs.qa.gameservice.application.in.QuestionScoreQueryUseCase;
import es.maneldevs.qa.gameservice.application.model.response.ScoreResponse;
import es.maneldevs.qa.gameservice.application.out.QuestionScorePort;
import es.maneldevs.qa.gameservice.domain.QuestionScore;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionScoreQueryService implements QuestionScoreQueryUseCase {
    private final QuestionScorePort questionScorePort;
    
    @Override
    public List<ScoreResponse> getQuestionScores(List<String> questionsCode) {
        List<QuestionScore> scores = questionScorePort.findByCodeIn(questionsCode);
        return scores.stream().map(ScoreResponse::new).toList();
    }
    
}
