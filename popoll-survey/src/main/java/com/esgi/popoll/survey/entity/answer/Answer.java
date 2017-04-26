package com.esgi.popoll.survey.entity.answer;

import com.esgi.popoll.survey.entity.survey.Survey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column
	private String answer;

	@OneToOne(fetch = LAZY)
	@JoinColumn
	private Survey survey;
}
