package es.maneldevs.qa.gameservice.application.in.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.maneldevs.qa.gameservice.application.out.AnswerScorePort;

@ExtendWith(MockitoExtension.class)
public class AnswerScoreCommandServiceTest {
    @Mock
    private AnswerScorePort answerScorePort;
    @InjectMocks
    private AnswerScoreCommandService serviceUnderTest;

    @Test
    void deleteByAnswersCode_codeList_void() {
        serviceUnderTest.deleteByCodes(List.of("code1", "code2"));
        verify(answerScorePort, times(1)).deleteByCode("code1");
        verify(answerScorePort, times(1)).deleteByCode("code2");
    }

}
