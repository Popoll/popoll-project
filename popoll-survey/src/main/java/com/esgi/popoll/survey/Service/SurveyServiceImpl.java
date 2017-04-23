package com.esgi.popoll.survey.Service;

import com.esgi.popoll.survey.exception.NotFoundSurveyException;
import com.esgi.popoll.survey.repository.SurveyRepository;
import com.esgi.popoll.survey.repository.VoteRepository;
import com.esgi.popoll.survey.survey.SurveyAdapter;
import com.esgi.popoll.survey.survey.SurveyDto;
import com.esgi.popoll.survey.vote.VoteAdapter;
import com.esgi.popoll.survey.vote.VoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Julien on 23/04/2017.
 */
@Service
public class SurveyServiceImpl implements SurveyService{

    private final SurveyRepository surveyRepository;
    private final SurveyAdapter surveyAdapter;
    private final VoteAdapter voteAdapter;
    private final VoteRepository voteRepository;


    @Autowired
    public SurveyServiceImpl(SurveyRepository surveyRepository, SurveyAdapter surveyAdapter, VoteAdapter voteAdapter, VoteRepository voteRepository)
    {
        this.surveyRepository = surveyRepository;
        this.surveyAdapter = surveyAdapter;
        this.voteAdapter = voteAdapter;
        this.voteRepository = voteRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public SurveyDto getSurveyById(Long id) {
        return surveyAdapter.toSurveyDto(surveyRepository.findById(id).orElseThrow(NotFoundSurveyException::new));

    }


    @Override
    @Transactional
    public SurveyDto createSurvey(SurveyDto dto) {

        return surveyAdapter.toSurveyDto(surveyRepository.save(surveyAdapter.toSurvey(dto)));
    }

    @Override
    public VoteDto addVoteInSurvey(Long id, VoteDto voteDto) {
        final SurveyDto surveyDto = getSurveyById(id);
        voteDto.setSurveyId(surveyDto.getId());
        return voteAdapter.toVoteDto(voteRepository.save(voteAdapter.toVote(voteDto)));

    }



}
