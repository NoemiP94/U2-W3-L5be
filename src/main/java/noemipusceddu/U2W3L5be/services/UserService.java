package noemipusceddu.U2W3L5be.services;

import noemipusceddu.U2W3L5be.entities.User;
import noemipusceddu.U2W3L5be.exceptions.NotFoundException;
import noemipusceddu.U2W3L5be.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User findById(UUID id){
        return userDAO.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public Page<User> findAllUsers(int page, int size, String orderBy){
        if(size >= 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return userDAO.findAll(pageable);
    }


}
