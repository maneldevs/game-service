package es.maneldevs.qa.gameservice.adapter.input.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.maneldevs.qa.gameservice.application.model.filter.ScoreFilter;
import es.maneldevs.qa.gameservice.application.model.response.ScoreResponse;
import jakarta.validation.Valid;

@RequestMapping("scores")
public interface ScoreApi {
    
    @GetMapping("questions")
    List<ScoreResponse> getQuestionsScore(@Valid ScoreFilter filter);

    @GetMapping("answers")
    List<ScoreResponse> getAnswersScore(@Valid ScoreFilter filter);

    @DeleteMapping("questions/{questionCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestionScore(@PathVariable String questionCode);

    @DeleteMapping("answers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAnswerScores(@Valid ScoreFilter filter);
    
}
