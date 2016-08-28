package org.sgcib.kata.meetings.service;

public class RoomNotExistException extends RuntimeException {
    public RoomNotExistException(String message) {
        super(message);
    }
}
