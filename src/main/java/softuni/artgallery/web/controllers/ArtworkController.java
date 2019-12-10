package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.constants.artworksMessages.ArtworkErrorMessages;
import softuni.artgallery.error.ArtworkNotFoundException;
import softuni.artgallery.services.models.ArtworkCreateServiceModel;
import softuni.artgallery.services.models.ArtworkServiceModel;
import softuni.artgallery.services.services.ArtistService;
import softuni.artgallery.services.services.ArtworkService;
import softuni.artgallery.services.services.CloudinaryService;
import softuni.artgallery.web.models.artist.ArtistViewModel;
import softuni.artgallery.web.models.artwork.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/artworks")
public class ArtworkController {
    private final ModelMapper modelMapper;
    private final ArtworkService artworkService;
    private final ArtistService artistService;
    private final CloudinaryService cloudinaryService;

    @ModelAttribute("artworkCreateModel")
    public ArtworkCreateModel artworkCreateModel() {
        return new ArtworkCreateModel();
    }

    @ModelAttribute("artworkDetailsModel")
    public ArtworkDetailsModel artworkDetailsModel() {
        return new ArtworkDetailsModel();
    }

    @ModelAttribute("artworkEditModel")
    public ArtworkEditModel artworkEditModel() {
        return new ArtworkEditModel();
    }


    @ModelAttribute("artworkDeleteModel")
    public ArtworkDeleteModel artworkDeleteModel() {
        return new ArtworkDeleteModel();
    }

    @Autowired
    public ArtworkController(ModelMapper modelMapper, ArtworkService artworkService, ArtistService artistService, CloudinaryService cloudinaryService) {
        this.modelMapper = modelMapper;
        this.artworkService = artworkService;
        this.artistService = artistService;
        this.cloudinaryService = cloudinaryService;
    }



    @GetMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView getArtworkCreateForm( @ModelAttribute("artworkCreateModel") ArtworkCreateModel artworkCreateModel,
                                             ModelAndView modelAndView) {
        modelAndView.setViewName("artworks/artworks-create");
        return modelAndView;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public ModelAndView createArtwork(@Valid @ModelAttribute("artworkCreateModel") ArtworkCreateModel artworkCreateModel,
                                BindingResult bindingResult, ModelAndView modelAndView) throws IOException {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("artworks/artworks-create");
            return modelAndView;
        }

        ArtworkCreateServiceModel artworkCreateServiceModel = this.modelMapper.map(artworkCreateModel, ArtworkCreateServiceModel.class);
        artworkCreateServiceModel.setImageUrl(cloudinaryService.uploadImage(artworkCreateModel.getImage()));
        this.artworkService.save(artworkCreateServiceModel);

         modelAndView.setViewName("redirect:/artworks/all");
         return modelAndView;
    }

    @GetMapping("/all")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getAllArtworks(ModelAndView modelAndView) {
        List<ArtworkViewModel> artworks = Arrays.stream(this.modelMapper.map(this.artworkService.findAll(), ArtworkViewModel[].class))
                .collect(Collectors.toList());
        modelAndView.addObject("artworks", artworks);
        modelAndView.setViewName("artworks/artworks-all");
        return modelAndView;
    }
    @GetMapping("/fetch")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public    List<ArtworkViewModel> getArtworks() {
        List<ArtworkViewModel>  artworks = Arrays.stream(this.modelMapper.map(this.artworkService.findAll(), ArtworkViewModel[].class))
                .collect(Collectors.toList());
        return artworks;
    }




    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getArtworkDetails(@PathVariable String id, @ModelAttribute("artworkDetailsModel") ArtworkDetailsModel artworkDetailsModel,
                                          ModelAndView modelAndView) {
        try {
            ArtworkServiceModel artworkServiceModel = this.artworkService.findById(id);
            artworkDetailsModel = this.modelMapper.map(artworkServiceModel, ArtworkDetailsModel.class);
            ArtistViewModel artistViewModel = this.modelMapper.map(artworkServiceModel.getArtist(), ArtistViewModel.class);
            artworkDetailsModel.setArtist(artistViewModel);
            artworkDetailsModel.setArtistId(artworkServiceModel.getArtist().getId());
            modelAndView.addObject("artwork", artworkDetailsModel);

            modelAndView.setViewName("artworks/artworks-details");

            return modelAndView;
        } catch (Exception ex) {
            throw new ArtworkNotFoundException(ArtworkErrorMessages.ARTWORK_WITH_ID_NOT_FOUND);
        }

    }



    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView getEditForm(@PathVariable String id, @ModelAttribute("artworkEditModel") ArtworkEditModel artworkEditModel, ModelAndView modelAndView) throws Exception, ArtworkNotFoundException {
        ArtworkServiceModel artworkServiceModel = this.artworkService.findById(id);
        artworkEditModel = this.modelMapper.map(artworkServiceModel, ArtworkEditModel.class);
        modelAndView.addObject("artwork", artworkEditModel);
        modelAndView.addObject("artworkId", id);
        modelAndView.setViewName("artworks/artwork-edit");
        return modelAndView;
    }



    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editArtwork(@PathVariable String id, @Valid @ModelAttribute("artworkEditModel") ArtworkEditModel artworkEditModel, BindingResult bindingResult,
                                    ModelAndView modelAndView) throws IOException {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("artwork", artworkEditModel);
            modelAndView.setViewName("artworks/artwork-edit");
            return modelAndView;
        }
        ArtworkServiceModel artworkServiceModel = this.modelMapper.map(artworkEditModel, ArtworkServiceModel.class);
        artworkServiceModel.setId(id);
        this.artworkService.edit(artworkServiceModel);
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView getArtworkDeleteForm(@PathVariable String id, @ModelAttribute("artworkDeleteModel") ArtworkDeleteModel artworkDeleteModel, ModelAndView modelAndView) throws Exception, ArtworkNotFoundException {
        ArtworkServiceModel artworkServiceModel = this.artworkService.findById(id);
        artworkDeleteModel = this.modelMapper.map(artworkServiceModel, ArtworkDeleteModel.class);
        artworkDeleteModel.setArtistName(artworkServiceModel.getArtist().getName());
        modelAndView.setViewName("artworks/artwork-delete");
        modelAndView.addObject("artwork", artworkDeleteModel);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteArtwork(Principal principal,@PathVariable String id, ModelAndView modelAndView) throws ArtworkNotFoundException {

        this.artworkService.delete(id);
        modelAndView.addObject("principal", principal);
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @GetMapping("/artworks-by-artist/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getAllArtworksByArtist(@PathVariable String id, ModelAndView modelAndView) {

        List<ArtworkServiceModel> artworkServiceModels = this.artworkService.findAllArtworksByArtistId(id);
        List<ArtworkViewModel> artworks = Arrays.stream(this.modelMapper.map(artworkServiceModels, ArtworkViewModel[].class))
                .collect(Collectors.toList());
        String artistName = this.artistService.findById(id).getName();
        modelAndView.addObject("artistName", artistName);
        modelAndView.addObject("artworks", artworks);
        modelAndView.addObject("artistId", id);
        modelAndView.setViewName("artworks/artworks-by-artist");
        return modelAndView;
    }

    @PostMapping("/delete/all-by-artist/{artistId}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteAllArtworksByArtistId(@PathVariable(value = "artistId") String artistId,
                                                    ModelAndView modelAndView) {
        this.artworkService.deleteAllArtworksByArtist(artistId);
        modelAndView.setViewName( "redirect:/artworks/all");
        return modelAndView;
    }


    @GetMapping("/artworks-by-category/{category}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getArtworksByCategory(@PathVariable String category, ModelAndView modelAndView) {
        List<ArtworkViewModel> artworksByCategory = Arrays.stream(this.modelMapper.map(this.artworkService.findAllByCategory(category),
                                                                                                            ArtworkViewModel[].class))
                .collect(Collectors.toList());
        modelAndView.addObject("artworks", artworksByCategory);
        modelAndView.addObject("category", category);
        modelAndView.setViewName("artworks/artworks-by-category");
        return modelAndView;
    }


}
