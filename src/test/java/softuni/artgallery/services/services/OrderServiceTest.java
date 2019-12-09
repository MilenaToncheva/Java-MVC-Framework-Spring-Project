package softuni.artgallery.services.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.artgallery.data.models.Artist;
import softuni.artgallery.data.models.Order;
import softuni.artgallery.data.models.User;
import softuni.artgallery.data.repository.OrderRepository;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.ArtistServiceModel;
import softuni.artgallery.services.models.OrderServiceModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTest extends ServiceTestBase {
    List<Order>orders;
    @MockBean
    OrderRepository orderRepository;
    @MockBean
    UserService userService;
    @MockBean
    ArtworkService artworkService;
    @Autowired
    OrderService orderService;

    @Override
    protected void beforeEach() {
        orders=new ArrayList<>();
    }

    @Test
    void findAll_whenExist_shouldReturnAllOrders(){
        Order order1=new Order();
        Order order2=new Order();

       orders.add(order1);
       orders.add(order2);
        Mockito.when(orderRepository.findAll()).thenReturn(orders);
        List<OrderServiceModel> ordersAll=orderService.findAll();
        Assert.assertEquals(orders.size(),ordersAll.size());

    }
    @Test
    void findAll_whenNoOrder_shouldReturnEmptyList(){
        Mockito.when(orderRepository.findAll()).thenReturn(orders);
     assertEquals(0,orderService.findAll().size());
    }

    @Test
    void findAllByUsername_whenUserExists_shouldReturnCorrect(){
        User user=new User();
        User user2=new User();
        user2.setUsername("Gosho");
        user.setUsername("Mitko");
        Order order1=new Order();
        order1.setUser(user);
        Order order2=new Order();
        order2.setUser(user);
        Order order3=new Order();
        order3.setUser(user2);
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        Mockito.when(orderRepository.findAllByUsername("Mitko"))
                .thenReturn(orders.stream().filter(o->o.getUser().getUsername().equals("Mitko")).collect(Collectors.toList()));
        List<OrderServiceModel>actualOrders=orderService.findAllByUsername("Mitko");
        assertEquals(2,actualOrders.size());

    }
@Test
    void findOrderById_whenExists_ShouldReturnOrder() {
    String id = "1";
    Order order = new Order();
    order.setId(id);
    Mockito.when(orderRepository.findById(id)).thenReturn(Optional.of(order));

    OrderServiceModel actualOrder = orderService.findById(id);
    assertEquals(order.getId(), actualOrder.getId());
}
}

