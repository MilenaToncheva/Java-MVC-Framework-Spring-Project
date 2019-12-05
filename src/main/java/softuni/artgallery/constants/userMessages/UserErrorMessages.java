package softuni.artgallery.constants.userMessages;

public class UserErrorMessages {
    public static final String USER_NOT_FOUND ="User was not found!";
    public static final String USER_PASSWORDS_DO_NOT_MATCH = "User password and confirm password don't match!";
    public static final String USER_INCORRECT_PASSWORD = "Incorrect password! Password should contain at least 1 lowercase letter," +
                                                         " 1 digit, 1 special symbol(@#$%^&+=)," +
                                                         " 1 uppercase letter and should be between 6 and 16 symbols long.";
    public static final String USER_NOT_DELETED ="User has orders and cannot be deleted." ;
    public static final String USER_WITH_USERNAME_ALREADY_EXISTS = "This username is not free! Please try with another username.";
    public static final String USER_EMAIL_ALREADY_EXISTS ="The email already exists! Please try with another one." ;
    public static final String USER_INCORRECT_INPUT = "Incorrect input for User register";
    public static final String USER_INCORRECT_USERNAME ="Username should be between 4 and 30 symbols!" ;
    public static final String USER_INCORRECT_FIRST_NAME ="User first name should be between 3 and 15 letters!" ;
    public static final String USER_INCORRECT_LAST_NAME ="User last name should be between 3 and 15 letters!" ;
    public static final String USER_INVALID_EMAIL ="User email is not valid!" ;
}
