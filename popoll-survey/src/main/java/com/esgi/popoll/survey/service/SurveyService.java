package com.esgi.popoll.survey.service;

import com.esgi.popoll.survey.entity.survey.SurveyDto;
import com.esgi.popoll.survey.entity.vote.VoteDto;

public interface SurveyService {

    SurveyDto getSurveyById(Long id);
    SurveyDto createSurvey(SurveyDto surveyDto);
    VoteDto addVoteInSurvey(Long id, VoteDto voteDto);
    void sendSurveyToWebsocket(SurveyDto surveyDto);
}

