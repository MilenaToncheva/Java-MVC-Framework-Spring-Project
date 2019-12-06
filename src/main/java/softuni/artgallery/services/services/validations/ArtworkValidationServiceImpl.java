package softuni.artgallery.services.services.validations;

import org.springframework.stereotype.Service;
import softuni.artgallery.constants.artworksMessages.ArtworkErrorMessages;
import softuni.artgallery.error.ArtworkIllegalArgumentsException;
import softuni.artgallery.services.models.ArtworkCreateServiceModel;
import softuni.artgallery.services.services.validations.ArtworkValidationService;

import java.math.BigDecimal;
@Service
public class ArtworkValidationServiceImpl implements ArtworkValidationService {
    @Override
    public boolean isValid(ArtworkCreateServiceModel artworkModel) {
        return isNameValid(artworkModel.getName()) && isImageUrlValid(artworkModel.getName()) && isPriceValid(artworkModel.getPrice()) &&
                isDescriptionValid(artworkModel.getDescription());

    }

    private boolean isNameValid(String name) {
        if (name == null || name.equals("") || name.length() < 2 || name.length() > 30) {
            throw new ArtworkIllegalArgumentsException(ArtworkErrorMessages.ARTWORK_INVALID_NAME);
        }
        return true;
    }

    private boolean isImageUrlValid(String imageUrl) {
        if(imageUrl==null||imageUrl.equals("")){
            throw new ArtworkIllegalArgumentsException(ArtworkErrorMessages.ARTWORK_INVALID_IMAGE_URL);
        }
        return true;
    }

    private boolean isPriceValid(BigDecimal price) {
        if(price==null||price.compareTo(BigDecimal.ZERO)<=0){
            throw new ArtworkIllegalArgumentsException(ArtworkErrorMessages.ARTWORK_INVALID_PRICE);
        }
        return true;
    }

    private boolean isDescriptionValid(String description) {
        if(description==null||description.equals("")||description.length()<10||description.length()>100){
            throw new ArtworkIllegalArgumentsException(ArtworkErrorMessages.ARTWORK_INVALID_DESCRIPTION);
        }
        return true;
    }


}
