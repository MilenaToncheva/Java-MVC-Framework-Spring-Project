package softuni.artgallery.services.validations;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.artgallery.data.models.Category;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.ArtworkCreateServiceModel;
import softuni.artgallery.services.services.validations.ArtworkValidationService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArtworkValidationTest extends ServiceTestBase {
private ArtworkCreateServiceModel artwork;
    @Autowired
    ArtworkValidationService artworkValidationService;

    @Override
    public void beforeEach(){
        artwork=new ArtworkCreateServiceModel();
        artwork.setArtistName("Misho");
        artwork.setName("Creepy");
        artwork.setPrice(new BigDecimal("100"));
        artwork.setDescription("Ceramic handmade scupture...");
        artwork.setCategory(Category.CERAMICS);
        artwork.setImageUrl("https://res.cloudinary.com/mt-art-gallery/image/upload/v1576099099/ptr2esthbq64lz0jjnhz.jpg");

    }
    @Test
    void isValid_whenValid_shouldReturnTrue(){

        Assert.assertTrue(this.artworkValidationService.isValid(artwork));

    }
    @Test
    void  isValid_whenNameInvalid_ShouldThrowException(){
        artwork.setName(null);
        assertThrows(RuntimeException.class,
                ()->artworkValidationService.isValid(artwork));
    }
}
