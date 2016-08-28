package org.sgcib.kata.meetings.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRoomRepository extends CrudRepository<MeetingRoom, Long> {
}
