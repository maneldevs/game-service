package es.maneldevs.qa.gameservice.application.in.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.maneldevs.qa.gameservice.application.out.QuestionScorePort;

@ExtendWith(MockitoExtension.class)
public class QuestionScoreCommandServiceTest {
    @Mock
    private QuestionScorePort questionScorePort;
    @InjectMocks
    private QuestionScoreCommandService serviceUnderTest;

    @Test
    void deleteByQuestionCode_existentQuestionScore_void() {
        serviceUnderTest.deleteByQuestionCode("code1");
        verify(questionScorePort, times(1)).deleteByCode("code1");
    }

}
