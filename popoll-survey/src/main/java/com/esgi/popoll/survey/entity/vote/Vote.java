package com.esgi.popoll.survey.entity.vote;

import com.esgi.popoll.survey.entity.answer.Answer;
import com.esgi.popoll.survey.entity.survey.Survey;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn
    private Survey survey;

    @Column
    private String userId;

    @OneToOne(fetch = LAZY)
    @JoinColumn
    private Answer answer;
}
