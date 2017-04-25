package com.esgi.popoll.survey.entity.vote;

import com.esgi.popoll.survey.entity.answer.AnswerDto;
import com.esgi.popoll.survey.entity.survey.SurveyDto;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

    private Long id;

    @NotNull
    private SurveyDto surveyId;

    @NotNull
    private String userId;

    @NotNull
    private AnswerDto answerId;
}
