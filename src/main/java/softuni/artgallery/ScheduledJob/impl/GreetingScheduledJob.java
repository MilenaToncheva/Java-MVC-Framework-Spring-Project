package softuni.artgallery.ScheduledJob.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import softuni.artgallery.ScheduledJob.ActiveGreeting;
import softuni.artgallery.ScheduledJob.ScheduledJob;
import softuni.artgallery.services.services.GreetingService;

@Component
public class GreetingScheduledJob implements ScheduledJob {

    private final GreetingService greetingService;

    @Autowired
    public GreetingScheduledJob(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    @Scheduled(fixedDelay = 1000)
    public void scheduledJob() {
        this.greetingService.reduceDuration(ActiveGreeting.getName());


    }
}
