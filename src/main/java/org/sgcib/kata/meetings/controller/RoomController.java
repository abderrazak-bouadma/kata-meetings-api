package org.sgcib.kata.meetings.controller;

import org.sgcib.kata.meetings.domain.MeetingRoom;
import org.sgcib.kata.meetings.domain.MeetingRoomRepository;
import org.sgcib.kata.meetings.service.RoomNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rooms", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController {

    private final MeetingRoomRepository meetingRoomRepository;

    @Autowired
    public RoomController(MeetingRoomRepository meetingRoomRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
    }

    @RequestMapping
    @Cacheable("meetingRooms")
    public List<MeetingRoom> listAllRooms() {
        return (List<MeetingRoom>) meetingRoomRepository.findAll();
    }

    @RequestMapping(value = "/{roomId}")
    public ResponseEntity<MeetingRoom> findRoom(@PathVariable("roomId") Long roomId) {
        MeetingRoom meetingRoom = meetingRoomRepository.findOne(roomId);
        if (meetingRoom==null) {
            throw new RoomNotExistException("Room not found");
        }
        return new ResponseEntity<>(meetingRoom, HttpStatus.OK);
    }
}
