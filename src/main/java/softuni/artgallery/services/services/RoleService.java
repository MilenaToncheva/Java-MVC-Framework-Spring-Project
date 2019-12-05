package softuni.artgallery.services.services;

import softuni.artgallery.services.models.RoleServiceModel;
import softuni.artgallery.services.models.UserServiceModel;

import java.util.Set;

public interface RoleService {

    void seedRolesInDb();


    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String authority);
}
