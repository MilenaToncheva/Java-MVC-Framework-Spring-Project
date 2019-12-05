package softuni.artgallery.web.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LastNameValidator implements ConstraintValidator<LastNameValidation,String> {
    @Override
    public boolean isValid(String lastName, ConstraintValidatorContext constraintValidatorContext) {
        return lastName.length()>=3&&lastName.length()<=15;
    }
}
