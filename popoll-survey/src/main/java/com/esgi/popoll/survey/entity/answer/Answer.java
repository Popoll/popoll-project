package com.esgi.popoll.survey.entity.answer;

import com.esgi.popoll.survey.entity.survey.Survey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@Data
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
	private Survey survey;
}
