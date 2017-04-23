package com.esgi.popoll.survey.vote;

import com.esgi.popoll.survey.survey.Survey;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;


/**
 * Created by Julien on 23/04/2017.
 */
@Entity
@Builder
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vote")
public class Vote {

    @Id
    private Long id;
    @Column
    private Long surveyId;
    @Column
    private Integer userId;
    @Column
    private String answer;

}
