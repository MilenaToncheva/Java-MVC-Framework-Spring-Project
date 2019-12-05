package softuni.artgallery.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.artgallery.data.models.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByAuthority(String authority);
}

