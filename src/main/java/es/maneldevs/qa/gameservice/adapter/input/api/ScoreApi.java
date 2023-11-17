package es.maneldevs.qa.gameservice.adapter.input.api;

import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.maneldevs.qa.gameservice.application.model.filter.ScoreFilter;
import es.maneldevs.qa.gameservice.application.model.response.ScoreResponse;
import jakarta.validation.Valid;

@RequestMapping("scores")
public interface ScoreApi {
    
    @GetMapping("questions")
    Stream<ScoreResponse> getQuestionsScore(@Valid ScoreFilter filter);

    @GetMapping("answers")
    Stream<ScoreResponse> getAnswersScore(@Valid ScoreFilter filter);
    
}
