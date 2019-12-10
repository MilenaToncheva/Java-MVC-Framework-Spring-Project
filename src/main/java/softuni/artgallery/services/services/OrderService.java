package softuni.artgallery.services.services;

import softuni.artgallery.error.OrderNotDeletedException;
import softuni.artgallery.services.models.ArtworkCartServiceModel;
import softuni.artgallery.services.models.OrderCreateServiceModel;
import softuni.artgallery.services.models.OrderServiceModel;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    void generateOrder(List<ArtworkCartServiceModel> cart, Principal principal) throws Exception;

    List<OrderServiceModel> findAll();

    List<OrderServiceModel> findAllByUsername(String username);

    OrderServiceModel findById(String id);

 //   void delete(String id) throws OrderNotDeletedException;


}
