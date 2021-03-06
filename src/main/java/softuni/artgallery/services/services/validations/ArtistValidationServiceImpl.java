package softuni.artgallery.services.services.validations;

import org.springframework.stereotype.Service;
import softuni.artgallery.constants.artistMessages.ArtistErrorMessages;
import softuni.artgallery.constants.common.CommonConstants;
import softuni.artgallery.error.ArtistIllegalArgumentsException;
import softuni.artgallery.services.models.ArtistCreateServiceModel;
import softuni.artgallery.services.models.ArtistServiceModel;
import softuni.artgallery.services.services.validations.ArtistValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class ArtistValidationServiceImpl implements ArtistValidationService {
    @Override
    public boolean isValid(ArtistCreateServiceModel artistModel) {
        return isNameValid(artistModel.getName())&&isLivesInValid(artistModel.getLivesIn())&&isEmailValid(artistModel.getEmail())
                &&isHistoryValid(artistModel.getHistory())&&isEducationValid(artistModel.getEducation())&&
                isImageUrlValid(artistModel.getImageUrl());

    }

    private boolean isNameValid(String name) {
        if(name==null||name.equals("")||name.length()<2||name.length()>30){
            throw new ArtistIllegalArgumentsException(ArtistErrorMessages.ARTIST_INVALID_NAME);
        }
        return true;
    }

     private boolean isLivesInValid(String livesIn) {
        if(livesIn==null||livesIn.equals("")||livesIn.length()<3||livesIn.length()>30){
            throw new ArtistIllegalArgumentsException(ArtistErrorMessages.ARTIST_INVALID_LIVES_IN);
        }
        return true;
    }

     private boolean isEmailValid(String email) {

        String ePattern = CommonConstants.EMAIL_PATTERN;
        Pattern pattern = Pattern.compile(ePattern);
        Matcher m = pattern.matcher(email);

        if(!m.matches()){
            throw new ArtistIllegalArgumentsException(ArtistErrorMessages.ARTIST_INVALID_EMAIL);
        }
        return true;
    }

     private boolean isHistoryValid(String history) {
        if(history==null||history.equals("")||history.length()<15||history.length()>200){
            throw new ArtistIllegalArgumentsException(ArtistErrorMessages.ARTIST_INVALID_HISTORY);
        }
        return true;
    }

     private boolean isEducationValid(String education) {
        if(education==null||education.equals("")||education.length()<2||education.length()>30){
            throw new ArtistIllegalArgumentsException(ArtistErrorMessages.ARTIST_INVALID_EDUCATION);
        }
        return true;
    }

     private boolean isImageUrlValid(String imageUrl) {
        if(imageUrl==null||imageUrl.equals("")){
            throw new ArtistIllegalArgumentsException(ArtistErrorMessages.ARTIST_INVALID_IMAGE_URL);
        }
        return true;
    }


}


