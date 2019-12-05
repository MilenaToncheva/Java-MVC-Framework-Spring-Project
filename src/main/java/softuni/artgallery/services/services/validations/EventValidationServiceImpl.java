package softuni.artgallery.services.services.validations;

import org.springframework.stereotype.Service;
import softuni.artgallery.constants.eventMessages.EventErrorMessages;
import softuni.artgallery.error.EventIllegalArgumentException;
import softuni.artgallery.services.models.EventCreateServiceModel;
import softuni.artgallery.services.services.validations.EventValidationService;

import java.time.LocalDateTime;

@Service
public class EventValidationServiceImpl implements EventValidationService {

    @Override
    public boolean isValid(EventCreateServiceModel eventModel) {
return isNameValid(eventModel.getName())&&isDescriptionValid(eventModel.getDescription())&&isImageUrlValid(eventModel.getImageUrl())
        &&isLocationValid(eventModel.getLocation())&&isStartsOnValid(eventModel.getStartsOn())&&isEndsOnValid(eventModel.getEndsOn());
    }
    private boolean isNameValid(String name) {
        if(name==null||name.equals("")||name.length()<5||name.length()>30){
            throw new EventIllegalArgumentException(EventErrorMessages.EVENT_INCORRECT_NAME);
        }
        return true;
    }
    private boolean isDescriptionValid(String description) {
        if(description==null||description.equals("")||description.length()<5||description.length()>200){
            throw new EventIllegalArgumentException(EventErrorMessages.EVENT_INCORRECT_DESCRIPTION);
        }
        return true;
    }

    private boolean isImageUrlValid(String imageUrl) {
        if(imageUrl==null||imageUrl.equals("")){
            throw new EventIllegalArgumentException(EventErrorMessages.EVENT_INCORRECT_IMAGE);
        }
        return true;

    }


    private boolean isLocationValid(String location) {
        if(location==null||location.equals("")||location.length()<5||location.length()>100){
            throw new EventIllegalArgumentException(EventErrorMessages.EVENT_INCORRECT_NAME);
        }
        return true;
    }

    private boolean isStartsOnValid(LocalDateTime startsOn) {
       return startsOn!=null;
    }

    private boolean isEndsOnValid(LocalDateTime endsOn) {
        return endsOn!=null;
    }
}
