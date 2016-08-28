package org.sgcib.kata.meetings.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;
    private String name;
    private int capacity;

    public MeetingRoom() { //thx JPA :D
    }

    public MeetingRoom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
