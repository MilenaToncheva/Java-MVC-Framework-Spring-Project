package softuni.artgallery.services.models;

import java.util.List;

public class ArtistServiceModel {
    private String id;
    private String name;
    private String livesIn;
    private String history;
    private String education;
    private String imageUrl;
    private String email;
    private List<ArtworkServiceModel> artworkServiceModelList;

    public ArtistServiceModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ArtworkServiceModel> getArtworkServiceModelList() {
        return artworkServiceModelList;
    }

    public void setArtworkServiceModelList(List<ArtworkServiceModel> artworkServiceModelList) {
        this.artworkServiceModelList = artworkServiceModelList;
    }

}
