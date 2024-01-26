package noemipusceddu.U2W3L5be.exceptions;

import noemipusceddu.U2W3L5be.payloads.ExceptionsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // --> Error 404
    public ExceptionsDTO notFoundHandle(NotFoundException e){
        e.printStackTrace();
        return new ExceptionsDTO(e.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // --> Error 401
    public ExceptionsDTO unauthorizedHandle(UnauthorizedException e){
        return new ExceptionsDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // --> Error 400
    public ExceptionsDTO badRequestHandle(BadRequestException e){
        return new ExceptionsDTO(e.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)// --> Error 403
    public ExceptionsDTO forbiddenHandle(Exception e){
        return new ExceptionsDTO("Impossibile accedere a questa funzionalità! Cambia ruolo", LocalDateTime.now());
    }

    @ExceptionHandler(UnavailableSeatsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // --> Error 400
    public ExceptionsDTO unavailableSeatsHandle(Exception e){
        return new ExceptionsDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // --> Error 500
    public ExceptionsDTO genericExHandle(Exception e){
        e.printStackTrace();
        return new ExceptionsDTO("Errore nel server! Contattare il backend!", LocalDateTime.now());
    }
}
