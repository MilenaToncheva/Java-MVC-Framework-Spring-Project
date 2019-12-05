package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Artwork was not found!")
public class ArtworkNotFoundException extends RuntimeException  {

    public ArtworkNotFoundException(){}

    public ArtworkNotFoundException(String message) {
        super(message);
    }

}
