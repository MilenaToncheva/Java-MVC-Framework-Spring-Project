package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.artgallery.constants.OrderMessages.OrderErrorMessages;
import softuni.artgallery.data.models.Artwork;
import softuni.artgallery.data.models.Order;
import softuni.artgallery.data.models.User;
import softuni.artgallery.data.repository.OrderRepository;
import softuni.artgallery.error.OrderNotDeletedException;
import softuni.artgallery.error.OrderNotFoundException;
import softuni.artgallery.services.models.ArtworkCartServiceModel;
import softuni.artgallery.services.models.ArtworkServiceModel;
import softuni.artgallery.services.models.OrderServiceModel;
import softuni.artgallery.services.services.ArtworkService;
import softuni.artgallery.services.services.OrderService;
import softuni.artgallery.services.services.UserService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;
    private final ArtworkService artworkService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,  UserService userService,
                            ArtworkService artworkService, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.artworkService = artworkService;
        this.modelMapper = modelMapper;
    }


    @Override
        public void generateOrder(List<ArtworkCartServiceModel> cart, Principal principal) throws Exception {
        List<ArtworkServiceModel>artworkServiceModels=this.generateOrderedList(cart);
        Order order =new Order();


        User user = this.modelMapper.map(this.userService.findByUsername(principal.getName()), User.class);
        order.setUser(user);
        order.setPlacedOn(LocalDateTime.now());
        BigDecimal totalPrice =artworkServiceModels.stream().map(a -> a.getPrice())
                .reduce(new BigDecimal("0"), (p1, p2) -> p1.add(p2));
        order.setTotalPrice(totalPrice);
        order=   this.orderRepository.saveAndFlush(order);

List<Artwork>artworks=Arrays.stream(this.modelMapper.map(artworkServiceModels, Artwork[].class))
        .collect(Collectors.toList());
        for (Artwork artwork : artworks) {
            artwork.setOrder(order);

        }
        order.setArtworks(artworks);


        this.orderRepository.saveAndFlush(order);

    }


    public List<ArtworkServiceModel> generateOrderedList(List<ArtworkCartServiceModel> cart) throws Exception {
        List<ArtworkServiceModel> orderedList = new ArrayList<>();
        for (ArtworkCartServiceModel artworkCartServiceModel : cart) {
            this.artworkService.writeOffArtwork(artworkCartServiceModel.getId());
            ArtworkServiceModel artworkServiceModel = this.artworkService.findById(artworkCartServiceModel.getId());
           orderedList.add(artworkServiceModel);
        }
        return orderedList;
    }

    @Override
    public List<OrderServiceModel> findAll() {
        List<Order> orders = this.orderRepository.findAll();
        return Arrays.stream(this.modelMapper.map(orders, OrderServiceModel[].class)).collect(Collectors.toList());
    }

    @Override
    public List<OrderServiceModel> findAllByUsername(String username) {
        List<Order> orders = this.orderRepository.findAllByUsername(username);
        return Arrays.stream(this.modelMapper.map(orders, OrderServiceModel[].class)).collect(Collectors.toList());
    }

    @Override
    public OrderServiceModel findById(String id) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(OrderErrorMessages.ORDER_NOT_FOUND));
        return this.modelMapper.map(order, OrderServiceModel.class);
    }

   @Override
   public void delete(String id) throws OrderNotDeletedException {
       Order order = this.orderRepository.findById(id)
               .orElseThrow(() -> new OrderNotFoundException(OrderErrorMessages.ORDER_NOT_FOUND));
       try {
           this.orderRepository.delete(order);
       } catch (Exception e) {

           e.printStackTrace();
           throw new OrderNotDeletedException(OrderErrorMessages.ORDER_NOT_DELETED);

       }
   }

}
