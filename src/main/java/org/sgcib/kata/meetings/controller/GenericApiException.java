package org.sgcib.kata.meetings.controller;

/**
 * Created by abderrazak on 8/28/16.
 */
public class GenericApiException extends RuntimeException {
    public GenericApiException(String message) {
        super(message);
    }
}
