package com.esgi.popoll.survey.entity.vote;

import com.esgi.popoll.survey.entity.answer.Answer;
import com.esgi.popoll.survey.entity.survey.Survey;

public class VoteAdapter {

    public static VoteDto toVoteDto(final Vote vote) {
        return vote != null
            ? VoteDto.builder()
                .id(vote.getId())
                .surveyId(Long.toString(vote.getSurveyId().getId()))
                .userId(vote.getUserId())
                .answer(vote.getAnswerId().getAnswer())
                .build()
            : null;
    }
    public static Vote toVote(final VoteDto voteDto, final Survey survey,
                              final Answer answer) {

        return voteDto != null
            ? Vote.builder()
            .id(voteDto.getId())
            .surveyId(survey)
            .userId(voteDto.getUserId())
            .answerId(answer)
            .build()
        : null;
    }
}
