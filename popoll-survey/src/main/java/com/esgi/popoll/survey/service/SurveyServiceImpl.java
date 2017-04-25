package com.esgi.popoll.survey.service;

import com.esgi.popoll.survey.entity.answer.Answer;
import com.esgi.popoll.survey.entity.answer.AnswerAdapter;
import com.esgi.popoll.survey.entity.answer.AnswerDto;
import com.esgi.popoll.survey.entity.survey.Survey;
import com.esgi.popoll.survey.entity.survey.SurveyAdapter;
import com.esgi.popoll.survey.entity.survey.SurveyDto;
import com.esgi.popoll.survey.entity.vote.VoteAdapter;
import com.esgi.popoll.survey.entity.vote.VoteDto;
import com.esgi.popoll.survey.exception.InvalidVoteException;
import com.esgi.popoll.survey.exception.NotFoundSurveyException;
import com.esgi.popoll.survey.repository.AnswerRepository;
import com.esgi.popoll.survey.repository.SurveyRepository;
import com.esgi.popoll.survey.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final VoteRepository voteRepository;
    private final AnswerRepository answerRepository;
    private final RestTemplate restTemplate;

    @Value("{info.services.websocket}")
    private String webSocketServiceUrl;

    public SurveyServiceImpl(final SurveyRepository surveyRepository, final VoteRepository voteRepository,
                             final AnswerRepository answerRepository, final RestTemplate restTemplate)
    {
        this.surveyRepository = surveyRepository;
        this.voteRepository = voteRepository;
        this.answerRepository = answerRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional(readOnly = true)
    public SurveyDto getSurveyById(final Long id) {
        return SurveyAdapter.toSurveyDto(
            surveyRepository.findById(id).orElseThrow(NotFoundSurveyException::new)
        );
    }

    @Override
    public SurveyDto createSurvey(final SurveyDto surveyDto) {
        // PB en BDD answer survey_id null
        return SurveyAdapter.toSurveyDto(surveyRepository.save(SurveyAdapter.toSurvey(surveyDto)));
    }

    @Override
    public AnswerDto addAnswerInSurvey(final Long id, final AnswerDto answerDto) {
        final SurveyDto surveyDto = getSurveyById(id);
        answerDto.setSurveyId(surveyDto);

        return AnswerAdapter.toAnswerDto(answerRepository.save(AnswerAdapter.toAnswer(answerDto)));
    }

    @Override
    public VoteDto addVoteInSurvey(final Long id, final VoteDto voteDto) {

        System.out.println(voteDto);
        final Survey survey = surveyRepository.findById(id).orElseThrow(NotFoundSurveyException::new);
        final Answer answer = answerRepository.findAnswerByAnswerAndSurveyId(
            voteDto.getAnswer(), survey
        ).orElseThrow(InvalidVoteException::new);

        return VoteAdapter.toVoteDto(voteRepository.save(VoteAdapter.toVote(voteDto, survey, answer)));
    }

    @Override
    public void sendSurveyToWebsocket(final SurveyDto surveyDto) {
        restTemplate.postForObject(webSocketServiceUrl, surveyDto, null);
    }
}
