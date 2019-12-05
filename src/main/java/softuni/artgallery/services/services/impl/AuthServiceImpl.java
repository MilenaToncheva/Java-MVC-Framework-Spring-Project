package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import softuni.artgallery.constants.userMessages.UserErrorMessages;
import softuni.artgallery.data.models.User;
import softuni.artgallery.data.repository.UserRepository;
import softuni.artgallery.error.UserRegistrationException;
import softuni.artgallery.services.models.UserRegisterServiceModel;
import softuni.artgallery.services.models.UserServiceModel;
import softuni.artgallery.services.services.AuthService;
import softuni.artgallery.services.services.AuthValidationService;
import softuni.artgallery.services.services.RoleService;

import java.util.LinkedHashSet;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final AuthValidationService authValidationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, AuthValidationService authValidationService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.authValidationService = authValidationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    @Override
    public UserServiceModel register(UserRegisterServiceModel userRegisterServiceModel) {
        this.roleService.seedRolesInDb();
        if (this.userRepository.count() == 0) {
            userRegisterServiceModel.setAuthorities(this.roleService.findAllRoles());
        } else {
            userRegisterServiceModel.setAuthorities(new LinkedHashSet<>());
            userRegisterServiceModel.getAuthorities()
                    .add(this.roleService.findByAuthority("ROLE_USER"));
        }

        if (!authValidationService.isValid(userRegisterServiceModel)) {
            throw new UserRegistrationException(UserErrorMessages.USER_REGISTER_INCORRECT_INPUT);
      }

            User user = this.modelMapper.map(userRegisterServiceModel, User.class);
            user.setPassword(this.bCryptPasswordEncoder.encode(userRegisterServiceModel.getPassword()));


            return this.modelMapper.map(this.userRepository.saveAndFlush(user),
                    UserServiceModel.class);


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(UserErrorMessages.USER_NOT_FOUND_Message));

        return user;
    }
}
