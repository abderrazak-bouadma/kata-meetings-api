package org.sgcib.kata.meetings.controller;

import org.sgcib.kata.meetings.representation.ErrorInfo;
import org.sgcib.kata.meetings.service.EntityNotFoundException;
import org.sgcib.kata.meetings.service.RoomNotExistException;
import org.sgcib.kata.meetings.service.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MeetingControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorInfo handleEntityNotFoundException() {
        return new ErrorInfo("0001","Entity not found","You tried to access to a resource but seems not existing or maybe deleted");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(GenericApiException.class)
    public ErrorInfo handleGenericApiException() {
        return new ErrorInfo("0005","Server Error","It looks that something unpredictable happened !");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorInfo handleUserNotFoundException() {
        return new ErrorInfo("0003","Server Error","User not found !");
    }

    @ResponseBody
    @ExceptionHandler(RoomNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInfo handleRoomNotExistException() {
        return new ErrorInfo("0002","Room not found","You tried to access to a room details but seems not existing or maybe deleted");
    }
}
