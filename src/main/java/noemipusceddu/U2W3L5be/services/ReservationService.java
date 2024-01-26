package noemipusceddu.U2W3L5be.services;

import noemipusceddu.U2W3L5be.entities.Event;
import noemipusceddu.U2W3L5be.entities.Reservation;
import noemipusceddu.U2W3L5be.entities.User;
import noemipusceddu.U2W3L5be.exceptions.NotFoundException;
import noemipusceddu.U2W3L5be.exceptions.UnavailableSeatsException;
import noemipusceddu.U2W3L5be.payloads.reservation.ReservationDTO;
import noemipusceddu.U2W3L5be.repositories.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ReservationService {
    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    public Reservation createReservation(ReservationDTO body){
        User user = userService.findById(body.idUser());
        Event event = eventService.findById(body.idEvent());

        if(event.getAvailableSeats() <= 0){
            throw new UnavailableSeatsException("Non ci sono posti disponibili per questo evento.");
        }else{
            event.setAvailableSeats(event.getAvailableSeats() - 1);
            eventService.updateEvent(event);

            Reservation reservation = new Reservation();
            reservation.setDateOfReservation(LocalDate.now());
            reservation.setCommonUser(user);
            reservation.setEvent(event);
            return reservationDAO.save(reservation);
        }
    }

    public Page<Reservation> findAllReservations(int page, int size, String orderBy){
        if(size >= 25) size = 25;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return reservationDAO.findAll(pageable);
    }

    public Reservation findById(UUID id){
        return reservationDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Reservation findByIdAndUpdate(UUID id, ReservationDTO body){
        Reservation found = this.findById(id);
        Event event = eventService.findById(id);
        User newUser = userService.findById(body.idUser());
        found.setCommonUser(newUser);
        found.setEvent(event);
        return reservationDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Reservation found = this.findById(id);
        reservationDAO.delete(found);
    }






}
