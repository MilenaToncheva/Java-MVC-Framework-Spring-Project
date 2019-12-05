package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.artgallery.constants.artworksMessages.ArtworkErrorMessages;
import softuni.artgallery.error.ArtworkAlreadyAddedInCartException;
import softuni.artgallery.error.ArtworkNotFoundException;
import softuni.artgallery.services.models.ArtworkCartServiceModel;
import softuni.artgallery.services.services.ArtworkService;
import softuni.artgallery.services.services.CartService;
import softuni.artgallery.web.models.artwork.ArtworkCartViewModel;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final ArtworkService artworkService;
    private final ModelMapper modelMapper;

    @Autowired
    public CartServiceImpl(ArtworkService artworkService, ModelMapper modelMapper) {
        this.artworkService = artworkService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addArtworkToCart(HttpSession session, String artworkId) {
        List<ArtworkCartServiceModel> cart = this.retrieveCartContent(session);
        ArtworkCartServiceModel artwork = null;
        try {
            artwork = this.modelMapper.map(this.artworkService.findById(artworkId), ArtworkCartServiceModel.class);
        } catch (Exception e) {
            throw new ArtworkNotFoundException(ArtworkErrorMessages.ARTWORK_WITH_ID_NOT_FOUND);
        }
        if (cart.stream().filter(a -> a.getId().equals(artworkId)).collect(Collectors.toList()).size() != 0) {
           throw new ArtworkAlreadyAddedInCartException(ArtworkErrorMessages.ARTWORK_ALREADY_ADDED_TO_CART);
        } else {
            cart.add(artwork);
            session.setAttribute("cart", cart);
            session.setAttribute("totalPrice",this.calculateTotalPrice(cart));
        }

    }

    @Override
    public void removeArtworkFromCart(HttpSession session, String artworkId) {
        List<ArtworkCartServiceModel>cart=this.retrieveCartContent(session);
      ArtworkCartServiceModel artworkToRemove=  cart.stream().filter(a->a.getId().equals(artworkId))
              .collect(Collectors.toList()).get(0);
      cart.remove(artworkToRemove);
      session.setAttribute("cart",cart);
      session.setAttribute("totalPrice",this.calculateTotalPrice(cart));
    }

    @Override
    public List<ArtworkCartServiceModel> retrieveCartContent(HttpSession session) {
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new ArrayList<>());
        }
        return (List<ArtworkCartServiceModel>) session.getAttribute("cart");
    }

    @Override
    public List<ArtworkCartServiceModel> cartCheckout(HttpSession session) {
List<ArtworkCartServiceModel>cart=this.retrieveCartContent(session);
session.setAttribute("cart",new ArrayList<ArtworkCartServiceModel>()); //empty cart
session.setAttribute("totalPrice",new BigDecimal("0")); //make totalPrice 0;
return cart;
    }

    private BigDecimal calculateTotalPrice(List<ArtworkCartServiceModel> cart) {
        BigDecimal totalPrice=new BigDecimal(0);
        for (ArtworkCartServiceModel artwork : cart) {
            totalPrice=totalPrice.add(artwork.getPrice());
        }
        return totalPrice;
    }



}
