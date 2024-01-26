package noemipusceddu.U2W3L5be.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reservations")
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate dateOfReservation;


    @ManyToOne
    @JoinColumn(name = "commonUser")
    private User commonUser;

    @ManyToOne
    @JoinColumn(name= "event")
    private Event event;
}
