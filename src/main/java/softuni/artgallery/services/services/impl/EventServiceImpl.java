package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.artgallery.constants.eventMessages.EventErrorMessages;
import softuni.artgallery.data.models.Event;
import softuni.artgallery.data.repository.EventRepository;
import softuni.artgallery.error.EventIllegalArgumentException;
import softuni.artgallery.error.EventNotFoundException;
import softuni.artgallery.services.models.EventCreateServiceModel;
import softuni.artgallery.services.models.EventServiceModel;
import softuni.artgallery.services.services.EventService;
import softuni.artgallery.services.services.validations.EventValidationService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final EventValidationService eventValidationService;


    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper, EventValidationService eventValidationService) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.eventValidationService = eventValidationService;
    }

    @Override
    public void register(EventCreateServiceModel eventCreateServiceModel) {

        if (!this.eventValidationService.isValid(eventCreateServiceModel)) {
            throw new EventIllegalArgumentException(EventErrorMessages.INVALID_INPUT);
        }
        Event event = this.modelMapper.map(eventCreateServiceModel, Event.class);
        this.eventRepository.saveAndFlush(event);
    }

    @Override
    public void edit(String id, EventServiceModel eventServiceModel) {
        Event event = this.eventRepository.findById(id).
                orElseThrow(() -> new EventNotFoundException(EventErrorMessages.EVENT_NOT_FOUND));
        event.setName(eventServiceModel.getName());
        event.setLocation(eventServiceModel.getLocation());
        event.setDescription(eventServiceModel.getDescription());
        event.setEndsOn(eventServiceModel.getEndsOn());
        event.setStartsOn(eventServiceModel.getStartsOn());
        if (!this.eventValidationService.isValid(this.modelMapper.map(event, EventCreateServiceModel.class))) {
            throw new EventIllegalArgumentException(EventErrorMessages.INVALID_INPUT);
        }
        this.eventRepository.saveAndFlush(event);
    }

    @Override
    public void delete(String id) {
        Event event = this.eventRepository.findById(id).orElseThrow(() ->
                new EventNotFoundException(EventErrorMessages.EVENT_NOT_FOUND));

         this.eventRepository.delete(event);

    }

    @Override
    public EventServiceModel findByName(String name) {
        Event event = null;
        try {
            event = this.eventRepository.findByName(name);
            return this.modelMapper.map(event, EventServiceModel.class);
        } catch (Exception e) {
            throw new EventNotFoundException(EventErrorMessages.EVENT_NOT_FOUND);
        }
    }

    @Override
    public EventServiceModel findById(String id) {
        Event event = this.eventRepository.findById(id).orElseThrow(() ->
                new EventNotFoundException(EventErrorMessages.EVENT_NOT_FOUND));
        return this.modelMapper.map(event, EventServiceModel.class);
    }

    @Override
    public List<EventServiceModel> findAll() {

        return Arrays.stream(this.modelMapper.map(this.eventRepository.findAll(), EventServiceModel[].class))
                .collect(Collectors.toList());
    }
}
