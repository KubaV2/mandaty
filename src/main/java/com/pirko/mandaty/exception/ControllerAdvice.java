package com.pirko.mandaty.exception;

import com.mysql.cj.exceptions.WrongArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleEntityNotFoundException(EntityNotFoundException exception) {
        return ErrorResponseDto.fromException(exception);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<String>  handleBindException(BindException exception) {
        List<String> errors = new ArrayList<>();
        exception.getFieldErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        return errors;
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleUnexpectedTypeException(UnexpectedTypeException exception) {
        return ErrorResponseDto.fromException(exception);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleNumberFormatException(NumberFormatException exception) {
        return exception.getLocalizedMessage();
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
    public Map<Object, String> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<Object, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(err -> errors.put(err.getInvalidValue(), err.getMessage()));
        return errors;
    }
}
