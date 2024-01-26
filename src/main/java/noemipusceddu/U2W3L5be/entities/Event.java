package noemipusceddu.U2W3L5be.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "events")
@Getter
@Setter
@JsonIgnoreProperties({"reservationList"})
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

    @OneToMany(mappedBy = "event")
    private List<Reservation> reservationList;

    @ManyToOne
    @JoinColumn(name = "admin")
    private User admin;

}
