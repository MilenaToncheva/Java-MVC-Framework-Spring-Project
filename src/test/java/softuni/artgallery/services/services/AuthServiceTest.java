package softuni.artgallery.services.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import softuni.artgallery.data.models.User;
import softuni.artgallery.data.repository.UserRepository;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.UserRegisterServiceModel;
import softuni.artgallery.services.models.UserServiceModel;
import softuni.artgallery.services.services.validations.AuthValidationService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AuthServiceTest extends ServiceTestBase {

    @MockBean
    AuthValidationService authValidationService;
    @MockBean
    UserRepository userRepository;
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

        Mockito.when(this.authValidationService.isValid(model)).thenReturn(true);
        Mockito.when(this.bCryptPasswordEncoder.encode(model.getPassword())).thenReturn("1");

        authService.register(model);

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).saveAndFlush(argument.capture());



           User user = argument.getValue();
         assertNotNull(user);
    }

    @Test
    void register_whenInputIsInvalid_shouldThrowException() {
        UserRegisterServiceModel userModel = new UserRegisterServiceModel();


        Mockito.when(this.authValidationService.isValid(userModel)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> authService.register(userModel));
    }





    }

