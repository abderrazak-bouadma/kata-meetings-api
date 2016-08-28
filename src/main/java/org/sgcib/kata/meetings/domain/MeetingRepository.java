package org.sgcib.kata.meetings.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Long> {
    Meeting findByMeetingRoomAndReservationDateAndDuration(MeetingRoom meetingRoom, Date reservationDate, int Duration);

    List<Meeting> findByMeetingRoom(MeetingRoom meetingRoom);
}
