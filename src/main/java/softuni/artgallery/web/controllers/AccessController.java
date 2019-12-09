package softuni.artgallery.web.controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AccessController {

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized( ModelAndView modelAndView){
        modelAndView.setViewName("unauthorized");
        return modelAndView;
    }

}
