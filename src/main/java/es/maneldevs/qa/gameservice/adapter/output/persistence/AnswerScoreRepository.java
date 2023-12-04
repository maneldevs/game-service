package es.maneldevs.qa.gameservice.adapter.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import es.maneldevs.qa.gameservice.application.out.AnswerScorePort;
import es.maneldevs.qa.gameservice.domain.AnswerScore;

public interface AnswerScoreRepository extends AnswerScorePort, JpaRepository<AnswerScore, Long> {
    
}
