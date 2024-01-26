package noemipusceddu.U2W3L5be.services;

import noemipusceddu.U2W3L5be.entities.User;
import noemipusceddu.U2W3L5be.exceptions.BadRequestException;
import noemipusceddu.U2W3L5be.exceptions.UnauthorizedException;
import noemipusceddu.U2W3L5be.payloads.login.LoginDTO;
import noemipusceddu.U2W3L5be.payloads.user.UserDTO;
import noemipusceddu.U2W3L5be.repositories.UserDAO;
import noemipusceddu.U2W3L5be.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcryptpw;

    @Autowired
    private UserDAO userDAO;

    public String authUser(LoginDTO body){
        User user = userService.findByEmail(body.email());
        if (bcryptpw.matches(body.password(), user.getPassword())){
            return jwtTools.createToken(user);
        }else{
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public User saveUser(UserDTO body){
        userDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("Email " + user.getEmail() + " già in uso!");
        });

        User user = new User();
        user.setName(body.name());
        user.setSurname(body.surname());
        user.setEmail(body.email());
        user.setPassword(bcryptpw.encode(body.password()));
        user.setRole(body.role());
        return userDAO.save(user);
    }

}
