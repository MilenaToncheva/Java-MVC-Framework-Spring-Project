package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.constants.common.CommonConstants;
import softuni.artgallery.services.models.GreetingServiceModel;
import softuni.artgallery.services.services.GreetingService;
import softuni.artgallery.web.models.greeting.GreetingCreateModel;

@Controller
@RequestMapping("/greetings")
public class GreetingController {
    private final GreetingService greetingService;
    private final ModelMapper modelMapper;

    @ModelAttribute(name="greetingCreateModel")
    public GreetingCreateModel greetingCreateModel(){
        return new GreetingCreateModel();
    }
    @Autowired
    public GreetingController(GreetingService greetingService, ModelMapper modelMapper) {
        this.greetingService = greetingService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getGreetingCreateForm(ModelAndView modelAndView,@ModelAttribute(name="greetingCreateModel")GreetingCreateModel greetingCreateModel){
        modelAndView.setViewName("greetings/greeting-create");
        modelAndView.addObject("greeting",greetingCreateModel);
        return modelAndView;
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView createGreeting(@ModelAttribute(name = "greetingCreateModel") GreetingCreateModel greetingCreateModel
                                                                ,ModelAndView modelAndView){
        GreetingServiceModel greetingServiceModel=this.modelMapper.map(greetingCreateModel,GreetingServiceModel.class);
        this.greetingService.createGreeting(greetingServiceModel);
        modelAndView.setViewName("home/home");

        return modelAndView;
    }

    @GetMapping("/fetch")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public GreetingServiceModel fetchGreeting(){
        GreetingServiceModel greetingServiceModel=this.greetingService.findByName(CommonConstants.GREETING_NAME);
        return greetingServiceModel;
    }
}

