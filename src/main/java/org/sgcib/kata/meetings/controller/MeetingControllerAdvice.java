package org.sgcib.kata.meetings.controller;

import org.sgcib.kata.meetings.service.EntityNotFoundException;
import org.sgcib.kata.meetings.service.RoomNotExistException;
import org.sgcib.kata.meetings.service.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MeetingControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleEntityNotFoundException() {
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GenericApiException.class)
    public void handleGenericApiException() {
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(UserNotFoundException.class)
    public void handleUserNotFoundException() {
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(RoomNotExistException.class)
    public void handleRoomNotExistException() {
    }
}
