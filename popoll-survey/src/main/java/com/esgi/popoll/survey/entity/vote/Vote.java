package com.esgi.popoll.survey.entity.vote;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long surveyId;

    @Column
    private Integer userId;

    @Column
    private String answer;
}
