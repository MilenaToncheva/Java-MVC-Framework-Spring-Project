package softuni.artgallery.services.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import softuni.artgallery.data.models.User;
import softuni.artgallery.data.repository.UserRepository;
import softuni.artgallery.error.UserNotFoundException;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.UserServiceModel;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest extends ServiceTestBase {
    @MockBean
    UserRepository userRepository;

    @MockBean
    RoleService roleService;
private final ModelMapper modelMapper;
    private User user;
    @Autowired
    private UserService userService;
@Autowired
    public UserServiceTest(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    protected void beforeEach() {
        user = new User();
        user.setUsername("Pesho");
        user.setFirstName("Pesho");
        user.setLastName("Peshev");
        user.setEmail("pesho@abv.bg");
        user.setPassword("1");
        user.setId("1");
    }

    @Test
    void userService_findUserByIdWhenValidId_shouldReturnUser() {


        Mockito.when(this.userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));
        UserServiceModel userModel = this.userService.findById(user.getId());
        Assert.assertEquals(user.getFirstName(), userModel.getFirstName());
        Assert.assertEquals(user.getLastName(), userModel.getLastName());
        Assert.assertEquals(user.getEmail(), userModel.getEmail());


    }

    @Test
    void userService_findUserByIdWhenInvalidId_shouldThrowException() {
        String id = "2";
        Mockito.when(this.userRepository.findById("2"))
                .thenReturn(Optional.empty());
        assertThrows(
                UserNotFoundException.class,
                () -> this.userService.findById(id));

    }

    @Test
    void userService_findUserByUsernameWhenValidUsername_shouldReturnUser() {
        Mockito.when(this.userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));


        Assert.assertEquals(user.getFirstName(), this.userService.findByUsername(this.user.getUsername()).getFirstName());

    }

    @Test
    void findUserByUsername_whenInvalidUsername_shouldThrowException() {
        String username = "Gosho";
        Mockito.when(this.userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(
                UsernameNotFoundException.class,
                () -> this.userService.findByUsername(username));
    }


    @Test
    void userService_findAllUsersWithCorrectData_shouldReturnCorrect() {
        User user2 = new User();
        user.setUsername("Gosho");
        user.setFirstName("Gosho");
        user.setLastName("Goshev");
        user.setEmail("gogo@abv.bg");
        user.setPassword("2");
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);

        Mockito.when(this.userRepository.findAll()).thenReturn(users);

        Assert.assertEquals(2, userService.findAll().size());


    }

    @Test
    void userService_findAllUsersWithNoUsers_shouldReturnCorrect() {
        List<User> users = new ArrayList<>();
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Assert.assertEquals(0, userService.findAll().size());
    }

}
