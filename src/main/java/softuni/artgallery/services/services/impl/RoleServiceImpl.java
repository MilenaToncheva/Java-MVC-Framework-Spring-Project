package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.artgallery.data.models.Role;
import softuni.artgallery.data.repository.RoleRepository;
import softuni.artgallery.services.models.RoleServiceModel;
import softuni.artgallery.services.models.UserServiceModel;
import softuni.artgallery.services.services.RoleService;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedRolesInDb() {
        if (roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Role("ROLE_USER"));
            this.roleRepository.saveAndFlush(new Role("ROLE_MODERATOR"));
            this.roleRepository.saveAndFlush(new Role("ROLE_ADMIN"));
            this.roleRepository.saveAndFlush(new Role("ROLE_ROOT"));
        }
    }




    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return Arrays.stream(this.modelMapper.map(this.roleRepository.findAll(), RoleServiceModel[].class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.modelMapper.map(this.roleRepository.findByAuthority(authority),RoleServiceModel.class);
    }
}
