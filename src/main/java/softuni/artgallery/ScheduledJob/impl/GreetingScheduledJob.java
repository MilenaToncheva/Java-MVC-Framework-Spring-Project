package softuni.artgallery.ScheduledJob.impl;

import ch.qos.logback.core.util.FixedDelay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import softuni.artgallery.ScheduledJob.ScheduledJob;
import softuni.artgallery.constants.common.CommonConstants;
import softuni.artgallery.data.repository.GreetingRepository;
import softuni.artgallery.services.services.GreetingService;

@Component
public class GreetingScheduledJob implements ScheduledJob {


    private final GreetingService greetingService;

    @Autowired
    public GreetingScheduledJob(GreetingService greetingService) {

        this.greetingService = greetingService;
    }


    @Override
    @Scheduled(fixedDelay = 60000)
    public void scheduledJob() {
       this.greetingService.reduceDuration(CommonConstants.GREETING_NAME);


    }
}
