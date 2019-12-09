package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.services.models.ArtworkCartServiceModel;
import softuni.artgallery.services.services.CartService;
import softuni.artgallery.services.services.OrderService;
import softuni.artgallery.web.models.artwork.ArtworkCartViewModel;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public CartController(CartService cartService, OrderService orderService, ModelMapper modelMapper) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add-artwork/{artworkId}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView addArtworkToCart(@PathVariable String  artworkId, HttpSession httpSession) {
        this.cartService.addArtworkToCart(httpSession, artworkId);

        ModelAndView modelAndView = new ModelAndView("redirect:/home");



        return modelAndView;

    }

    @PostMapping("/remove-artwork/{artworkId}")
    @PreAuthorize("isAuthenticated()")
    public String removeArtworkFromCart(@PathVariable String artworkId, HttpSession httpSession) {
        this.cartService.removeArtworkFromCart(httpSession, artworkId);
        return "redirect:/cart/details";
    }

    @GetMapping("/details")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getCart( HttpSession session, ModelAndView modelAndView) {
     List<ArtworkCartViewModel> cart = Arrays.stream(this.modelMapper.map(this.cartService.retrieveCartContent(session),
            ArtworkCartViewModel[].class)).collect(Collectors.toList());
    BigDecimal totalCartPrice = (BigDecimal) session.getAttribute("totalPrice");
    modelAndView.addObject("totalPrice", totalCartPrice);
        modelAndView.setViewName("cart/cart-details");
        return modelAndView;
    }

    @PostMapping("/checkout")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView checkOUtCart(HttpSession httpSession, ModelAndView modelAndView,Principal principal) throws Exception {
        List<ArtworkCartServiceModel> cart = this.cartService.cartCheckout(httpSession);
        this.orderService.generateOrder(cart,principal);
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }


}
