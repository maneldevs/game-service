package es.maneldevs.qa.gameservice.application.in.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.maneldevs.qa.gameservice.application.in.QuestionScoreCommandUseCase;
import es.maneldevs.qa.gameservice.application.out.QuestionScorePort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionScoreCommandService implements QuestionScoreCommandUseCase {
    private final QuestionScorePort questionScorePort;

    @Override
    @Transactional
    public void deleteByQuestionCode(String questionCode) {
        questionScorePort.deleteByCode(questionCode);
    }
}
