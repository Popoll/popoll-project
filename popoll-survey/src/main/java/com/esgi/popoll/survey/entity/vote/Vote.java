package com.esgi.popoll.survey.entity.vote;

import com.esgi.popoll.survey.entity.answer.Answer;
import com.esgi.popoll.survey.entity.survey.Survey;
import lombok.*;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.LazyToOneOption.NO_PROXY;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn
    private Survey survey;

    @Column
    private String userId;

    @OneToOne(fetch = LAZY)
    @JoinColumn
    @LazyToOne(NO_PROXY)
    private Answer answer;
}
