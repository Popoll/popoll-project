package com.esgi.popoll.survey.service;

import com.esgi.popoll.survey.exception.NotFoundSurveyException;
import com.esgi.popoll.survey.repository.SurveyRepository;
import com.esgi.popoll.survey.repository.VoteRepository;
import com.esgi.popoll.survey.entity.survey.SurveyAdapter;
import com.esgi.popoll.survey.entity.survey.SurveyDto;
import com.esgi.popoll.survey.entity.vote.VoteAdapter;
import com.esgi.popoll.survey.entity.vote.VoteDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final VoteRepository voteRepository;
    private final RestTemplate restTemplate;

    @Value("{info.services.websocket}")
    private String webSocketServiceUrl;

    public SurveyServiceImpl(final SurveyRepository surveyRepository, final VoteRepository voteRepository,
                             final RestTemplate restTemplate)
    {
        this.surveyRepository = surveyRepository;
        this.voteRepository = voteRepository;
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
        surveyDto.setVotes(new ArrayList());
        return SurveyAdapter.toSurveyDto(surveyRepository.save(SurveyAdapter.toSurvey(surveyDto)));
    }

    @Override
    public VoteDto addVoteInSurvey(final Long id, final VoteDto voteDto) {
        getSurveyById(id);
        return VoteAdapter.toVoteDto(voteRepository.save(VoteAdapter.toVote(voteDto)));
    }

    @Override
    public void sendSurveyToWebsocket(final SurveyDto surveyDto) {
        restTemplate.postForObject(webSocketServiceUrl, surveyDto, null);
    }
}
