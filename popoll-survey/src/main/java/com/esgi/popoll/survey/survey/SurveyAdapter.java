package com.esgi.popoll.survey.survey;

import org.springframework.stereotype.Component;

/**
 * Created by Julien on 23/04/2017.
 */
@Component
public class SurveyAdapter {
    public SurveyDto toSurveyDto(Survey survey){
        return survey != null
                ?SurveyDto.builder()
                    .id(survey.getId())
                    .votes(survey.getVotes())
                    .question(survey.getQuestion())
                    .answer(survey.getAnswer())
                    .author(survey.getAuthor())
                    .channel(survey.getChannel()).build()
                : null;
    }

    public Survey toSurvey(SurveyDto surveyDto){
        return surveyDto != null
                ?Survey.builder()
                .id(surveyDto.getId())
                .votes(surveyDto.getVotes())
                .question(surveyDto.getQuestion())
                .answer(surveyDto.getAnswer())
                .author(surveyDto.getAuthor())
                .channel(surveyDto.getChannel()).build()
                : null;
    }

}
