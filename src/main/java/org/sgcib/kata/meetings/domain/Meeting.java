package org.sgcib.kata.meetings.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long meetingId;
    @ManyToOne
    private User mainGuest;
    @ManyToOne
    private MeetingRoom meetingRoom;
    @NotNull
    @Future
    private Date reservationDate;
    @NotNull
    @Range(max = 23L)
    private Integer duration;


    public Meeting() {
    }

    public Meeting(User mainGuest, MeetingRoom meetingRoom, Date reservationDate, int duration) {
        this.mainGuest = mainGuest;
        this.meetingRoom = meetingRoom;
        this.reservationDate = reservationDate;
        this.duration = duration;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public User getMainGuest() {
        return mainGuest;
    }

    public void setMainGuest(User mainGuest) {
        this.mainGuest = mainGuest;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
