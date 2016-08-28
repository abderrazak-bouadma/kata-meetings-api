package org.sgcib.kata.meetings;

import org.sgcib.kata.meetings.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@SpringBootApplication
@EnableCaching
public class SgcibKataMeetingApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRepository meetingRepository;

    @Autowired
    public SgcibKataMeetingApplication(UserRepository userRepository, MeetingRoomRepository meetingRoomRepository, MeetingRepository meetingRepository) {
        this.userRepository = userRepository;
        this.meetingRoomRepository = meetingRoomRepository;
        this.meetingRepository = meetingRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SgcibKataMeetingApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        // some users
        User mario = userRepository.save(new User("mario@apiland.com"));
        User yoshi = userRepository.save(new User("yoshi@apiland.com"));
        User luigi = userRepository.save(new User("luigi@apiland.com"));

        // some meeting rooms
        MeetingRoom pacMan = meetingRoomRepository.save(new MeetingRoom("PacMan", 4));
        meetingRoomRepository.save(new MeetingRoom("Donkey Kong", 7));
        MeetingRoom marioBros = meetingRoomRepository.save(new MeetingRoom("Mario Bros.", 8));
        meetingRoomRepository.save(new MeetingRoom("Super Mario Bros.", 12));
        meetingRoomRepository.save(new MeetingRoom("Tetris", 12));
        meetingRoomRepository.save(new MeetingRoom("Space Invaders", 18));
        meetingRoomRepository.save(new MeetingRoom("Galaga", 2));
        MeetingRoom zelda = meetingRoomRepository.save(new MeetingRoom("Zelda", 4));
        meetingRoomRepository.save(new MeetingRoom("Pole Position", 4));
        meetingRoomRepository.save(new MeetingRoom("Paper Boy", 6));

        // some meetings
        LocalDateTime ldt = LocalDateTime.of(2016, Month.AUGUST, 31, 14, 0,0,0);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        Date aDay = Date.from(zdt.toInstant());
        meetingRepository.save(new Meeting(mario, marioBros, aDay, 2));

        ldt = LocalDateTime.of(2016, Month.SEPTEMBER, 6, 14, 0);
        zdt = ldt.atZone(ZoneId.of("UTC"));
        aDay = Date.from(zdt.toInstant());
        meetingRepository.save(new Meeting(luigi, pacMan, aDay, 2));
    }
}
