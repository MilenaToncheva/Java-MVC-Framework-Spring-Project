package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.artgallery.constants.artistMessages.ArtistErrorMessages;
import softuni.artgallery.constants.userMessages.UserErrorMessages;
import softuni.artgallery.data.models.Artist;
import softuni.artgallery.data.repository.ArtistRepository;
import softuni.artgallery.error.ArtistNotDeletedException;
import softuni.artgallery.error.ArtistNotFoundException;
import softuni.artgallery.error.UserIllegalArgumentsException;
import softuni.artgallery.services.models.ArtistCreateServiceModel;
import softuni.artgallery.services.models.ArtistServiceModel;
import softuni.artgallery.services.services.ArtistService;
import softuni.artgallery.services.services.ArtistValidationService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final ModelMapper modelMapper;
    private final ArtistValidationService artistValidationService;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository, ModelMapper modelMapper, ArtistValidationService artistValidationService) {
        this.artistRepository = artistRepository;
        this.modelMapper = modelMapper;
        this.artistValidationService = artistValidationService;
    }

    @Override
    public void register(ArtistCreateServiceModel artistCreateServiceModel) {
        if (!this.artistValidationService.isValid(artistCreateServiceModel)) {
            throw new UserIllegalArgumentsException(UserErrorMessages.USER_INCORRECT_INPUT);
        }
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
        if (!this.artistValidationService.isValid(this.modelMapper.map(artist, ArtistCreateServiceModel.class))) {
            throw new UserIllegalArgumentsException(UserErrorMessages.USER_INCORRECT_INPUT);
        }
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
        try {
            Artist artist = this.artistRepository.findByName(name);
            return this.modelMapper.map(this.artistRepository.findByName(name), ArtistServiceModel.class);
        } catch (Exception e) {
            throw new ArtistNotFoundException(ArtistErrorMessages.ARTIST_NOT_FOUND);
        }
    }
        @Override
        public ArtistServiceModel findById(String id){
            Artist artist = this.artistRepository.findById(id).orElseThrow(()
                    -> new ArtistNotFoundException(ArtistErrorMessages.ARTIST_NOT_FOUND));
            return this.modelMapper.map(artist, ArtistServiceModel.class);
        }

        @Override
        public List<ArtistServiceModel> findAll () {
            return Arrays.stream(this.modelMapper.map(this.artistRepository.findAll(),
                    ArtistServiceModel[].class)).collect(Collectors.toList());
        }
    }
