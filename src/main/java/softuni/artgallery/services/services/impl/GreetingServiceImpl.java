package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.artgallery.ScheduledJob.ActiveGreeting;
import softuni.artgallery.constants.greetingMessages.GreetingErrorMessages;
import softuni.artgallery.data.models.Greeting;
import softuni.artgallery.data.repository.GreetingRepository;
import softuni.artgallery.error.GreetingAlreadyExistsException;
import softuni.artgallery.error.GreetingNotFoundException;
import softuni.artgallery.services.models.GreetingServiceModel;
import softuni.artgallery.services.services.GreetingService;
import softuni.artgallery.web.models.greeting.GreetingEditModel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        if ( greeting!=null &&greeting.getDuration() > 0) {
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

    @Override
    public void edit(String id, GreetingEditModel greetingEditModel) {
        Greeting greeting=this.greetingRepository.findById(id)
                .orElseThrow(()->new GreetingNotFoundException(GreetingErrorMessages.GREETING_NOT_FOUND));

        greeting.setName(greetingEditModel.getName());
        greeting.setDuration(greetingEditModel.getDuration());
        //todo validate
        this.greetingRepository.saveAndFlush(greeting);
    }

    @Override
    public List<GreetingServiceModel> findAll() {
        List<Greeting>greetings=this.greetingRepository.findAll();
        return Arrays.stream(this.modelMapper.map(greetings, GreetingServiceModel[].class))
                .collect(Collectors.toList());
    }

    @Override
    public void enableGreeting(String id) {
        Greeting greeting=this.greetingRepository.findById(id)
                .orElseThrow(()->new GreetingNotFoundException(GreetingErrorMessages.GREETING_NOT_FOUND));
        greeting.setEnabled(true);
        ActiveGreeting
                .setName(greeting.getName());
        this.greetingRepository.saveAndFlush(greeting);
    }

    @Override
    public void disableGreeting(String id) {
        Greeting greeting=this.greetingRepository.findById(id)
                .orElseThrow(()->new GreetingNotFoundException(GreetingErrorMessages.GREETING_NOT_FOUND));
        greeting.setEnabled(false);
        ActiveGreeting.setName(null);
        this.greetingRepository.saveAndFlush(greeting);
    }

    @Override
    public void deleteGreeting(String id) {
        Greeting greeting=this.greetingRepository.findById(id)
                .orElseThrow(()->new GreetingNotFoundException(GreetingErrorMessages.GREETING_NOT_FOUND));
        this.greetingRepository.delete(greeting);

    }

    @Override
    public GreetingServiceModel findById(String id) {
        Greeting greeting=this.greetingRepository.findById(id)
                .orElseThrow(()->new GreetingNotFoundException(GreetingErrorMessages.GREETING_NOT_FOUND));
        return this.modelMapper.map(greeting,GreetingServiceModel.class);
    }
}
