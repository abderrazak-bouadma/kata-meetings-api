package org.sgcib.kata.meetings.representation;

import java.time.Instant;

public class MeetingDtoBuilder {

    private Long meetingId;
    private Instant instant;
    private String roomName;
    private Integer duration;
    private String mainGuest;

    private MeetingDtoBuilder() {
    }

    public static MeetingDtoBuilder newInstance() {
        return new MeetingDtoBuilder();
    }

    public MeetingDtoBuilder withId(Long meetingId) {
        this.meetingId = meetingId;
        return this;
    }

    public MeetingDtoBuilder withDate(Instant instant) {
        this.instant = instant;
        return this;
    }

    public MeetingDtoBuilder withRoomName(String roomName) {
        this.roomName = roomName;
        return this;
    }

    public MeetingDtoBuilder withMainGuest(String mainGuest) {
        this.mainGuest = mainGuest;
        return this;
    }

    public MeetingDtoBuilder withDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public MeetingDto build() {
        return new MeetingDto(meetingId, instant, roomName, duration, mainGuest);
    }
}
