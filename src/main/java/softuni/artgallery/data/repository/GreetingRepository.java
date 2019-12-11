package softuni.artgallery.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.artgallery.data.models.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting,String> {

    Greeting findByName(String name);
}
