package softuni.artgallery.services.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import softuni.artgallery.data.models.Role;
import softuni.artgallery.data.models.User;
import softuni.artgallery.data.repository.UserRepository;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.RoleServiceModel;
import softuni.artgallery.services.models.UserRegisterServiceModel;
import softuni.artgallery.services.models.UserServiceModel;
import softuni.artgallery.services.services.validations.AuthValidationService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AuthServiceTest extends ServiceTestBase {

    @MockBean
    AuthValidationService authValidationService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    UserService userService;
    @MockBean
    ModelMapper modelMapper;
    @MockBean
    RoleService roleService;
    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AuthService authService;

    @Test
    void register_whenAllIsValid_shouldSave() {
        UserRegisterServiceModel model = new UserRegisterServiceModel();

        model.setUsername("morgana");
        model.setFirstName("Morgana");
        model.setLastName("Thewitch");
        model.setEmail("morgana@abv.bg");
        model.setPassword("1aA#111");
        model.setConfirmPassword("1aA#111");
        model.setAuthorities(new HashSet<>());

        RoleServiceModel role = new RoleServiceModel();
        role.setAuthority("ROLE_USER");
        User user = new User();
        user.setUsername(model.getUsername());
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setEmail(model.getEmail());
        user.setPassword(model.getPassword());
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        user.setAuthorities(roles);

        Mockito.when(this.userRepository.count()).thenReturn(1L);
        Mockito.when(this.authValidationService.isValid(model)).thenReturn(true);
        Mockito.when(this.bCryptPasswordEncoder.encode(model.getPassword())).thenReturn("1aA#111");
        Mockito.when(this.roleService.findByAuthority("ROLE_USER")).thenReturn(role);
        Mockito.when(this.modelMapper.map(model, User.class)).thenReturn(user);
        authService.register(model);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).saveAndFlush(argument.capture());


        User user1 = argument.getValue();
        assertNotNull(user1);
    }

    @Test
    void register_whenInputIsInvalid_shouldThrowException() {
        UserRegisterServiceModel userModel = new UserRegisterServiceModel();


        Mockito.when(this.authValidationService.isValid(userModel)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authService.register(userModel));
    }

    @Test
    void loadUserByUsername_whenUserExists_shouldReturnUser() {
        String username = "morgana";
        User user=new User();

        user.setUsername(username);
        Mockito.when(this.userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        UserDetails actual=this.authService.loadUserByUsername(username);
        assertEquals(user.getUsername(),actual.getUsername());
    }

    @Test
    void loadUserByUserName_whenNoUser_shouldThrowException(){
       String username="morgana";
        Mockito.when(this.userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,
                ()->this.authService.loadUserByUsername(username));
    }
}

