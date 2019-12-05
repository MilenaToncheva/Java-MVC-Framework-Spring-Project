package softuni.artgallery.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.artgallery.data.models.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    @Query("SELECT o FROM Order o where o.user.username=:username ")
    List<Order> findAllByUsername(@Param(value = "username") String username);
}
