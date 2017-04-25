package com.esgi.popoll.survey.entity.vote;

import com.esgi.popoll.survey.entity.answer.AnswerAdapter;
import com.esgi.popoll.survey.entity.survey.SurveyAdapter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Component
public class VoteAdapter {

    private SurveyAdapter surveyAdapter;
    private AnswerAdapter answerAdapter;

    public VoteDto toVoteDto(final Vote vote) {
        return vote != null
            ? VoteDto.builder()
                .id(vote.getId())
                .surveyId(surveyAdapter.toSurveyDto(vote.getSurveyId()))
                .userId(vote.getUserId())
                .answerId(answerAdapter.toAnswerDto(vote.getAnswerId()))
                .build()
            : null;
    }
    public Vote toVote(final VoteDto voteDto) {
        return voteDto != null
            ? Vote.builder()
            .id(voteDto.getId())
            .surveyId(surveyAdapter.toSurvey(voteDto.getSurveyId()))
            .userId(voteDto.getUserId())
            .answerId(answerAdapter.toAnswer(voteDto.getAnswerId()))
            .build()
        : null;
    }
}
