package com.esgi.popoll.survey.entity.survey;

import com.esgi.popoll.survey.entity.answer.AnswerAdapter;
import com.esgi.popoll.survey.entity.vote.VoteAdapter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Setter
@Component
public class SurveyAdapter {

    private AnswerAdapter answerAdapter;
    private VoteAdapter voteAdapter;

    public SurveyDto toSurveyDto(final Survey survey) {
        return survey != null
            ? SurveyDto.builder()
            .id(survey.getId())
            .votes(survey.getVotes().stream().map(voteAdapter::toVoteDto).collect(toList()))
            .question(survey.getQuestion())
            .answers(survey.getAnswers().stream().map(answerAdapter::toAnswerDto).collect(toList()))
            .author(survey.getAuthor())
            .channel(survey.getChannel()).build()
        : null;
    }

    public Survey toSurvey(final SurveyDto surveyDto) {
        return surveyDto != null
            ? Survey.builder()
            .id(surveyDto.getId())
            .votes(surveyDto.getVotes().stream().map(voteAdapter::toVote).collect(toList()))
            .question(surveyDto.getQuestion())
            .answers(surveyDto.getAnswers().stream().map(answerAdapter::toAnswer).collect(toList()))
            .author(surveyDto.getAuthor())
            .channel(surveyDto.getChannel()).build()
        : null;
    }
}
