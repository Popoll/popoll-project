package com.esgi.popoll.survey.vote;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by Julien on 23/04/2017.
 */

@Builder
@Getter
@Setter
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
