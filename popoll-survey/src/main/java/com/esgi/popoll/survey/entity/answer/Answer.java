package com.esgi.popoll.survey.entity.answer;

import com.esgi.popoll.survey.entity.survey.Survey;
import com.esgi.popoll.survey.entity.vote.Vote;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
public class Answer {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String answer;

	@OneToOne(fetch = LAZY)
	@JoinColumn
	private Survey surveyId;
}
