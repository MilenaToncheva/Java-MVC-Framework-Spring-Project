package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.artgallery.constants.artistMessages.ArtistErrorMessages;
import softuni.artgallery.data.models.Artist;
import softuni.artgallery.data.repository.ArtistRepository;
import softuni.artgallery.error.ArtistNotDeletedException;
import softuni.artgallery.error.ArtistNotFoundException;
import softuni.artgallery.services.models.ArtistCreateServiceModel;
import softuni.artgallery.services.models.ArtistServiceModel;
import softuni.artgallery.services.services.ArtistService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository, ModelMapper modelMapper) {
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(ArtistCreateServiceModel artistCreateServiceModel) {
        this.artistRepository.saveAndFlush(this.modelMapper.map(artistCreateServiceModel, Artist.class));

    }

    @Override
    public void edit(String id, ArtistServiceModel artistServiceModel) {
        Artist artist = this.artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException(ArtistErrorMessages.ARTIST_NOT_FOUND));
        artist.setName(artistServiceModel.getName());
        artist.setLivesIn(artistServiceModel.getLivesIn());
        artist.setEducation(artistServiceModel.getEducation());
        artist.setEmail(artistServiceModel.getEmail());
        artist.setHistory(artistServiceModel.getHistory());
        artist.setImageUrl(artistServiceModel.getImageUrl());
        this.artistRepository.save(artist);

    }

    @Override
    public void delete(String id) throws ArtistNotDeletedException {
        Artist artist = this.artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException(ArtistErrorMessages.ARTIST_NOT_FOUND));
        try {
            this.artistRepository.delete(artist);
        } catch (Exception e) {
            throw new ArtistNotDeletedException(ArtistErrorMessages.ARTIST_NOT_DELETED);
        }
    }

    @Override
    public ArtistServiceModel findByName(String name) {

        return this.modelMapper.map(this.artistRepository.findByName(name), ArtistServiceModel.class);
    }

    @Override
    public ArtistServiceModel findById(String id) {
        Artist artist = this.artistRepository.findById(id).orElseThrow(() -> new ArtistNotFoundException(ArtistErrorMessages.ARTIST_NOT_FOUND));
        return this.modelMapper.map(artist, ArtistServiceModel.class);
    }

    @Override
    public List<ArtistServiceModel> findAll() {
        return Arrays.stream(this.modelMapper.map(this.artistRepository.findAll(), ArtistServiceModel[].class)).collect(Collectors.toList());
    }
}
