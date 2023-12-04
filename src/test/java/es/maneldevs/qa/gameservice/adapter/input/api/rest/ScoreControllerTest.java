package es.maneldevs.qa.gameservice.adapter.input.api.rest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import es.maneldevs.qa.gameservice.application.in.AnswerScoreCommandUseCase;
import es.maneldevs.qa.gameservice.application.in.AnswerScoreQueryUseCase;
import es.maneldevs.qa.gameservice.application.in.QuestionScoreCommandUseCase;
import es.maneldevs.qa.gameservice.application.in.QuestionScoreQueryUseCase;
import es.maneldevs.qa.gameservice.application.model.response.ScoreResponse;

@WebFluxTest(controllers = ScoreController.class)
public class ScoreControllerTest {
    private List<ScoreResponse> scoreResponses;
    private List<String> codes;
    @MockBean
    private QuestionScoreQueryUseCase questionScoreQueryUseCase;
    @MockBean
    private AnswerScoreQueryUseCase answerScoreQueryUseCase;
    @MockBean
    private QuestionScoreCommandUseCase questionScoreCommandUseCase;
    @MockBean
    private AnswerScoreCommandUseCase answerScoreCommandUseCase;
    @Autowired
    private WebTestClient webClient;

    @BeforeEach
    void beforeEach() {
        ScoreResponse score1 = ScoreResponse.builder()
                .code("code1")
                .pointSum(10).build();
        ScoreResponse score2 = ScoreResponse.builder()
                .code("code2")
                .pointSum(20).build();
        scoreResponses = List.of(score1, score2);
        codes = scoreResponses.stream().map(ScoreResponse::getCode).toList();
    }

    @Test
    void getAnswersScore_AnswerCodeList_scoreResponseList() {
        when(answerScoreQueryUseCase.getAnswerScores(codes)).thenReturn(scoreResponses);
        webClient
                .get()
                .uri("/scores/answers?targetCodes=code1,code2")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$.[0].code").isEqualTo("code1")
                .jsonPath("$.[0].pointSum").isEqualTo("10")
                .jsonPath("$.[1].code").isEqualTo("code2")
                .jsonPath("$.[1].pointSum").isEqualTo("20");
    }

    @Test
    void getAnswersScore_nullFilter_422() {
        webClient
                .get()
                .uri("/scores/answers")
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Validation failed")
                .jsonPath("$.errors.targetCodes").isEqualTo("must not be null");
    }

    @Test
    void getAnswersScore_emptyList_422() {
        webClient
                .get()
                .uri("/scores/answers?targetCodes=")
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Validation failed")
                .jsonPath("$.errors.targetCodes").isEqualTo("size must be between 1 and 2147483647");
    }

    @Test
    void getQuestionsScore_QuestionsCodeList_scoreResponseList() {
        when(questionScoreQueryUseCase.getQuestionScores(codes)).thenReturn(scoreResponses);
        webClient
                .get()
                .uri("/scores/questions?targetCodes=code1,code2")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$.[0].code").isEqualTo("code1")
                .jsonPath("$.[0].pointSum").isEqualTo("10")
                .jsonPath("$.[1].code").isEqualTo("code2")
                .jsonPath("$.[1].pointSum").isEqualTo("20");
    }

    @Test
    void getQuestionsScore_nullFilter_422() {
        webClient
                .get()
                .uri("/scores/questions")
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Validation failed")
                .jsonPath("$.errors.targetCodes").isEqualTo("must not be null");
    }

    @Test
    void getQuestionsScore_emptyList_422() {
        webClient
                .get()
                .uri("/scores/questions?targetCodes=")
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Validation failed")
                .jsonPath("$.errors.targetCodes").isEqualTo("size must be between 1 and 2147483647");
    }

    @Test
    void deleteQuestionScore_questionCode_204() {
        webClient
                .delete()
                .uri("/scores/questions/code1")
                .exchange()
                .expectStatus().isNoContent();
        verify(questionScoreCommandUseCase, times(1)).deleteByQuestionCode("code1");
    }

    @Test
    void deleteAnswerScores_answerCodes_204() {
         webClient
                .delete()
                .uri("/scores/answers?targetCodes=code1,code2")
                .exchange()
                .expectStatus().isNoContent();
        verify(answerScoreCommandUseCase, times(1)).deleteByCodes(List.of("code1", "code2"));
    }

    @Test
    void deleteAnswerScores_emptyList_422() {
        webClient
                .delete()
                .uri("/scores/answers")
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody()
                .jsonPath("$.message").isEqualTo("Validation failed")
                .jsonPath("$.errors.targetCodes").isEqualTo("must not be null");
    }
}
