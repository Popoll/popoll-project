package com.esgi.popoll.survey.repository;

import com.esgi.popoll.survey.entity.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SurveyRepository extends JpaRepository<Survey,Long> {

    Optional<Survey> findById(Long id);
}
