package softuni.artgallery.services.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.artgallery.data.models.Artist;
import softuni.artgallery.data.repository.ArtistRepository;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.ArtistCreateServiceModel;
import softuni.artgallery.services.models.ArtistServiceModel;
import softuni.artgallery.services.services.validations.ArtistValidationService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class ArtistServiceTest extends ServiceTestBase {


    @MockBean
    ArtistRepository artistRepository;

    @MockBean
    ArtistValidationService artistValidationService;

    @Autowired
    ArtistService artistService;

    @Test
     void createArtist_withCorrectData_shouldSave() {
        ArtistCreateServiceModel artistModel = new ArtistCreateServiceModel();
        artistModel.setName("");
        artistModel.setEmail("mara@abv.bg");
        artistModel.setEducation("SBU");
        artistModel.setImageUrl("http://res.cloudinary.com/mt-art-gallery/image/upload/v1575580860/th88ylq7esqib9anvzkg.jpg");
        artistModel.setLivesIn("Sofia");
        artistModel.setHistory(" yuyui yui yiy ui");
        Mockito.when(artistValidationService.isValid(artistModel)).thenReturn(true);

        artistService.create(artistModel);

        ArgumentCaptor<Artist> argument = ArgumentCaptor.forClass(Artist.class);
        Mockito.verify(artistRepository).saveAndFlush(argument.capture());

        Artist artist = argument.getValue();
        assertNotNull(artist);


    }

    @Test
     void createArtist_withIncorrectData_shouldThrowException() {
        ArtistCreateServiceModel artistModel = new ArtistCreateServiceModel();
        artistModel.setName("Maria");
        artistModel.setEmail("mara@abv.bg");
        artistModel.setEducation("SBU");
        artistModel.setImageUrl("http://res.cloudinary.com/mt-art-gallery/image/upload/v1575580860/th88ylq7esqib9anvzkg.jpg");
        artistModel.setLivesIn("Sofia");
        artistModel.setHistory(" yuyui yui yiy ui");


        Mockito.when(artistValidationService.isValid(artistModel)).thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> artistService.create(artistModel));

    }


    @Test
     void findByName_whenExist_shouldReturnArtist() {
        String name="Morgana";
        Artist artist=new Artist();
        artist.setName(name);

        Mockito.when(artistRepository.findByName(name))
                .thenReturn(artist);
        ArtistServiceModel artistServiceModel=artistService.findByName(name);

        assertEquals(artist.getName(),artistServiceModel.getName());

    }
    @Test
    void findByName_whenNotExist_shouldThrowException() {
        String name="Artist Name";
      Artist artist=new Artist();

        Mockito.when(artistRepository.findByName(name))
                .thenReturn(null);
       assertThrows(RuntimeException.class,
               ()->artistService.findByName(name));

    }

    @Test
    void findArtistById_whenArtistExists_shouldReturnArtist(){
        String id="1";
        Artist artist=new Artist();
        artist.setId(id);
        Mockito.when(artistRepository.findById(id)).thenReturn(Optional.of(artist));

        ArtistServiceModel artistServiceModel=artistService.findById(id);
        assertEquals(artist.getId(),artistServiceModel.getId());
    }

    @Test
    void findArtistById_whenArtistDoesNotExist_shouldThrowException(){
        String id="1";
        Artist artist=new Artist();

        Mockito.when(artistRepository.findById(id)).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class,
                ()->artistService.findById(id));
    }

    @Test
     void findAllArtist_whenArtistsExist_shouldReturnArtists(){
        Artist artist1=new Artist();
        Artist artist2=new Artist();
        Artist[]artists=new Artist[2];
        Mockito.when(artistRepository.findAll()).thenReturn(Arrays.asList(artists));
        List<ArtistServiceModel> artistsAll=artistService.findAll();
        Assert.assertEquals(artists.length,artistsAll.size());


    }


}
