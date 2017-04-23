package com.esgi.popoll.survey.service;

import com.esgi.popoll.survey.exception.NotFoundSurveyException;
import com.esgi.popoll.survey.repository.SurveyRepository;
import com.esgi.popoll.survey.repository.VoteRepository;
import com.esgi.popoll.survey.survey.SurveyAdapter;
import com.esgi.popoll.survey.survey.SurveyDto;
import com.esgi.popoll.survey.vote.VoteAdapter;
import com.esgi.popoll.survey.vote.VoteDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyAdapter surveyAdapter;
    private final VoteAdapter voteAdapter;
    private final VoteRepository voteRepository;

    public SurveyServiceImpl(final SurveyRepository surveyRepository, final SurveyAdapter surveyAdapter,
                             final VoteAdapter voteAdapter, final VoteRepository voteRepository)
    {
        this.surveyRepository = surveyRepository;
        this.surveyAdapter = surveyAdapter;
        this.voteAdapter = voteAdapter;
        this.voteRepository = voteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public SurveyDto getSurveyById(final Long id) {
        return surveyAdapter.toSurveyDto(
            surveyRepository.findById(id).orElseThrow(NotFoundSurveyException::new)
        );
    }

    @Override
    public SurveyDto createSurvey(final SurveyDto dto) {
        return surveyAdapter.toSurveyDto(surveyRepository.save(surveyAdapter.toSurvey(dto)));
    }

    @Override
    public VoteDto addVoteInSurvey(final Long id, final VoteDto voteDto) {

        final SurveyDto surveyDto = getSurveyById(id);
        voteDto.setSurveyId(surveyDto.getId());
        return voteAdapter.toVoteDto(voteRepository.save(voteAdapter.toVote(voteDto)));
    }
}
