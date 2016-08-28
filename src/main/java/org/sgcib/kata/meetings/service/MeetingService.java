package org.sgcib.kata.meetings.service;

import org.sgcib.kata.meetings.domain.*;
import org.sgcib.kata.meetings.representation.MeetingDto;
import org.sgcib.kata.meetings.representation.MeetingDtoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final UserRepository userRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, MeetingRoomRepository meetingRoomRepository, UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.meetingRoomRepository = meetingRoomRepository;
        this.userRepository = userRepository;
    }

    // TODO useless method unless it carries search criteria
    public List<MeetingDto> returnAllMeetings() {
        List<MeetingDto> meetings = new ArrayList<>();
        Iterable<Meeting> allMeetings = meetingRepository.findAll();
        for (Meeting e : allMeetings) {
            meetings.add(MeetingDtoBuilder.newInstance()
                    .withDate(e.getReservationDate().toInstant())
                    .withDuration(e.getDuration())
                    .withId(e.getMeetingId())
                    .withMainGuest(e.getMainGuest().getEmail())
                    .withRoomName(e.getMeetingRoom().getName())
                    .build());
        }
        return meetings;
    }


    /**
     * @param meetingId
     * @return <code>true</code> if meeting has been correctly deleted
     */
    public boolean delete(Long meetingId) {
        Meeting meeting = meetingRepository.findOne(meetingId);
        if (meeting == null) {
            throw new EntityNotFoundException("Meeting with id= " + meetingId + " doesn't exist");
        }
        meetingRepository.delete(meeting);
        meeting = meetingRepository.findOne(meetingId);
        return meeting == null;
    }

    /**
     * <p>tries to create a meeting</p>
     *
     * @param userEmail
     * @param roomId
     * @param date
     * @param duration
     * @return
     */
    public MeetingDto create(String userEmail, Long roomId, Date date, Integer duration) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null)
            throw new UserNotFoundException("User with email " + userEmail + " not found");
        MeetingRoom room = meetingRoomRepository.findOne(roomId);
        if (room == null)
            throw new RoomNotExistException("Room with id " + roomId + " Doesn't exist");
        if (noMeetingConflict(room, date, duration)) {
            Meeting meeting = meetingRepository.save(new Meeting(user, room, date, duration));
            return MeetingDtoBuilder.newInstance()
                    .withDate(meeting.getReservationDate().toInstant())
                    .withDuration(meeting.getDuration())
                    .withId(meeting.getMeetingId())
                    .withMainGuest(meeting.getMainGuest().getEmail())
                    .withRoomName(meeting.getMeetingRoom().getName())
                    .build();
        } else {
            // return all available time slots for indicated date/time
            return null;
        }
    }

    private boolean noMeetingConflict(MeetingRoom meetingRoom, Date date, Integer duration) {
        Meeting meeting = meetingRepository.findByMeetingRoomAndReservationDateAndDuration(meetingRoom, date, duration);
        return meeting == null;
    }
}
