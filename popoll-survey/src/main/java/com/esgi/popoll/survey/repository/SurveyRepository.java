package com.esgi.popoll.survey.repository;

import com.esgi.popoll.survey.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Julien on 23/04/2017.
 */
@Repository
public interface SurveyRepository extends JpaRepository<Survey,Long> {

    Optional<Survey> findById (Long id);

}
