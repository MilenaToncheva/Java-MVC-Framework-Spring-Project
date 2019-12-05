package softuni.artgallery.services.services;

import softuni.artgallery.services.models.ArtworkCartServiceModel;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {

    void addArtworkToCart(HttpSession httpSession, String artworkId);

    void removeArtworkFromCart(HttpSession session, String artworkId);
    List<ArtworkCartServiceModel> retrieveCartContent(HttpSession session);

    List<ArtworkCartServiceModel>  cartCheckout(HttpSession httpSession);
}
