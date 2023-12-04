package es.maneldevs.qa.gameservice.application.in.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.maneldevs.qa.gameservice.application.model.response.ScoreResponse;
import es.maneldevs.qa.gameservice.application.out.AnswerScorePort;
import es.maneldevs.qa.gameservice.domain.AnswerScore;

@ExtendWith(MockitoExtension.class)
public class AnswerScoreQueryServiceTest {
    private List<AnswerScore> answerScores;
    private List<ScoreResponse> scoreResponses;

    @Mock
    private AnswerScorePort questionScorePort;
    @InjectMocks
    AnswerScoreQueryService serviceUnderTest;

    @BeforeEach
    void beforeEach() {
        AnswerScore answerScore1 = AnswerScore.builder()
                .id(1L)
                .version(0)
                .code("acode1")
                .pointSum(10).build();
        ScoreResponse scoreResponse1 = ScoreResponse.builder()
                .code("acode1")
                .pointSum(10).build();
        AnswerScore answerScore2 = AnswerScore.builder()
                .id(2L)
                .version(0)
                .code("acode2")
                .pointSum(20).build();
        ScoreResponse scoreResponse2 = ScoreResponse.builder()
                .code("acode2")
                .pointSum(20).build();
        answerScores = List.of(answerScore1, answerScore2);
        scoreResponses = List.of(scoreResponse1, scoreResponse2);
    }

    @Test
    void getQuestionScores_existentCodes_scoreResponseList() {
        when(questionScorePort.findByCodeIn(List.of("acode1", "acode2"))).thenReturn(answerScores);
        List<ScoreResponse> result = serviceUnderTest.getAnswerScores(List.of("acode1", "acode2"));
        assertEquals(2, result.size());
        assertTrue(result.contains(scoreResponses.get(0)));
        assertTrue(result.contains(scoreResponses.get(1)));
    }
}
