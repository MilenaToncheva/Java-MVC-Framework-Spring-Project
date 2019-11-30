package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.services.services.ArtworkService;
import softuni.artgallery.web.models.artwork.ArtworkViewModel;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final ArtworkService artworkService;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(ArtworkService artworkService, ModelMapper modelMapper) {
        this.artworkService = artworkService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    public String index() {
        return "home/index";
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView home(Principal principal, ModelAndView modelAndView) {
        List<ArtworkViewModel> artworks = Arrays.stream(this.modelMapper.map(this.artworkService.findAll(), ArtworkViewModel[].class))
                .collect(Collectors.toList());
        modelAndView.addObject("principal",principal);
      modelAndView.setViewName("home/home");
       modelAndView.addObject("artworks",artworks);
        return modelAndView;
    }
}
