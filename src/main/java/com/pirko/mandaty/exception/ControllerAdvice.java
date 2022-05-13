package com.pirko.mandaty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleEntityNotFoundException(EntityNotFoundException exception) {
        return ErrorResponseDto.fromException(exception);
    }

    @ExceptionHandler(TooManyPointsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleTooManyPointsException(TooManyPointsException exception) {
        return ErrorResponseDto.fromException(exception);
    }

    @ExceptionHandler(PersonExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handlePersonExistException(PersonExistException exception) {
        return ErrorResponseDto.fromException(exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<Object, String> handleValidationExceptions(ConstraintViolationException ex) {
        Map<Object, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(err -> errors.put(err.getInvalidValue(), err.getMessage()));
        return errors;
    }
}
