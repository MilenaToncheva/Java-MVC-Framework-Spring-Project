package softuni.artgallery.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.artgallery.data.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event,String> {
    @Query("SELECT e FROM Event e Where e.name=:name")
    Event findByName(@Param(value = "name") String name);
}
