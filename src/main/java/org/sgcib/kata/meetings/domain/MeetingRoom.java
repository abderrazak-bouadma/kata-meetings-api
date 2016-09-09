package org.sgcib.kata.meetings.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class MeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long roomId;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private int capacity;

    public MeetingRoom() { //thx JPA :D
    }

    public MeetingRoom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

}
