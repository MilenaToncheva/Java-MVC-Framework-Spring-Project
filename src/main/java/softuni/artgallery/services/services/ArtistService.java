package softuni.artgallery.services.services;

import org.springframework.stereotype.Service;
import softuni.artgallery.error.ArtistNotDeletedException;
import softuni.artgallery.services.models.ArtistCreateServiceModel;
import softuni.artgallery.services.models.ArtistServiceModel;

import java.util.List;

@Service
public interface ArtistService {
    void create(ArtistCreateServiceModel artistCreateServiceModel);

    void edit(String id,ArtistServiceModel artistServiceModel);

    void delete(String id) throws ArtistNotDeletedException;

    ArtistServiceModel findByName(String name);
    ArtistServiceModel findById(String id);

    List<ArtistServiceModel> findAll();

}
