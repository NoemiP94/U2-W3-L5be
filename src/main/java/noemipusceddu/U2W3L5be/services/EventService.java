package noemipusceddu.U2W3L5be.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import noemipusceddu.U2W3L5be.entities.Event;
import noemipusceddu.U2W3L5be.entities.User;
import noemipusceddu.U2W3L5be.exceptions.NotFoundException;
import noemipusceddu.U2W3L5be.payloads.event.EventDTO;
import noemipusceddu.U2W3L5be.repositories.EventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;
@Service
public class EventService {
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private UserService userService;

    @Autowired
    private Cloudinary imagesUploader;

    public Event createEvent(EventDTO body){
        User user = userService.findById(body.idAdmin());
        Event event = new Event();
        event.setTitle(body.title());
        event.setDescription(body.description());
        event.setDate(LocalDate.now());
        event.setPlace(body.place());
        event.setAvailableSeats(body.availableSeats());
        event.setImage(body.image());
        event.setAdmin(user);
        return eventDAO.save(event);
    }

    public Page<Event> findAllEvents(int page, int size, String orderBy){
        if(size >= 25) size = 25;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return eventDAO.findAll(pageable);
    }

    public Event findById(UUID id){
        return eventDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Event findByIdAndUpdate(UUID id, EventDTO body){
        Event found = this.findById(id);
        User newAdmin = userService.findById(body.idAdmin());
        found.setTitle(body.title());
        found.setDescription(body.description());
        found.setPlace(body.place());
        found.setAvailableSeats(body.availableSeats());
        found.setImage(body.image());
        found.setAdmin(newAdmin);
        return eventDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Event found = this.findById(id);
        eventDAO.delete(found);
    }

    public String uploadImage(MultipartFile file) throws IOException{
        String url = (String) imagesUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;
    }

    public void updateEvent(Event event){
        eventDAO.save(event);
    }


}
