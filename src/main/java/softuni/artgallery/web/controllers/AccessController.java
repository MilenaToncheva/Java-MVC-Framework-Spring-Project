package softuni.artgallery.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class AccessController {

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized(Principal principal, ModelAndView modelAndView){
        modelAndView.addObject("principal",principal);
        modelAndView.setViewName("unauthorized");
        return modelAndView;
    }

}
