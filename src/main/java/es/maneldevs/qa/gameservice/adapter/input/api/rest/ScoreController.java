package es.maneldevs.qa.gameservice.adapter.input.api.rest;

import java.util.stream.Stream;

import org.springframework.web.bind.annotation.RestController;

import es.maneldevs.libs.exceptionhandling.NotFoundException;
import es.maneldevs.qa.gameservice.adapter.input.api.ScoreApi;
import es.maneldevs.qa.gameservice.application.model.filter.ScoreFilter;
import es.maneldevs.qa.gameservice.application.model.response.ScoreResponse;
import jakarta.validation.Valid;

@RestController
public class ScoreController implements ScoreApi {

    @Override
    public Stream<ScoreResponse> getQuestionsScore(@Valid ScoreFilter filter) {
        if (filter.getTargetCodes().contains("error")) {
            throw new NotFoundException("Question not found");
        }
        return filter.getTargetCodes().stream()
                .map(c -> ScoreResponse.builder().code(c).pointSum((int) (Math.random() * 100)).build());
    }

    @Override
    public Stream<ScoreResponse> getAnswersScore(@Valid ScoreFilter filter) {
        if (filter.getTargetCodes().contains("error")) {
            throw new NotFoundException("Answer not found");
        }
        return filter.getTargetCodes().stream()
                .map(c -> ScoreResponse.builder().code(c).pointSum((int) (Math.random() * 100)).build());
    }

}
