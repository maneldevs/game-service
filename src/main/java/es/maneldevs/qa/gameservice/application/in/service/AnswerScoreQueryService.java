package es.maneldevs.qa.gameservice.application.in.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.maneldevs.qa.gameservice.application.in.AnswerScoreQueryUseCase;
import es.maneldevs.qa.gameservice.application.model.response.ScoreResponse;
import es.maneldevs.qa.gameservice.application.out.AnswerScorePort;
import es.maneldevs.qa.gameservice.domain.AnswerScore;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerScoreQueryService implements AnswerScoreQueryUseCase {
    private AnswerScorePort answerScorePort;
    
    @Override
    public List<ScoreResponse> getAnswerScores(List<String> answersCode) {
        List<AnswerScore> scores = answerScorePort.findByCodeIn(answersCode);
        return scores.stream().map(ScoreResponse::new).toList();
    }
}
