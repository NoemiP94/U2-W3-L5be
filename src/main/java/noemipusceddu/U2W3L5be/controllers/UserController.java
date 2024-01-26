package noemipusceddu.U2W3L5be.controllers;

import noemipusceddu.U2W3L5be.entities.User;
import noemipusceddu.U2W3L5be.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findUserById(@PathVariable UUID id){
        return userService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> findUsers(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "name") String orderBy){
        return userService.findAllUsers(page, size, orderBy);
    }
}
