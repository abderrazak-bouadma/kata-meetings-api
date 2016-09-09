package org.sgcib.kata.meetings.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long meetingId;
    @ManyToOne
    @Getter @Setter
    private User mainGuest;
    @ManyToOne
    @Getter @Setter
    private MeetingRoom meetingRoom;
    @NotNull
    @Future
    @Getter @Setter
    private Date reservationDate;
    @NotNull
    @Range(max = 23L)
    @Getter @Setter
    private Integer duration;


    public Meeting() {
    }

    public Meeting(User mainGuest, MeetingRoom meetingRoom, Date reservationDate, int duration) {
        this.mainGuest = mainGuest;
        this.meetingRoom = meetingRoom;
        this.reservationDate = reservationDate;
        this.duration = duration;
    }

}
