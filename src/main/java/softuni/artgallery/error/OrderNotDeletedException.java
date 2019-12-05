package softuni.artgallery.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.CONFLICT,reason = "Order not deleted")
public class OrderNotDeletedException extends RuntimeException {
    public OrderNotDeletedException() {
    }

    public OrderNotDeletedException(String message) {
        super(message);
    }
}