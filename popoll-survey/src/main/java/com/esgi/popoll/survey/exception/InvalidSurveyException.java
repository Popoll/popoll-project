package com.esgi.popoll.survey.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Julien on 23/04/2017.
 */
@ResponseStatus(BAD_REQUEST)
public class InvalidSurveyException extends RuntimeException{}
