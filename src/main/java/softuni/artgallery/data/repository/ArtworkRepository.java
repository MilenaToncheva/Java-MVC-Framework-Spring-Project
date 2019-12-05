package softuni.artgallery.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.artgallery.data.models.Artwork;
import softuni.artgallery.data.models.Category;
import softuni.artgallery.data.models.User;

import java.util.List;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork,String>{
    @Query("SELECT a FROM Artwork a WHERE a.artist.id=:artistId")
        List<Artwork> findAllArtworksByArtistId(@Param(value="artistId")String artistId);
@Query("SELECT a FROM Artwork a WHERE a.category=:category")
    List<Artwork> findByCategory(@Param(value = "category") Category category);
}