package noemipusceddu.U2W3L5be.controllers;

import noemipusceddu.U2W3L5be.entities.Event;
import noemipusceddu.U2W3L5be.exceptions.BadRequestException;
import noemipusceddu.U2W3L5be.payloads.event.EventDTO;
import noemipusceddu.U2W3L5be.payloads.event.EventResponseDTO;
import noemipusceddu.U2W3L5be.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public EventResponseDTO createEvent(@RequestBody @Validated EventDTO eventBody, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException("Errore nel payload!");
        }else{
            Event event = eventService.createEvent(eventBody);
            return new EventResponseDTO(event.getId());
        }
    }

    @GetMapping
    public Page<Event> findAllEvents(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String orderBy){
        return eventService.findAllEvents(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Event findEventById(@PathVariable UUID id){
        return eventService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event findEventByIdAndUpdate(@PathVariable UUID id, @RequestBody EventDTO modifiedBody){
        return eventService.findByIdAndUpdate(id, modifiedBody);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findEventAndDelete(@PathVariable UUID id){
        eventService.findByIdAndDelete(id);
    }

    @PostMapping("/{id}/upload")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadImage(@RequestParam("image") MultipartFile file, @PathVariable UUID id) throws IOException {
        return eventService.uploadImage(file);
    }


}
