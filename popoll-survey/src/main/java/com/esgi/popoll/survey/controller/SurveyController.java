package com.esgi.popoll.survey.controller;


import com.esgi.popoll.survey.Service.SurveyService;
import com.esgi.popoll.survey.exception.InvalidSurveyException;
import com.esgi.popoll.survey.exception.InvalidVoteException;
import com.esgi.popoll.survey.survey.SurveyDto;
import com.esgi.popoll.survey.vote.VoteDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Julien on 23/04/2017.
 */
@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public SurveyDto createSurvey(@Valid @RequestBody SurveyDto surveyDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidSurveyException();
        }
        return surveyService.createSurvey(surveyDto);
    }

    @PostMapping("{id}/vote")
    public VoteDto addVote(@PathVariable Long id,@Valid @RequestBody VoteDto voteDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidVoteException();
        }

        return surveyService.addVoteInSurvey(id,voteDto);
    }

    @GetMapping("{id}")
    public SurveyDto getSurveyById(@PathVariable Long id){
    return surveyService.getSurveyById(id);

    }
}
