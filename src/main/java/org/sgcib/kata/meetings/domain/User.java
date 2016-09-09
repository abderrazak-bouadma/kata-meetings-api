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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long userId;
    @Getter
    @Setter
    private String email;

    public User() {

    }

    public User(String email) {
        this.email = email;
    }
}
