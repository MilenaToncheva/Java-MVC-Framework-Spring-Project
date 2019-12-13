package softuni.artgallery.services.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.artgallery.data.models.Artwork;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.ArtistServiceModel;
import softuni.artgallery.services.models.ArtworkCartServiceModel;
import softuni.artgallery.services.models.ArtworkServiceModel;

import javax.servlet.http.HttpSession;

public class CartServiceTest extends ServiceTestBase {
    @MockBean
    ArtworkService artworkService;
    @MockBean
    HttpSession httpSession;

    @Autowired
    CartService cartService;

    //@Test
    //void addArtworkToCart_whenArtworkExistsAndNotInCart_shouldAdd() throws Exception {
    //    String id="1";
    //    ArtworkCartServiceModel artwork=new ArtworkCartServiceModel();
    //    artwork.setId(id);
    //    ArtworkServiceModel artworkServiceModel=new ArtworkServiceModel();
    //    Mockito.when(artworkService.findById(id)).thenReturn(artworkServiceModel);
    //}

}
