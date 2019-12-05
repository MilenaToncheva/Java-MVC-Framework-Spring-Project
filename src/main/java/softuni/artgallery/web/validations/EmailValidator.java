package softuni.artgallery.web.validations;

import softuni.artgallery.constants.common.CommonConstants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation,String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.matches(CommonConstants.EMAIL_PATTERN);
    }
}
