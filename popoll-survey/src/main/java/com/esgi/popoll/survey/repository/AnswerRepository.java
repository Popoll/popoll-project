package com.esgi.popoll.survey.repository;

import com.esgi.popoll.survey.entity.answer.Answer;
import com.esgi.popoll.survey.entity.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("select a from Answer a where a.answer=:answerValue and a.survey=:survey")
    Optional<Answer> findAnswerByAnswerAndSurveyId(@Param("answerValue") String answerValue, @Param("survey") Survey survey);
}
