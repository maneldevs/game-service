package es.maneldevs.qa.gameservice.application.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class ScoreResponse {
    private final String code;
    private final Integer pointSum;
}
