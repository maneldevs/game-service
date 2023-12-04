package es.maneldevs.qa.gameservice.adapter.input.api.rest;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import es.maneldevs.qa.gameservice.adapter.input.api.ScoreApi;
import es.maneldevs.qa.gameservice.application.in.AnswerScoreCommandUseCase;
import es.maneldevs.qa.gameservice.application.in.AnswerScoreQueryUseCase;
import es.maneldevs.qa.gameservice.application.in.QuestionScoreCommandUseCase;
import es.maneldevs.qa.gameservice.application.in.QuestionScoreQueryUseCase;
import es.maneldevs.qa.gameservice.application.model.filter.ScoreFilter;
import es.maneldevs.qa.gameservice.application.model.response.ScoreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ScoreController implements ScoreApi {
    private final QuestionScoreQueryUseCase questionScoreQueryUseCase;
    private final AnswerScoreQueryUseCase answerScoreQueryUseCase;
    private final QuestionScoreCommandUseCase questionScoreCommandUseCase;
    private final AnswerScoreCommandUseCase answerScoreCommandUseCase;

    @Override
    public List<ScoreResponse> getQuestionsScore(@Valid ScoreFilter filter) {
        return questionScoreQueryUseCase.getQuestionScores(filter.getTargetCodes());
    }

    @Override
    public List<ScoreResponse> getAnswersScore(@Valid ScoreFilter filter) {
        return answerScoreQueryUseCase.getAnswerScores(filter.getTargetCodes());
    }

    @Override
    public void deleteQuestionScore(String questionCode) {
        questionScoreCommandUseCase.deleteByQuestionCode(questionCode);
    }

    @Override
    public void deleteAnswerScores(@Valid ScoreFilter filter) {
        answerScoreCommandUseCase.deleteByCodes(filter.getTargetCodes());
    }

}
