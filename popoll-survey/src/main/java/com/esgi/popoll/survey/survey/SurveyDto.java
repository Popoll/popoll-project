package com.esgi.popoll.survey.survey;

import com.esgi.popoll.survey.vote.Vote;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDto {

        private Long id;

        @NotNull
        private String question;

        @NotNull
        private String answer;

        @NotNull
        private String author;

        @NotNull
        private String channel;

        @NotNull
        private List<Vote> votes;
}
