package es.maneldevs.qa.gameservice.application.in.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.maneldevs.qa.gameservice.application.in.AnswerScoreCommandUseCase;
import es.maneldevs.qa.gameservice.application.out.AnswerScorePort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerScoreCommandService implements AnswerScoreCommandUseCase {
    private final AnswerScorePort answerScorePort;

    @Override
    @Transactional
    public void deleteByCodes(List<String> codes) {
        codes.forEach(answerScorePort::deleteByCode);
    }


}
