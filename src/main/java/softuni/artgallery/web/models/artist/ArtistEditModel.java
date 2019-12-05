package softuni.artgallery.web.models.artist;

import softuni.artgallery.constants.artistMessages.ArtistRegisterViolationMessages;
import softuni.artgallery.constants.common.CommonConstants;

import javax.validation.constraints.Pattern;

public class ArtistEditModel {
    private String name;
    private String livesIn;
    private String history;
    private String education;
    private String imageUrl;
    private String email;

    public ArtistEditModel() {
    }
    @Pattern(regexp = "[a-zA-Z]{2,45}", message = ArtistRegisterViolationMessages.ARTIST_INCORRECT_NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLivesIn() {
        return livesIn;
    }

    public void setLivesIn(String livesIn) {
        this.livesIn = livesIn;
    }
    @Pattern(regexp = "[.]{1,255}",message = ArtistRegisterViolationMessages.ARTIST_NOT_EMPTY_HISTORY)
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Pattern(regexp = CommonConstants.EMAIL_PATTERN, message = ArtistRegisterViolationMessages.ARTIST_INCORRECT_EMAIL)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
