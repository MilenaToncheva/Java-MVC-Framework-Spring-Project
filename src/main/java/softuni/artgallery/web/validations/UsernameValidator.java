package softuni.artgallery.web.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UsernameValidation,String> {
    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return  username.length()>=4&&username.length()<=30;
    }
}
