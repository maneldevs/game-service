package es.maneldevs.qa.gameservice.adapter.output.persistence;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import es.maneldevs.qa.gameservice.MysqlTestBase;
import es.maneldevs.qa.gameservice.domain.AnswerScore;

public class AnswerScoreRepositoryTest extends MysqlTestBase {
    private List<AnswerScore> savedScores;
    @Autowired
    private TestEntityManager em;
    @Autowired
    private AnswerScoreRepository repositoryUnderTest;

    @BeforeEach
    void beforeEach() {
        AnswerScore score1 = AnswerScore.builder()
                .code("code1")
                .pointSum(10).build();
        AnswerScore score2 = AnswerScore.builder()
                .code("code2")
                .pointSum(20).build();
        em.persist(score1);
        em.persist(score2);
        savedScores = List.of(score1, score2);
    }

    @Test
    void findByCodeIn_existentCodes_scoreList() {
        List<AnswerScore> result = repositoryUnderTest.findByCodeIn(List.of("code1"));
        assertEquals(1, result.size());
        assertTrue(savedScores.contains(result.get(0)));
        result = repositoryUnderTest.findByCodeIn(List.of("code1", "code2"));
        assertEquals(2, result.size());
        assertTrue(savedScores.contains(result.get(0)));
        assertTrue(savedScores.contains(result.get(1)));
    }

    @Test
    void findByCodeIn_NonexistentCodes_emptyList() {
        List<AnswerScore> result = repositoryUnderTest.findByCodeIn(List.of("codenonexistent"));
        assertEquals(0, result.size());
    }

    @Test
    void deleteByCode_answerCode_void() {
        Long result = repositoryUnderTest.deleteByCode("code1");
        assertEquals(1L, result);
        AnswerScore deleted = em.find(AnswerScore.class, 1L);
        assertNull(deleted);
        AnswerScore existent = em.find(AnswerScore.class, 2L);
        assertNotNull(existent);
    }
}
