package org.sgcib.kata.meetings.representation;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

public class MeetingDto {


    private Long id;
    @JsonIgnore
    private Instant instant;
    private String date;
    private String roomName;
    private Integer duration;
    private String mainGuest;

    public MeetingDto(Long id, Instant instant, String roomName, Integer duration, String mainGuest) {
        this.id = id;
        this.instant = instant;
        this.roomName = roomName;
        this.duration = duration;
        this.mainGuest = mainGuest;
        this.date = instant.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeetingDto that = (MeetingDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (instant != null ? !instant.equals(that.instant) : that.instant != null) return false;
        if (roomName != null ? !roomName.equals(that.roomName) : that.roomName != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        return mainGuest != null ? mainGuest.equals(that.mainGuest) : that.mainGuest == null;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getMainGuest() {
        return mainGuest;
    }

    public void setMainGuest(String mainGuest) {
        this.mainGuest = mainGuest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (instant != null ? instant.hashCode() : 0);
        result = 31 * result + (roomName != null ? roomName.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (mainGuest != null ? mainGuest.hashCode() : 0);
        return result;
    }
}
