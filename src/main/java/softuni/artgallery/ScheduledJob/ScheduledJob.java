package softuni.artgallery.ScheduledJob;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public interface ScheduledJob {
    @Async
    void scheduledJob();
}
