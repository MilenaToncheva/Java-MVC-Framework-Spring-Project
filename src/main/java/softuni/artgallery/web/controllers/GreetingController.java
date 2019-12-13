package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.ScheduledJob.ActiveGreeting;
import softuni.artgallery.ScheduledJob.ScheduledJob;
import softuni.artgallery.constants.common.CommonConstants;
import softuni.artgallery.services.models.GreetingServiceModel;
import softuni.artgallery.services.services.GreetingService;
import softuni.artgallery.web.models.greeting.GreetingCreateModel;
import softuni.artgallery.web.models.greeting.GreetingEditModel;
import softuni.artgallery.web.models.greeting.GreetingViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/greetings")
public class GreetingController {
    private final GreetingService greetingService;
    private final ModelMapper modelMapper;
    private final ScheduledJob scheduledJob;

    @ModelAttribute(name = "greetingCreateModel")
    public GreetingCreateModel greetingCreateModel() {
        return new GreetingCreateModel();
    }

    @ModelAttribute(name = "greetingEditModel")
    public GreetingEditModel greetingEditModel() {
        return new GreetingEditModel();
    }

    @Autowired
    public GreetingController(GreetingService greetingService, ModelMapper modelMapper, ScheduledJob scheduledJob) {
        this.greetingService = greetingService;
        this.modelMapper = modelMapper;
        this.scheduledJob = scheduledJob;
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getGreetingCreateForm(ModelAndView modelAndView, @ModelAttribute(name = "greetingCreateModel") GreetingCreateModel greetingCreateModel) {
        modelAndView.setViewName("greetings/greeting-create");
        modelAndView.addObject("greeting", greetingCreateModel);
        return modelAndView;
    }


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView createGreeting(@ModelAttribute(name = "greetingCreateModel") GreetingCreateModel greetingCreateModel
            , ModelAndView modelAndView) {
        GreetingServiceModel greetingServiceModel = this.modelMapper.map(greetingCreateModel, GreetingServiceModel.class);
        this.greetingService.createGreeting(greetingServiceModel);
        modelAndView.setViewName("home/home");

        return modelAndView;
    }

    @GetMapping("/fetch")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public GreetingServiceModel fetchGreeting() {
        GreetingServiceModel greetingServiceModel = this.greetingService.findByName(ActiveGreeting.getName());
        return greetingServiceModel;
    }

    @GetMapping("/edit/{name}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getGreetingEditForm(@PathVariable String name, ModelAndView modelAndView) {
        modelAndView.setViewName("greetings/greeting-edit");
        modelAndView.addObject("greeting");
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView editGreeting(@PathVariable String id, ModelAndView modelAndView,
                                     @ModelAttribute(name = "greetingEditModel") GreetingEditModel greetingEditModel) {
        this.greetingService.edit(id, greetingEditModel);
        modelAndView.setViewName("redirect:/greetings/all");
        return modelAndView;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getAllGreetings(ModelAndView modelAndView) {
        List<GreetingViewModel> greetings = Arrays.stream(this.modelMapper.map(this.greetingService.findAll(), GreetingViewModel[].class))
                .collect(Collectors.toList());
        modelAndView.addObject("greetings", greetings);
        modelAndView.setViewName("greetings/greetings-all");
        return modelAndView;
    }

    @PostMapping("/enable/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public  ModelAndView enableGreeting(@PathVariable String id,ModelAndView modelAndView){
        this.greetingService.enableGreeting(id);

        modelAndView.setViewName("redirect:/greetings/all");
        return modelAndView;
    }
    @PostMapping("/disable/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public  ModelAndView disableGreeting(@PathVariable String id,ModelAndView modelAndView){
        this.greetingService.disableGreeting(id);

        modelAndView.setViewName("redirect:/greetings/all");
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public  ModelAndView deleteGreeting(@PathVariable String id,ModelAndView modelAndView){
        this.greetingService.deleteGreeting(id);
        modelAndView.setViewName("redirect:/greetings/all");
        return modelAndView;
    }
}
