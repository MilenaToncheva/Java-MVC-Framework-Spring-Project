package softuni.artgallery.web.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstNameValidator implements ConstraintValidator <FirstNameValidation,String>{

    @Override
    public boolean isValid(String firstName, ConstraintValidatorContext constraintValidatorContext) {
        return firstName.length()>=3&&firstName.length()<=15;
    }
}
