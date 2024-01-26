package noemipusceddu.U2W3L5be.controllers;

import noemipusceddu.U2W3L5be.entities.User;
import noemipusceddu.U2W3L5be.exceptions.BadRequestException;
import noemipusceddu.U2W3L5be.payloads.login.LoginDTO;
import noemipusceddu.U2W3L5be.payloads.login.LoginResponseDTO;
import noemipusceddu.U2W3L5be.payloads.user.UserDTO;
import noemipusceddu.U2W3L5be.payloads.user.UserResponseDTO;
import noemipusceddu.U2W3L5be.services.AuthService;
import noemipusceddu.U2W3L5be.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO createUser(@RequestBody @Validated UserDTO newUserBody, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException("Controlla il payload!");
        } else {
            User newUser = authService.saveUser(newUserBody);
            return new UserResponseDTO(newUser.getId());
        }
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body){
        String token = authService.authUser(body);
        return new LoginResponseDTO(token);
    }
}
