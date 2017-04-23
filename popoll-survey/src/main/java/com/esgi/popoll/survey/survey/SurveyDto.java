package com.esgi.popoll.survey.survey;

import com.esgi.popoll.survey.vote.Vote;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Julien on 23/04/2017.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDto {

        private Long id;
        @NotNull
        private List<Vote> votes;
        @NotNull
        private String question;
        @NotNull
        private String answer;
        @NotNull
        private String author;
        @NotNull
        private String channel;

}
