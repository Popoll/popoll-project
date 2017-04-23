package com.esgi.popoll.survey.Service;

import com.esgi.popoll.survey.survey.Survey;
import com.esgi.popoll.survey.survey.SurveyDto;
import com.esgi.popoll.survey.vote.VoteDto;

import java.util.List;
import java.util.Optional;

/**
 * Created by Julien on 23/04/2017.
 */
public interface SurveyService {

    SurveyDto getSurveyById (Long id);
    SurveyDto createSurvey(SurveyDto dto);
    VoteDto addVoteInSurvey (Long id, VoteDto voteDto);

}

