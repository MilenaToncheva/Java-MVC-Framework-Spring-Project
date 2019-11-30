package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.services.models.UserRegisterServiceModel;
import softuni.artgallery.web.models.auth.UserRegisterModel;
import softuni.artgallery.services.services.AuthService;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class AuthController {
    private final AuthService authService;
    private final ModelMapper modelMapper;

    @ModelAttribute("userRegisterModel")
    public UserRegisterModel userRegisterModel() {
        return new UserRegisterModel();
    }


    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String getRegisterForm(@ModelAttribute("userRegisterModel") UserRegisterModel userRegisterModel) {
        return "auth/register.html";
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public String registerUser(@Valid @ModelAttribute("userRegisterModel") UserRegisterModel userRegisterModel,
                                                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        if (!userRegisterModel.getConfirmPassword().equals(userRegisterModel.getPassword())) {
            return "auth/register";
        }
        UserRegisterServiceModel userRegisterServiceModel = this.modelMapper.map(userRegisterModel,
                                                                                    UserRegisterServiceModel.class);

        this.authService.register(userRegisterServiceModel);

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public String login( ) {
         return "auth/login";

    }


}