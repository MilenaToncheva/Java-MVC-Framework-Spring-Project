package softuni.artgallery.services.services;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.artgallery.data.models.Greeting;
import softuni.artgallery.data.models.User;
import softuni.artgallery.data.repository.GreetingRepository;
import softuni.artgallery.services.base.ServiceTestBase;
import softuni.artgallery.services.models.GreetingServiceModel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GreetingServiceTest extends ServiceTestBase {
    @MockBean
    GreetingRepository greetingRepository;

    @Autowired
    GreetingService greetingService;

    @Test
    void reduceDuration_whenGreetingExistsAndDurationGraterThanZero_ShouldReduce() {
        String name = "Christmas";
        Greeting greeting = new Greeting();
        greeting.setId("1");
        greeting.setName(name);
        greeting.setMessage1("ho");
        greeting.setMessage2("hohoho");
        greeting.setDuration(10);
        Mockito.when(this.greetingRepository.findByName(name)).thenReturn(greeting);

        this.greetingService.reduceDuration(name);
        ArgumentCaptor<Greeting> argument = ArgumentCaptor.forClass(Greeting.class);
        Mockito.verify(this.greetingRepository).saveAndFlush(argument.capture());
        Greeting gr = argument.getValue();
        Assert.assertEquals(9, gr.getDuration());

    }

    @Test
    void findByName_whenGreetingExists_shouldReturnGreeting() {
        String name = "Christmas";
        Greeting greeting = new Greeting();
        greeting.setName(name);
        Mockito.when(this.greetingRepository.findByName(name)).thenReturn(greeting);
        GreetingServiceModel result = this.greetingService.findByName(name);
        Assert.assertEquals(greeting.getName(), result.getName());
    }

   // @Test
   //void createGreeting_whenValidInput_shouldCreate() {
   //    String name = "Christmas";
   //    GreetingServiceModel greeting = new GreetingServiceModel();
   //    greeting.setId("1");
   //    greeting.setName(name);
   //    greeting.setMessage1("ho");
   //    greeting.setMessage2("hohoho");
   //    greeting.setDuration(10);
   //    Mockito.when(this.greetingRepository.findByName(name)).thenReturn(null);
   //    Mockito.when(this.greetingService.isUnique(name)).thenReturn(true);
   //    this.greetingService.createGreeting(greeting);


   //    ArgumentCaptor<Greeting> argument = ArgumentCaptor.forClass(Greeting.class);
   //    Mockito.verify(greetingRepository).saveAndFlush(argument.capture());


   //    Greeting result = argument.getValue();
   //    assertNotNull(result);
   //}

    @Test
    void isUnique_whenNoGreeting_shouldReturnTrue(){
        String name="Xmas";
        Mockito.when(this.greetingRepository.findByName(name)).thenReturn(null);
        Boolean result=this.greetingService.isUnique(name);
        assertEquals(true,result);
    }
}