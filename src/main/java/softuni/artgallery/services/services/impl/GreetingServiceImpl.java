package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.artgallery.constants.greetingMessages.GreetingErrorMessages;
import softuni.artgallery.data.models.Greeting;
import softuni.artgallery.data.repository.GreetingRepository;
import softuni.artgallery.error.GreetingAlreadyExistsException;
import softuni.artgallery.error.GreetingNotFoundException;
import softuni.artgallery.services.models.GreetingServiceModel;
import softuni.artgallery.services.services.GreetingService;

@Service
public class GreetingServiceImpl implements GreetingService {
    private final GreetingRepository greetingRepository;
    private final ModelMapper modelMapper;

    public GreetingServiceImpl(GreetingRepository greetingRepository, ModelMapper modelMapper) {
        this.greetingRepository = greetingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void reduceDuration(String name) {
        Greeting greeting = this.greetingRepository.findByName(name);
        if (greeting != null && greeting.getDuration() > 0) {
            greeting.setDuration(greeting.getDuration() - 1);
            this.greetingRepository.saveAndFlush(greeting);
        }
    }

    @Override
    public GreetingServiceModel findByName(String name) {
        try {
            Greeting greeting = this.greetingRepository.findByName(name);
        } catch (Exception ex) {
            throw new GreetingNotFoundException(GreetingErrorMessages.GREETING_NOT_FOUND);
        }
        return this.modelMapper.map(this.greetingRepository.findByName(name), GreetingServiceModel.class);
    }

    @Override
    public void createGreeting(GreetingServiceModel greetingServiceModel) {
        if (!this.isUnique(greetingServiceModel.getName())) {
            throw new GreetingAlreadyExistsException(GreetingErrorMessages.GREETING_ALREADY_EXISTS);
        }
        Greeting greeting = this.modelMapper.map(greetingServiceModel, Greeting.class);
        this.greetingRepository.saveAndFlush(greeting);
    }

    @Override
    public boolean isUnique(String name) {
        Greeting greeting=this.greetingRepository.findByName(name);
        return greeting == null;
    }
}
