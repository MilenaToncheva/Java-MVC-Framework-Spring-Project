package softuni.artgallery.web.validations;

import softuni.artgallery.constants.userMessages.UserRegistrationViolationMessages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Constraint(validatedBy = FirstNameValidator.class)
public @interface FirstNameValidation {
    String message() default UserRegistrationViolationMessages.USER_INCORRECT_FIRST_NAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
