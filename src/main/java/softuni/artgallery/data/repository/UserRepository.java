package softuni.artgallery.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.artgallery.data.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
    User findByUsernameAndPassword(@Param(value = "username") String username,@Param(value = "password")String password);

    User findByEmail(String email);
}
