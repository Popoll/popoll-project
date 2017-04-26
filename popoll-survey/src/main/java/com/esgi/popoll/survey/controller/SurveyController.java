package com.esgi.popoll.survey.controller;

import com.esgi.popoll.survey.entity.survey.SurveyAdapter;
import com.esgi.popoll.survey.entity.survey.SurveyDto;
import com.esgi.popoll.survey.entity.vote.VoteDto;
import com.esgi.popoll.survey.exception.InvalidSurveyException;
import com.esgi.popoll.survey.exception.InvalidVoteException;
import com.esgi.popoll.survey.service.SurveyService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(final SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public SurveyDto createSurvey(@Valid @RequestBody final SurveyDto surveyDto,
                                  final BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            throw new InvalidSurveyException();

        return surveyService.createSurvey(surveyDto);
    }

    @PostMapping("{id}/vote")
    @ResponseStatus(CREATED)
    public VoteDto addVote(@PathVariable final Long id, @Valid @RequestBody final VoteDto voteDto,
                           final BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            throw new InvalidVoteException();

        return surveyService.addVoteInSurvey(id, voteDto);
    }

    @GetMapping("{id}")
    public SurveyDto getSurveyById(@PathVariable final Long id) {
        return SurveyAdapter.toSurveyDto(surveyService.getSurveyById(id));
    }
}
