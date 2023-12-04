package es.maneldevs.qa.gameservice.application.in.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.maneldevs.qa.gameservice.application.model.response.ScoreResponse;
import es.maneldevs.qa.gameservice.application.out.QuestionScorePort;
import es.maneldevs.qa.gameservice.domain.QuestionScore;

@ExtendWith(MockitoExtension.class)
public class QuestionScoreQueryServiceTest {
    private List<QuestionScore> questionScores;
    private List<ScoreResponse> scoreResponses;

    @Mock
    private QuestionScorePort questionScorePort;
    @InjectMocks
    QuestionScoreQueryService serviceUnderTest;

    @BeforeEach
    void beforeEach() {
        QuestionScore questionScore1 = QuestionScore.builder()
                .id(1L)
                .version(0)
                .code("qcode1")
                .pointSum(10).build();
        ScoreResponse scoreResponse1 = ScoreResponse.builder()
                .code("qcode1")
                .pointSum(10).build();
        QuestionScore questionScore2 = QuestionScore.builder()
                .id(2L)
                .version(0)
                .code("qcode2")
                .pointSum(20).build();
        ScoreResponse scoreResponse2 = ScoreResponse.builder()
                .code("qcode2")
                .pointSum(20).build();
        questionScores = List.of(questionScore1, questionScore2);
        scoreResponses = List.of(scoreResponse1, scoreResponse2);
    }

    @Test
    void getQuestionScores_existentCodes_scoreResponseList() {
        when(questionScorePort.findByCodeIn(List.of("qcode1", "qcode2"))).thenReturn(questionScores);
        List<ScoreResponse> result = serviceUnderTest.getQuestionScores(List.of("qcode1", "qcode2"));
        assertEquals(2, result.size());
        assertTrue(result.contains(scoreResponses.get(0)));
        assertTrue(result.contains(scoreResponses.get(1)));
    }

    

}
