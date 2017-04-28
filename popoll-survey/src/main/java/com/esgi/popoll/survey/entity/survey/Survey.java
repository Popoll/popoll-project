package com.esgi.popoll.survey.entity.survey;

import com.esgi.popoll.survey.entity.answer.Answer;
import com.esgi.popoll.survey.entity.vote.Vote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "survey")
public class Survey {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String question;

    @Column
    private String author;

    @Column
    private String channel;

    @OneToMany(fetch = LAZY, mappedBy = "survey", cascade = ALL)
    private List<Answer> answers;

    @OneToMany(fetch = LAZY, mappedBy = "survey", cascade = ALL)
    private List<Vote> votes;
}
