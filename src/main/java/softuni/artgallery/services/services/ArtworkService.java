package softuni.artgallery.services.services;

import softuni.artgallery.error.ArtworkNotFoundException;
import softuni.artgallery.services.models.ArtworkCreateServiceModel;
import softuni.artgallery.services.models.ArtworkServiceModel;

import java.util.List;

public interface ArtworkService {
    List<ArtworkServiceModel> findAll();

    void save(ArtworkCreateServiceModel artworkCreateServiceModel);

    ArtworkServiceModel findById(String id) throws Exception, ArtworkNotFoundException;

    void edit(ArtworkServiceModel artworkServiceModel);

    void delete(String id) throws ArtworkNotFoundException;

    List<ArtworkServiceModel> findAllArtworksByArtistId(String artistId);

   // void deleteAllArtworksByArtist(String artistId);

    List<ArtworkServiceModel> findAllByCategory(String category);



 void writeOffArtwork(String id);// mark it unavailable
}
