package com.esgi.popoll.survey.survey;

import com.esgi.popoll.survey.vote.Vote;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Julien on 23/04/2017.
 */
@Entity
@Builder
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "survey")
public class Survey {
    @Id
    private Long id;
    @OneToMany
    private List<Vote> votes;
    @Column
    private String question;
    @Column
    private String answer;
    @Column
    private String author;
    @Column
    private String channel;
}
