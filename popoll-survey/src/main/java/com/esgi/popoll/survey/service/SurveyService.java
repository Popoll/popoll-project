package com.esgi.popoll.survey.service;

import com.esgi.popoll.survey.survey.SurveyDto;
import com.esgi.popoll.survey.vote.VoteDto;

public interface SurveyService {

    SurveyDto getSurveyById(Long id);
    SurveyDto createSurvey(SurveyDto dto);
    VoteDto addVoteInSurvey(Long id, VoteDto voteDto);
}

