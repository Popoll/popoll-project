package com.esgi.popoll.survey.entity.survey;

import com.esgi.popoll.survey.entity.answer.Answer;
import com.esgi.popoll.survey.entity.vote.Vote;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String question;

    @Column
    private String author;

    @Column
    private String channel;

    @OneToMany(fetch = LAZY, cascade = ALL)
    private List<Answer> answers;

    @OneToMany(fetch = LAZY, cascade = ALL)
    private List<Vote> votes;
}
