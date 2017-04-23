package com.esgi.popoll.survey.vote;

import org.springframework.stereotype.Component;

@Component
public class VoteAdapter {

    public VoteDto toVoteDto(final Vote vote) {
        return vote != null
            ? VoteDto.builder()
                .id(vote.getId())
                .surveyId(vote.getSurveyId())
                .userId(vote.getUserId())
                .answer(vote.getAnswer()).build()
            : null;
    }
    public Vote toVote(final VoteDto voteDto) {
        return voteDto != null
            ? Vote.builder()
            .id(voteDto.getId())
            .surveyId(voteDto.getSurveyId())
            .userId(voteDto.getUserId())
            .answer(voteDto.getAnswer()).build()
        : null;
    }
}
