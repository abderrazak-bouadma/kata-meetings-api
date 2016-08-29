package org.sgcib.kata.meetings.controller;

public class GenericApiException extends RuntimeException {
    public GenericApiException(String message) {
        super(message);
    }
}
