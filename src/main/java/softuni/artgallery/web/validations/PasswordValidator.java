package softuni.artgallery.web.validations;

import softuni.artgallery.constants.userMessages.UserRegistrationViolationMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValidation,String> {
    private Matcher matcher;
    private Pattern pattern;
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        this.pattern=Pattern.compile(UserRegistrationViolationMessages.PASSWORD_PATTERN);
        this.matcher=pattern.matcher(password);
        return matcher.matches();
    }
}
