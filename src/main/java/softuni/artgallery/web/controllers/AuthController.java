package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.artgallery.services.models.UserLoginServiceModel;
import softuni.artgallery.services.models.UserRegisterServiceModel;
import softuni.artgallery.web.models.auth.UserLoginModel;
import softuni.artgallery.web.models.auth.UserRegisterModel;
import softuni.artgallery.services.services.AuthService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class AuthController {
    private final AuthService authService;
    private final ModelMapper modelMapper;

@ModelAttribute("userRegisterModel")
public UserRegisterModel userRegisterModel(){
    return new UserRegisterModel();
}
@ModelAttribute("userLoginModel")
public UserLoginModel userLoginModel(){
    return new UserLoginModel();
}
    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String getRegisterForm(@ModelAttribute("userRegisterModel")UserRegisterModel userRegisterModel) {
        return "auth/register.html";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userRegisterModel") UserRegisterModel userRegisterModel, BindingResult bindingResult) {

       if(bindingResult.hasErrors()){
           return "auth/register.html";
       }

        UserRegisterServiceModel userRegisterServiceModel = this.modelMapper.map(userRegisterModel, UserRegisterServiceModel.class);

            this.authService.register(userRegisterServiceModel);

             return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String getLoginForm(@ModelAttribute("userLoginModel")UserLoginModel userLoginModel) {
        return "auth/login.html";
    }

    @PostMapping("/login")
    public String loginUser( @ModelAttribute("userLoginModel") UserLoginModel userLoginModel, HttpSession session) throws Exception {


    try {
            this.authService.login(this.modelMapper.map(userLoginModel, UserLoginServiceModel.class));
            session.setAttribute("username", userLoginModel.getUsername());

            return "redirect:/home";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session){
    session.invalidate();
    return "redirect:/";
    }
}