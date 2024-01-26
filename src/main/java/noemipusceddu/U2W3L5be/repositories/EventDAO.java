package noemipusceddu.U2W3L5be.repositories;

import noemipusceddu.U2W3L5be.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventDAO extends JpaRepository<Event, UUID> {
}
