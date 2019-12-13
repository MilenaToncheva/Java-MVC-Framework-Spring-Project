package softuni.artgallery.services.services;

import net.bytebuddy.asm.Advice;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import softuni.artgallery.data.models.Artist;
import softuni.artgallery.data.models.Artwork;
import softuni.artgallery.data.models.Category;
import softuni.artgallery.data.models.Event;
import softuni.artgallery.data.repository.ArtworkRepository;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.ArtistCreateServiceModel;
import softuni.artgallery.services.models.ArtistServiceModel;
import softuni.artgallery.services.models.ArtworkCreateServiceModel;
import softuni.artgallery.services.models.ArtworkServiceModel;
import softuni.artgallery.services.services.validations.ArtworkValidationService;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArtworkServiceTest extends ServiceTestBase {
    List<Artwork> artworks;
    @MockBean
    ArtworkRepository artworkRepository;

    @MockBean
    ArtworkValidationService artworkValidationService;

    @MockBean
    ArtistService artistService;

    @Autowired
    ArtworkService artworkService;

    @BeforeEach
    protected void beforeEach() {
        artworks = new ArrayList<>();
        Mockito.when(artworkRepository.findAll()).thenReturn(artworks);
    }

    @Test
    void findAllArtworks_whenArtworksExist_shouldReturnArtworks() {

        Artwork artwork1 = new Artwork();
        Artwork artwork2 = new Artwork();
        artwork1.setId("1");
        artwork2.setId("2");
        artworks.add(artwork1);
        artworks.add(artwork2);

        List<ArtworkServiceModel> actualArtworks = artworkService.findAll();

        Assert.assertEquals(artworks.size(), actualArtworks.size());
        Assert.assertEquals(artworks.get(0).getId(), actualArtworks.get(0).getId());
        Assert.assertEquals(artworks.get(1).getId(), actualArtworks.get(1).getId());


    }

    @Test
    void findAllArtworks_whenNo_shouldReturnEmptyList() {
        artworks.clear();

        List<ArtworkServiceModel> actualArtworks = artworkService.findAll();

        Assert.assertEquals(0, actualArtworks.size());
    }

    @Test
    void saveArtwork_whenValidInput_shouldSave() {
        String artistName = "Misho";
        ArtworkCreateServiceModel artworkCreateModel = new ArtworkCreateServiceModel();
        artworkCreateModel.setImageUrl("/rr/rrr/rrrr/rrrr.jpg");
        artworkCreateModel.setArtistName(artistName);
        artworkCreateModel.setCategory(Category.CERAMICS);
        artworkCreateModel.setDescription("wwe qwoei oqweq e wieoqwep qwe eq ");
        artworkCreateModel.setName("birds");
        artworkCreateModel.setPrice(new BigDecimal("200"));
        ArtistCreateServiceModel artistModel = new ArtistCreateServiceModel();
        ArtistServiceModel artist = new ArtistServiceModel();
        artist.setName(artistName);
        artistModel.setName(artistName);
        artworkCreateModel.setArtistName(artistName);

        Mockito.when(artistService.findByName(artistName)).thenReturn(artist);
        Mockito.when(artworkValidationService.isValid(artworkCreateModel)).thenReturn(true);
        this.artworkService.save(artworkCreateModel);

        ArgumentCaptor<Artwork> argument = ArgumentCaptor.forClass(Artwork.class);
        Mockito.verify(artworkRepository).save(argument.capture());
        Artwork artwork = argument.getValue();
        assertNotNull(artwork);
    }


    @Test
    void saveArtwork_whenInvalidInput_shouldThrowException() {
        ArtworkCreateServiceModel artworkCreateModel = new ArtworkCreateServiceModel();
        artworkCreateModel.setImageUrl("/rr/rrr/rrrr/rrrr.jpg");
        artworkCreateModel.setArtistName("Misho");
        artworkCreateModel.setCategory(Category.CERAMICS);
        artworkCreateModel.setDescription("wwe qwoei oqweq e wieoqwep qwe eq ");
        artworkCreateModel.setName("birds");
        artworkCreateModel.setPrice(new BigDecimal("200"));
        String artistName = "Misho";
        ArtistCreateServiceModel artistModel = new ArtistCreateServiceModel();
        ArtistServiceModel artist = new ArtistServiceModel();
        artist.setName(artistName);
        artistModel.setName(artistName);

        Mockito.when(artistService.findByName(artistName)).thenReturn(artist);
        Mockito.when(artworkValidationService.isValid(artworkCreateModel)).thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> artworkService.save(artworkCreateModel));

    }

    @Test
    void findArtworkById_whenExists_shouldReturnArtwork() throws Exception {
        String id = "1";
        Artwork artwork = new Artwork();
        artwork.setImageUrl("/rr/rrr/rrrr/rrrr.jpg");
        artwork.setArtist(new Artist());
        artwork.setCategory(Category.CERAMICS);
        artwork.setDescription("wwe qwoei oqweq e wieoqwep qwe eq ");
        artwork.setName("birds");
        artwork.setPrice(new BigDecimal("200"));
        artwork.setId(id);
        Mockito.when(artworkRepository.findById(id)).thenReturn(Optional.of(artwork));

        ArtworkServiceModel actualArtwork = artworkService.findById(id);

        assertEquals(artwork.getId(), actualArtwork.getId());

    }

    @Test
    void findArtworkById_whenDoesNotExist_shouldThrowException() {
        String id = "invalid id";
        Mockito.when(artworkRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> artworkService.findById(id));

    }

    @Test
    void findAllArtworksByArtistId_whenArtistExistsAndArtworksExist_shouldReturnArtworks() {
        String artistName = "Pesho";
        String id = "2";
        Artwork artwork1 = new Artwork();
        artwork1.setId("1");
        Artwork artwork2 = new Artwork();
        artwork2.setId("2");
        Artist artist = new Artist();
        artist.setName(artistName);
        artist.setId(id);
        artwork1.setArtist(artist);
        artwork2.setArtist(artist);
        artworks.add(artwork1);
        artworks.add(artwork2);
        artist.setArtworks(artworks);
        Mockito.when(artworkRepository.findAllArtworksByArtistId(id)).thenReturn(artworks);

        List<ArtworkServiceModel> actualArtworks = artworkService.findAllArtworksByArtistId(id);
        assertEquals(artworks.size(), actualArtworks.size());
        assertEquals(artworks.get(0).getId(), actualArtworks.get(0).getId());
        assertEquals(artworks.get(1).getId(), actualArtworks.get(1).getId());
    }


    @Test
    void findAllArtworksByArtistId_whenArtistExistsButNoArtworks_shouldReturnEmptyList() {
        String artistName = "Pesho";
        String id = "2";
        Artist artist = new Artist();
        Mockito.when(artworkRepository.findAllArtworksByArtistId(id)).thenReturn(artworks);
        List<ArtworkServiceModel> actualArtworks = artworkService.findAllArtworksByArtistId(id);
        assertEquals(0, actualArtworks.size());

    }


    @Test
    void findAllByCategory_whenArtworksExists_shouldReturnArtworks() {
        Artwork artwork1 = new Artwork();
        artwork1.setCategory(Category.CERAMICS);
        Artwork artwork2 = new Artwork();
        artwork2.setCategory(Category.CERAMICS);
        Artwork artwork3 = new Artwork();
        artwork3.setCategory(Category.DRAWING);

        artworks.add(artwork1);
        artworks.add(artwork2);
        artworks.add(artwork3);
        String category = Category.CERAMICS.name();

        Mockito.when(artworkRepository.findByCategory(Category.CERAMICS))
                .thenReturn(artworks.stream().filter(a -> a.getCategory().name().equals(category)).collect(Collectors.toList()));
        List<ArtworkServiceModel> actualArtworks = artworkService.findAllByCategory(Category.CERAMICS.name());
        assertEquals(2, actualArtworks.size());
        assertEquals(Category.CERAMICS, actualArtworks.get(0).getCategory());
        assertEquals(Category.CERAMICS, actualArtworks.get(1).getCategory());

    }

    @Test
    void writeOffArtwork_whenExistsAndIsAvailable_shouldWriteOff() {
        String id = "3";
        Artwork artwork = new Artwork();
        artwork.setId(id);
        Mockito.when(artworkRepository.findById(id)).thenReturn(Optional.of(artwork));

        artworkService.writeOffArtwork(id);
        assertFalse(artwork.isAvailable());
    }

    @Test
    void delete_whenExist_shouldDelete(){

            String id="1";
            Artwork artwork=new Artwork();
            artwork.setId(id);
            Mockito.when(artworkRepository.findById(id)).thenReturn(Optional.of(artwork)).thenThrow(RuntimeException.class);
            this.artworkService.delete(id);
            assertThrows(RuntimeException.class,
                    ()->this.artworkService.delete(id));
        }
    }

