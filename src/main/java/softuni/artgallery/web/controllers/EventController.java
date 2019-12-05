package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.services.models.EventCreateServiceModel;
import softuni.artgallery.services.services.CloudinaryService;
import softuni.artgallery.services.services.EventService;
import softuni.artgallery.web.models.event.EventAllViewModel;
import softuni.artgallery.web.models.event.EventCreateModel;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public EventController(EventService eventService, ModelMapper modelMapper, CloudinaryService cloudinaryService) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
    }

    @ModelAttribute(name = "eventCreateModel")
    public EventCreateModel eventCreateModel() {
        return new EventCreateModel();
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getEventCreateForm(Principal principal, ModelAndView modelAndView,
                                           @ModelAttribute(name = "eventCreateModel") EventCreateModel eventCreateModel) {

        modelAndView.addObject("principal", principal);
        modelAndView.addObject("eventModel", eventCreateModel);

        modelAndView.setViewName("events/event-create");
        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView createEvent(Principal principal, @ModelAttribute(name = "eventCreateModel") EventCreateModel eventCreateModel,
                                    ModelAndView modelAndView) throws IOException {
        EventCreateServiceModel eventCreateServiceModel = this.modelMapper.map(eventCreateModel, EventCreateServiceModel.class);
        eventCreateServiceModel.setImageUrl(cloudinaryService.uploadImage(eventCreateModel.getImage()));
        this.eventService.register(eventCreateServiceModel);
        modelAndView.addObject("principal", principal);
        modelAndView.setViewName("redirect:events/events-all");
        return modelAndView;
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getAllEvents(Principal principal, ModelAndView modelAndView) {
        List<EventAllViewModel> events = Arrays.stream(this.modelMapper.map(this.eventService.findAll(), EventAllViewModel[].class))
                .collect(Collectors.toList());
        modelAndView.addObject("events", events);
        modelAndView.addObject("principal", principal);
        modelAndView.setViewName("events/events-all");
        return modelAndView;
    }
}
