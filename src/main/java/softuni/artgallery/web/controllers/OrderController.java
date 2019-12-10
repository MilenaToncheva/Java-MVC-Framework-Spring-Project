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
import softuni.artgallery.error.OrderNotDeletedException;
import softuni.artgallery.error.OrderNotFoundException;
import softuni.artgallery.services.models.OrderServiceModel;
import softuni.artgallery.services.models.UserServiceModel;
import softuni.artgallery.services.services.OrderService;
import softuni.artgallery.services.services.UserService;
import softuni.artgallery.web.models.order.OrderAllViewModel;
import softuni.artgallery.web.models.order.OrderDetailsViewModel;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper, UserService userService) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getAllOrders( ModelAndView modelAndView) {
        List<OrderAllViewModel> orders = this.orderService.findAll().stream().map(o -> {
            OrderAllViewModel orderAllViewModel = this.modelMapper.map(o, OrderAllViewModel.class);
            orderAllViewModel.setUsername(o.getUser().getUsername());
            return orderAllViewModel;
        }).collect(Collectors.toList());

        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("orders/orders-all");
        return modelAndView;
    }

    @GetMapping("/my-orders")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getMyOrders(Principal principal, ModelAndView modelAndView) {
        List<OrderAllViewModel> orders = this.orderService.findAllByUsername(principal.getName()).stream()
                .map(o -> {
                    OrderAllViewModel orderAllViewModel = this.modelMapper.map(o, OrderAllViewModel.class);
                    orderAllViewModel.setUsername(o.getUser().getUsername());
                    return orderAllViewModel;
                }).collect(Collectors.toList());

        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("orders/orders-all");
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView getOrderDetails(@PathVariable String id,  ModelAndView modelAndView) {
        OrderServiceModel orderServiceModel = this.orderService.findById(id);
        OrderDetailsViewModel order = this.modelMapper.map(orderServiceModel, OrderDetailsViewModel.class);
        order.setUsername(orderServiceModel.getUser().getUsername());
        order.setFirstName(orderServiceModel.getUser().getFirstName());
        order.setLastName(orderServiceModel.getUser().getLastName());
        modelAndView.setViewName("orders/order-details");
        modelAndView.addObject("order", order);
        return modelAndView;
    }

    @GetMapping("/my-orders/details/{orderId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getMyOrderDetails(@PathVariable String orderId, Principal principal,
                                          ModelAndView modelAndView) {
        OrderServiceModel orderServiceModel = this.orderService
                .findAllByUsername(principal.getName())
                .stream()
                .filter(o -> o.getId().equals(orderId))
                .collect(Collectors.toList())
                .get(0);
        OrderDetailsViewModel order = this.modelMapper.map(orderServiceModel, OrderDetailsViewModel.class);
        order.setUsername(principal.getName());


        order.setFirstName(orderServiceModel.getUser().getFirstName());
        order.setLastName(orderServiceModel.getUser().getLastName());
        modelAndView.addObject("order", order);
        modelAndView.setViewName("orders/order-details");
        return modelAndView;
    }

  //  @PostMapping("/delete/{id}")
  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
  //  public ModelAndView deleteArtwork( @PathVariable String id, ModelAndView modelAndView) throws OrderNotFoundException, OrderNotDeletedException {
//
  //      this.orderService.delete(id);
  //      modelAndView.setViewName("redirect:/orders/all");
  //      return modelAndView;
  //  }
}
