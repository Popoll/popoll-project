package com.esgi.popoll.survey.entity.survey;

import com.esgi.popoll.survey.entity.vote.Vote;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    private String answer;

    @Column
    private String author;

    @Column
    private String channel;

    @OneToMany
    private List<Vote> votes;
}
