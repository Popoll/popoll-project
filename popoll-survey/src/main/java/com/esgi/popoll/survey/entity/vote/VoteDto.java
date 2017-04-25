package com.esgi.popoll.survey.entity.vote;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

    private Long id;

    @NotNull
    private String surveyId;

    @NotNull
    private String userId;

    @NotNull
    private String answer;
}
