package softuni.artgallery.web.models.auth;

import softuni.artgallery.web.validations.*;

public class UserRegisterModel {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;

    public UserRegisterModel() {
    }

    @UsernameValidation
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FirstNameValidation
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @LastNameValidation
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @EmailValidation
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PasswordValidation
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PasswordValidation
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
