package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.artgallery.constants.artworksMessages.ArtworkErrorMessages;
import softuni.artgallery.data.models.Artist;
import softuni.artgallery.data.models.Artwork;
import softuni.artgallery.data.models.Category;
import softuni.artgallery.data.repository.ArtworkRepository;
import softuni.artgallery.error.ArtworkIllegalArgumentsException;
import softuni.artgallery.error.ArtworkNotFoundException;
import softuni.artgallery.services.models.ArtistServiceModel;
import softuni.artgallery.services.models.ArtworkCreateServiceModel;
import softuni.artgallery.services.models.ArtworkServiceModel;
import softuni.artgallery.services.services.ArtistService;
import softuni.artgallery.services.services.ArtworkService;
import softuni.artgallery.services.services.validations.ArtworkValidationService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    private final ArtworkRepository artworkRepository;
    private final ArtistService artistService;
private final ArtworkValidationService artworkValidationService;
    private final ModelMapper modelMapper;


    @Autowired
    public ArtworkServiceImpl(ModelMapper modelMapper, ArtworkRepository artworkRepository, ArtistService artistService,
                              ArtworkValidationService artworkValidationService) {
        this.modelMapper = modelMapper;
        this.artworkRepository = artworkRepository;
        this.artistService = artistService;

        this.artworkValidationService = artworkValidationService;
    }

    @Override
    public List<ArtworkServiceModel> findAll() {
        return Arrays.stream(this.modelMapper.map(this.artworkRepository.findAll(),
                                                                                ArtworkServiceModel[].class))
                                                     .collect(Collectors.toList());
    }

    @Override
    public void save(ArtworkCreateServiceModel artworkCreateServiceModel) {
        if(!this.artworkValidationService.isValid(artworkCreateServiceModel)){
            throw new ArtworkIllegalArgumentsException(ArtworkErrorMessages.ARTWORK_INVALID_INPUT);
        }
        Artist artist = this.modelMapper.map(this.artistService
                .findByName(artworkCreateServiceModel.getArtistName()), Artist.class);
        Artwork artwork = this.modelMapper.map(artworkCreateServiceModel, Artwork.class);

        artwork.setArtist(artist);
        this.artworkRepository.save(artwork);

    }

    @Override
    public ArtworkServiceModel findById(String id) {
        Artwork artwork = this.artworkRepository.findById(id).orElseThrow(() ->
                new ArtworkNotFoundException(ArtworkErrorMessages.ARTWORK_WITH_ID_NOT_FOUND));

        ArtworkServiceModel artworkServiceModel = this.modelMapper.map(artwork, ArtworkServiceModel.class);
        String artistName = artwork.getArtist().getName();
        ArtistServiceModel artistServiceModel = this.artistService.findByName(artistName);
        artworkServiceModel.setArtist(artistServiceModel);

        return artworkServiceModel;
    }

    @Override
    public void edit(ArtworkServiceModel artworkServiceModel) {
        Artwork artwork = this.artworkRepository.findById(artworkServiceModel.getId())
                .orElseThrow(() -> new ArtworkNotFoundException(ArtworkErrorMessages.ARTWORK_WITH_ID_NOT_FOUND));
        artwork.setName(artworkServiceModel.getName());
        artwork.setDescription(artworkServiceModel.getDescription());
        String artistName = artworkServiceModel.getArtist().getName();
        ArtistServiceModel artistServiceModel = this.artistService.findByName(artistName);
        artwork.setArtist(this.modelMapper.map(artistServiceModel, Artist.class));
        artwork.setCategory(artworkServiceModel.getCategory());
        artwork.setImageUrl(artworkServiceModel.getImageUrl());
        artwork.setPrice(artworkServiceModel.getPrice());
if(!this.artworkValidationService.isValid(this.modelMapper.map(artwork,ArtworkCreateServiceModel.class))){
    throw new ArtworkIllegalArgumentsException(ArtworkErrorMessages.ARTWORK_INVALID_INPUT);
}
        this.artworkRepository.saveAndFlush(artwork);
    }

    @Override
    public void delete(String id) {
        Artwork artwork = this.artworkRepository.findById(id)
                .orElseThrow(() -> new ArtworkNotFoundException(ArtworkErrorMessages.ARTWORK_WITH_ID_NOT_FOUND));
        this.artworkRepository.delete(artwork);
    }

    @Override
    public List<ArtworkServiceModel> findAllArtworksByArtistId(String artistId) {
        return Arrays.stream(this.modelMapper.map(this.artworkRepository.findAllArtworksByArtistId(artistId),
                                                                                ArtworkServiceModel[].class))
                                                                         .collect(Collectors.toList());

    }

  // @Override
  // public void deleteAllArtworksByArtist(String artistId) {
  //     this.artworkRepository.findAllArtworksByArtistId(artistId).forEach(this.artworkRepository::delete);
  // }

  @Override
  public List<ArtworkServiceModel> findAllByCategory(String category) {
      List<Artwork> artworks = this.artworkRepository.findByCategory(Category.valueOf(category));

      return Arrays.stream(this.modelMapper.map(artworks, ArtworkServiceModel[].class))
                                                                              .collect(Collectors.toList());
  }

    @Override
    public void writeOffArtwork(String id) {
      Artwork artwork=  this.artworkRepository.findById(id).orElseThrow(()->new ArtworkNotFoundException(
                                                             ArtworkErrorMessages.ARTWORK_WITH_ID_NOT_FOUND));

      artwork.setAvailable(false);

      this.artworkRepository.saveAndFlush(artwork);

    }


}
