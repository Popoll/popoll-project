package com.esgi.popoll.survey.entity.vote;

import com.esgi.popoll.survey.entity.answer.Answer;
import com.esgi.popoll.survey.entity.survey.Survey;

public class VoteAdapter {

    public static VoteDto toVoteDto(final Vote vote) {
        return vote != null
            ? VoteDto.builder()
                .id(vote.getId())
                //.surveyId(Long.toString(vote.getSurvey().getId()))
                .userId(vote.getUserId())
                .answer(vote.getAnswer().getAnswer())
                .build()
            : null;
    }
    public static Vote toVote(final VoteDto voteDto, final Survey survey,
                              final Answer answer) {

        return voteDto != null
            ? Vote.builder()
            .id(voteDto.getId())
            .survey(survey)
            .userId(voteDto.getUserId())
            .answer(answer)
            .build()
        : null;
    }
}
