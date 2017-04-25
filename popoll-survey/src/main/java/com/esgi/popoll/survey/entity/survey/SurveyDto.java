package com.esgi.popoll.survey.entity.survey;

import com.esgi.popoll.survey.entity.answer.AnswerDto;
import com.esgi.popoll.survey.entity.vote.VoteDto;
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
        private String author;

        @NotNull
        private String channel;

        @NotNull
        private List<AnswerDto> answers;

        private List<VoteDto> votes;
}
