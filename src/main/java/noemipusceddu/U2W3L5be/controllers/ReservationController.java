package noemipusceddu.U2W3L5be.controllers;

import noemipusceddu.U2W3L5be.entities.Reservation;
import noemipusceddu.U2W3L5be.exceptions.BadRequestException;
import noemipusceddu.U2W3L5be.payloads.reservation.ReservationDTO;
import noemipusceddu.U2W3L5be.payloads.reservation.ReservationResponseDTO;
import noemipusceddu.U2W3L5be.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO createReservation(@RequestBody @Validated ReservationDTO reservationBody, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException("Errore nel payload!");
        }else{
            Reservation reservation = reservationService.createReservation(reservationBody);
            return new ReservationResponseDTO(reservation.getId());
        }
    }

    @GetMapping
    public Page<Reservation> findAllReservations(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String orderBy){
        return reservationService.findAllReservations(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Reservation findReservationById(@PathVariable UUID id){
        return reservationService.findById(id);
    }

    @PutMapping("/{id}")
    public Reservation findReservationByIdAndUpdate(@PathVariable UUID id, @RequestBody ReservationDTO modifiedBody){
        return reservationService.findByIdAndUpdate(id, modifiedBody);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findReservationAndDelete(@PathVariable UUID id){
        reservationService.findByIdAndDelete(id);
    }

}
