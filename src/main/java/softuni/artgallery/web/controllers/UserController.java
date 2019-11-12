package softuni.artgallery.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }
}
