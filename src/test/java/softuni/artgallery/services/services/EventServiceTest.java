package softuni.artgallery.services.services;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.artgallery.data.models.Artist;
import softuni.artgallery.data.models.Event;
import softuni.artgallery.data.repository.EventRepository;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.ArtistCreateServiceModel;
import softuni.artgallery.services.models.ArtistServiceModel;
import softuni.artgallery.services.models.EventCreateServiceModel;
import softuni.artgallery.services.models.EventServiceModel;
import softuni.artgallery.services.services.validations.EventValidationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventServiceTest extends ServiceTestBase {
    List<Event> events;
    @MockBean
    EventRepository eventRepository;

    @MockBean
    EventValidationService eventValidationService;

    @Autowired
    EventService eventService;

    @Override
    protected void beforeEach() {
        events = new ArrayList<>();
    }



   // @Test
    // void registerEvent_whenAllIsValid_shouldRegister() {
    //     EventCreateServiceModel eventModel = new EventCreateServiceModel();
    //     eventModel.setName("The swimmer");
    //     eventModel.setLocation("Sofia");
    //     eventModel.setImageUrl("/www/w///w///w////w/");
    //     eventModel.setDescription("qwoei p qweo pwei weoqi pweip qweip");
    //     LocalDateTime date = LocalDateTime.now();
    //     eventModel.setStartsOn(date);
    //     eventModel.setEndsOn(date);
//
    //     Mockito.when(eventValidationService.isValid(eventModel)).thenReturn(true);
    //     eventService.create(eventModel);
//
    //     ArgumentCaptor<Event> argument = ArgumentCaptor.forClass(Event.class);
    //     Mockito.verify(eventRepository).saveAndFlush(argument.capture());
//
    //     Event event = argument.getValue();
    //     assertNotNull(event);
//
//
    // }

    @Test
    void registerEvent_whenInvalid_shouldThrowException() {
        EventCreateServiceModel eventModel = new EventCreateServiceModel();
        eventModel.setName("");
        eventModel.setLocation("Sofia");
        eventModel.setImageUrl("/www/w///w///w////w/");
        eventModel.setDescription("qwoei p qweo pwei weoqi pweip qweip");
        LocalDateTime date = LocalDateTime.now();
        eventModel.setStartsOn(date);
        eventModel.setEndsOn(date);
        Mockito.when(eventValidationService.isValid(eventModel)).thenReturn(false);
        assertThrows(RuntimeException.class,
                () -> eventService.register(eventModel));
    }
    @Test
    void registerEvent_withIncorrectData_shouldThrowException() {
        EventCreateServiceModel eventModel = new EventCreateServiceModel();
        eventModel.setName("The swimmer");
        eventModel.setLocation("Sofia");
        eventModel.setImageUrl("/www/w///w///w////w/");
        eventModel.setDescription("qwoei p qweo pwei weoqi pweip qweip");
        LocalDateTime date = LocalDateTime.now();
        eventModel.setStartsOn(date);
        eventModel.setEndsOn(date);


        Mockito.when(eventValidationService.isValid(eventModel)).thenReturn(false);

        assertThrows(RuntimeException.class,
                () -> eventService.register(eventModel));

    }

    @Test
    void findByName_whenExists_shouldReturnEvent(){
        String name="Morgana";
        Event event=new Event();
        event.setName(name);

        Mockito.when(eventRepository.findByName(name))
                .thenReturn(event);
        EventServiceModel eventServiceModel=eventService.findByName(name);

        assertEquals(event.getName(),eventServiceModel.getName());
    }
    @Test
    void findById_whenExists_shouldReturnEvent(){
        String id="1";
        Event event=new Event();
        event.setId(id);
       Mockito.when(eventRepository.findById(id)).thenReturn(Optional.of(event));

        EventServiceModel eventServiceModel=eventService.findById(id);
        assertEquals(event.getId(),eventServiceModel.getId());
    }
    @Test
    void findEventById_whenEventDoesNotExist_shouldThrowException(){
        String id="1";
        Event event=new Event();
        event.setId(id);

        Mockito.when(eventRepository.findById(id)).thenReturn(Optional.empty());


        assertThrows(RuntimeException.class,
                ()->eventService.findById(id));
    }

    @Test
    void findAllArtist_whenArtistsExist_shouldReturnArtists(){
        Event event1=new Event();
        Event event2=new Event();
        events.add(event1);
        events.add(event2);

        Mockito.when(eventRepository.findAll()).thenReturn(events);
        List<EventServiceModel> artistsAll=eventService.findAll();
        Assert.assertEquals(events.size(),artistsAll.size());


    }
    @Test
    void delete_whenNotExist_shouldThrowException(){
        String id="4";
        Mockito.when(eventRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                ()->eventService.delete(id));

    }
}
