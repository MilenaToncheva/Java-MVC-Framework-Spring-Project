package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.services.models.EventCreateServiceModel;
import softuni.artgallery.services.models.EventServiceModel;
import softuni.artgallery.services.services.CloudinaryService;
import softuni.artgallery.services.services.EventService;
import softuni.artgallery.web.models.event.EventAllViewModel;
import softuni.artgallery.web.models.event.EventCreateModel;
import softuni.artgallery.web.models.event.EventDeleteModel;
import softuni.artgallery.web.models.event.EventViewModel;

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

    @ModelAttribute(name = "eventViewModel")
    public EventViewModel eventViewModel() {
        return new EventViewModel();
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
        modelAndView.setViewName("redirect:/events/all");
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

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getEventDetails(@PathVariable String id, ModelAndView modelAndView, Principal principal) {
        EventViewModel eventViewModel = this.modelMapper.map(this.eventService.findById(id), EventViewModel.class);
        modelAndView.setViewName("events/event-details");
        modelAndView.addObject("principal", principal);
        modelAndView.addObject("event", eventViewModel);

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView deleteEvent(@PathVariable String id, ModelAndView modelAndView, Principal principal) {
        this.eventService.delete(id);
        modelAndView.addObject("principal", principal);
        modelAndView.setViewName("redirect:/events/all");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getEditEvent(@PathVariable String id, @ModelAttribute(name = "eventViewModel") EventViewModel eventViewModel,
                                     ModelAndView modelAndView) {
        eventViewModel = this.modelMapper.map(this.eventService.findById(id), EventViewModel.class);
        modelAndView.addObject("event", eventViewModel);
        modelAndView.setViewName("events/event-edit");
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView editEvent(@PathVariable String id, @ModelAttribute(name = "eventViewModel") EventViewModel eventViewModel,
                                     ModelAndView modelAndView) {
      this.eventService.edit(id,this.modelMapper.map(eventViewModel,EventServiceModel.class));
        modelAndView.setViewName("redirect:/events/all");
        return modelAndView;
    }
}
