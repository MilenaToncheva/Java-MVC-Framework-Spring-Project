package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.error.ArtistNotDeletedException;
import softuni.artgallery.services.models.ArtistCreateServiceModel;
import softuni.artgallery.services.models.ArtistServiceModel;
import softuni.artgallery.services.services.ArtistService;
import softuni.artgallery.services.services.CloudinaryService;
import softuni.artgallery.web.models.artist.ArtistViewAllModel;
import softuni.artgallery.web.models.artist.ArtistCreateModel;
import softuni.artgallery.web.models.artist.ArtistEditModel;
import softuni.artgallery.web.models.artist.ArtistViewModel;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/artists")
public class ArtistController {
    private final ModelMapper modelMapper;
    private final ArtistService artistService;
    private final CloudinaryService cloudinaryService;

    @ModelAttribute("artistCreateModel")
    public ArtistCreateModel artistCreateModel() {
        return new ArtistCreateModel();
    }

    @ModelAttribute("artistEditModel")
    public ArtistEditModel artistEditModel() {
        return new ArtistEditModel();
    }


    @Autowired
    public ArtistController(ModelMapper modelMapper, ArtistService artistService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.artistService = artistService;
        this.cloudinaryService = cloudinaryService;
    }

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getArtistCreateForm( @ModelAttribute("artistCreateModel") ArtistCreateModel artistCreateModel,
                                            ModelAndView modelAndView) {
        modelAndView.setViewName("artists/artist-create");
        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView artistCreate(@ModelAttribute("artistCreateModel") ArtistCreateModel artistCreateModel,
                                     BindingResult bindingResult, ModelAndView modelAndView) throws IOException {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("artists/artists-create");
            return modelAndView;
        }
        ArtistCreateServiceModel artistCreateServiceModel = this.modelMapper.map(artistCreateModel, ArtistCreateServiceModel.class);
        artistCreateServiceModel.setImageUrl(cloudinaryService.uploadImage(artistCreateModel.getImage()));
        this.artistService.register(artistCreateServiceModel);
        modelAndView.setViewName("redirect:/artists/all");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getArtistEditForm(@PathVariable String id, @ModelAttribute("artistEditModel") ArtistEditModel artistEditModel, ModelAndView modelAndView) {
        ArtistServiceModel artistServiceModel = this.artistService.findById(id);
        artistEditModel = this.modelMapper.map(artistServiceModel, ArtistEditModel.class);
        modelAndView.addObject("artist", artistEditModel);
        modelAndView.addObject("artistId", id);
        modelAndView.setViewName("artists/artist-edit");
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView editArtist(@PathVariable String id, @ModelAttribute("artistEditModel") ArtistEditModel artistEditModel, ModelAndView modelAndView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("artist", artistEditModel);
            modelAndView.addObject("artistId", id);
            modelAndView.setViewName("artists/artist-edit");
            return modelAndView;
        }
        ArtistServiceModel artistServiceModel = this.modelMapper.map(artistEditModel, ArtistServiceModel.class);
        artistServiceModel.setId(id);
        this.artistService.edit(id, artistServiceModel);
        modelAndView.setViewName("redirect:/artists/all");
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getArtistDetails(@PathVariable String id, ModelAndView modelAndView) {
        ArtistServiceModel artistServiceModel = this.artistService.findById(id);
        ArtistViewModel artistViewModel = this.modelMapper.map(artistServiceModel, ArtistViewModel.class);
        modelAndView.addObject("artist", artistViewModel);
        modelAndView.setViewName("artists/artist-details");
        return modelAndView;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getAllArtists(ModelAndView modelAndView) {
        List<ArtistViewAllModel> artists = Arrays.stream(this.modelMapper.map(this.artistService.findAll(), ArtistViewAllModel[].class))
                .collect(Collectors.toList());
        modelAndView.addObject("artists", artists);
        modelAndView.setViewName("/artists/artists-all");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getDeleteForm(@PathVariable String id, ModelAndView modelAndView) {
        ArtistServiceModel artistServiceModel = this.artistService.findById(id);
        ArtistViewModel artistViewModel = this.modelMapper.map(artistServiceModel, ArtistViewModel.class);
        modelAndView.addObject("artist", artistViewModel);
        modelAndView.setViewName("artists/artist-delete");
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView deleteArtist(@PathVariable String id,ModelAndView modelAndView) throws ArtistNotDeletedException {
        this.artistService.delete(id);
        modelAndView.setViewName("redirect:/artists/all");
        return modelAndView;
    }

    @GetMapping("/fetch")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    @ResponseBody
    public List<ArtistViewModel> fetchArtists(){
        List<ArtistViewModel>artists= Arrays.stream(this.modelMapper.map(this.artistService.findAll(), ArtistViewModel[].class))
                .collect(Collectors.toList());
        return artists;
    }
}

