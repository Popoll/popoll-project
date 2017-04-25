package com.esgi.popoll.survey.entity.vote;

import com.esgi.popoll.survey.entity.answer.AnswerAdapter;
import com.esgi.popoll.survey.entity.survey.SurveyAdapter;

public class VoteAdapter {

    public static VoteDto toVoteDto(final Vote vote) {
        return vote != null
            ? VoteDto.builder()
                .id(vote.getId())
                .surveyId(SurveyAdapter.toSurveyDto(vote.getSurveyId()))
                .userId(vote.getUserId())
                .answer(AnswerAdapter.toAnswerDto(vote.getAnswerId()))
                .build()
            : null;
    }
    public static Vote toVote(final VoteDto voteDto) {
        return voteDto != null
            ? Vote.builder()
            .id(voteDto.getId())
            .surveyId(SurveyAdapter.toSurvey(voteDto.getSurveyId()))
            .userId(voteDto.getUserId())
            .answerId(AnswerAdapter.toAnswer(voteDto.getAnswer()))
            .build()
        : null;
    }
}
