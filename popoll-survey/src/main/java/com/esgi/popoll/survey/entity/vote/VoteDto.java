package com.esgi.popoll.survey.entity.vote;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

    private Long id;

    @NotNull
    private Long surveyId;

    @NotNull
    private Integer userId;

    @NotNull
    private String answer;
}
