package noemipusceddu.U2W3L5be.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){
        super("Elemento con id " + id + "non è stato trovato");
    }

    public NotFoundException(String message){
        super(message);
    }
}
