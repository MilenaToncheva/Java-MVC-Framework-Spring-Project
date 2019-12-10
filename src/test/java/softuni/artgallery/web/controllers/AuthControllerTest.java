package softuni.artgallery.web.controllers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import softuni.artgallery.data.repository.UserRepository;
import softuni.artgallery.services.models.UserRegisterServiceModel;
import softuni.artgallery.services.services.AuthService;
import softuni.artgallery.services.services.validations.AuthValidationService;
import softuni.artgallery.web.base.ControllerTestBase;
import softuni.artgallery.web.models.auth.UserRegisterModel;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class AuthControllerTest extends ControllerTestBase {

    @MockBean
    UserRepository userRepository;
    @MockBean
    BindingResult bindingResult;

    @MockBean
    AuthService authService;
    @Autowired
    AuthController authController;

    @Test
    public void register_shouldReturnCorrectView() throws Exception {
        this.mockMvc
                .perform(get("/users/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/create.html"));


    }


    @Test
    public void login_withCorrectData_shouldRedirectHome(){

    }


}

