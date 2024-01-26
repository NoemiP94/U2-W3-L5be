package noemipusceddu.U2W3L5be.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name= "events")
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    private LocalDate date;
    private String place;
    private int availableSeats;
    private String image;
}
