package softuni.artgallery.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.artgallery.data.models.Artist;
@Repository
public interface ArtistRepository extends JpaRepository<Artist,String> {
    Artist findByName(String name);
}
