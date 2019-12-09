package softuni.artgallery.services.validations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.ArtistCreateServiceModel;
import softuni.artgallery.services.services.validations.ArtistValidationService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArtistValidationTest extends ServiceTestBase {
    ArtistCreateServiceModel artistModel;
    @Autowired
    ArtistValidationService artistValidationService;

    @Override
    protected void beforeEach() {
        artistModel = new ArtistCreateServiceModel();
        artistModel.setName("Ana");
        artistModel.setEmail("ana@abv.bg");
        artistModel.setImageUrl("/dff/fff/fff/f/f.jpg");
        artistModel.setEducation("SoftUNi");
        artistModel.setHistory("bla bla bla ...");
        artistModel.setLivesIn("Sofia");

    }

    @Test
    void isValid_whenAllIsValid_shouldReturnTrue() {
        boolean isValid = artistValidationService.isValid(artistModel);
        assertTrue(isValid);

    }
    @Test
    void isValid_whenNameIsNull_shouldThrowException() {
        artistModel.setName(null);
        assertThrows(RuntimeException.class,
                ()->artistValidationService.isValid(artistModel));

    }
    @Test
    void isValid_whenEmailIsInvalid_shouldThrowException() {
        artistModel.setEmail("ana.ana");
        assertThrows(RuntimeException.class,
                ()->artistValidationService.isValid(artistModel));

    }
 @Test
    void IsValid_whenLivesInIsNull_shouldThrowException(){
        artistModel.setLivesIn(null);
     assertThrows(RuntimeException.class,
             ()->artistValidationService.isValid(artistModel));
 }
    @Test
    void IsValid_whenHistoryInIsNull_shouldThrowException(){
        artistModel.setHistory(null);
        assertThrows(RuntimeException.class,
                ()->artistValidationService.isValid(artistModel));
    }

    @Test
    void IsValid_whenEducationIsNull_shouldThrowException(){
        artistModel.setEducation(null);
        assertThrows(RuntimeException.class,
                ()->artistValidationService.isValid(artistModel));
    }

    @Test
    void IsValid_whenImageUrlIsNull_shouldThrowException(){
        artistModel.setImageUrl(null);
        assertThrows(RuntimeException.class,
                ()->artistValidationService.isValid(artistModel));
    }

}
