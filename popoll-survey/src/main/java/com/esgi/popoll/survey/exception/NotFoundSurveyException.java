package com.esgi.popoll.survey.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Created by Julien on 23/04/2017.
 */

@ResponseStatus(NOT_FOUND)
public class NotFoundSurveyException extends RuntimeException {}
