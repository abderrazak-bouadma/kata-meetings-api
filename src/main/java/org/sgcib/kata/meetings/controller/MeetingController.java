package org.sgcib.kata.meetings.controller;

import org.sgcib.kata.meetings.domain.MeetingRoomRepository;
import org.sgcib.kata.meetings.representation.MeetingDto;
import org.sgcib.kata.meetings.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/meetings", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MeetingController {

    private final MeetingService meetingService;
    private final MeetingRoomRepository meetingRoomRepository;

    @Autowired
    public MeetingController(MeetingService meetingService, MeetingRoomRepository meetingRoomRepository) {
        this.meetingService = meetingService;
        this.meetingRoomRepository = meetingRoomRepository;
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<MeetingDto> findAllMeetings() {
        return meetingService.returnAllMeetings();
    }

    @RequestMapping(value = "/{meetingId}", method = RequestMethod.DELETE)
    public void deleteMeeting(@PathVariable("meetingId") Long meetingId) {
        if (!meetingService.delete(meetingId)) {
            throw new GenericApiException("deleting meeting with id " + meetingId + " doesn't succeed");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestParam("email") String email, @RequestParam("roomId") Long roomId, @RequestParam("date") String date, @RequestParam("duration") Integer duration) {
        LocalDateTime ldt = ZonedDateTime.parse(date).toLocalDateTime();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        Date from = Date.from(zdt.toInstant());
        MeetingDto meetingDto = meetingService.create(email, roomId, from, duration);
        if (meetingDto != null) {
            return new ResponseEntity<>(meetingDto, HttpStatus.CREATED);
        } else {
            List<Integer> availabeTimeSlots = meetingService.findAvailableTimeSlot(roomId, from, duration);
            return new ResponseEntity<>(availabeTimeSlots, HttpStatus.CONFLICT);
        }
    }
}
