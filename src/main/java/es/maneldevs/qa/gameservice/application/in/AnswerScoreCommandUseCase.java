package es.maneldevs.qa.gameservice.application.in;

import java.util.List;

public interface AnswerScoreCommandUseCase {
    void deleteByCodes(List<String> codes);
}
