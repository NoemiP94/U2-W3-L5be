package noemipusceddu.U2W3L5be.repositories;

import noemipusceddu.U2W3L5be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
