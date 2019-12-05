package softuni.artgallery.constants.userMessages;

public class UserRegistrationViolationMessages {

    public static final String PASSWORD_PATTERN="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,16}$";
    public static final String USER_INCORRECT_USERNAME="Username should be between 4 and 30 symbols. ";
    public static final String USER_INCORRECT_FIRST_NAME="First name should be between 3 and 15 symbols. ";
    public static final String USER_INCORRECT_LAST_NAME="Last name should be between 3 and 15 symbols. ";
    public static final String USER_INCORRECT_EMAIL="Incorrect email. ";
    public static final String USER_INCORRECT_PASSWORD="Password should contain at least 1 lowercase letter, 1 digit, 1 special symbol(@#$%^&+=), 1 uppercase letter and should be between 6 and 16 symbols long. ";


}
