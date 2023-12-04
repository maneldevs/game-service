package es.maneldevs.qa.gameservice.adapter.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import es.maneldevs.qa.gameservice.application.out.QuestionScorePort;
import es.maneldevs.qa.gameservice.domain.QuestionScore;

public interface QuestionScoreRepository extends QuestionScorePort, JpaRepository<QuestionScore, Long> {
    
}
