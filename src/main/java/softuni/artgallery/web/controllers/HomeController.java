package softuni.artgallery.web.controllers;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.constants.common.CommonConstants;
import softuni.artgallery.services.models.GreetingServiceModel;
import softuni.artgallery.services.services.ArtworkService;
import softuni.artgallery.services.services.GreetingService;
import softuni.artgallery.web.models.artwork.ArtworkViewModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final ArtworkService artworkService;
    private final ModelMapper modelMapper;
    private final GreetingService greetingService;

    @Autowired
    public HomeController(ArtworkService artworkService, ModelMapper modelMapper, GreetingService greetingService) {

        this.artworkService = artworkService;
        this.modelMapper = modelMapper;
        this.greetingService = greetingService;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public ModelAndView index() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("home/index");
        return modelAndView;
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home( ModelAndView modelAndView) {
        List<ArtworkViewModel> artworks = Arrays.stream(this.modelMapper.map(this.artworkService.findAll(), ArtworkViewModel[].class))
                .collect(Collectors.toList());
        modelAndView.addObject("artworks",artworks);
        modelAndView.setViewName("home/home");
        return modelAndView;
    }
}
