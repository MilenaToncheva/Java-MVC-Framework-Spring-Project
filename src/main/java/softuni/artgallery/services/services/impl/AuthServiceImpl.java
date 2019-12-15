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
import softuni.artgallery.services.services.validations.AuthValidationService;
import softuni.artgallery.services.services.RoleService;
import softuni.artgallery.services.services.validations.EscapeHtmlService;

import java.util.LinkedHashSet;

@Service
public class AuthServiceImpl implements AuthService  {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final AuthValidationService authValidationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EscapeHtmlService escapeHtmlService;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, AuthValidationService authValidationService, BCryptPasswordEncoder bCryptPasswordEncoder, EscapeHtmlService escapeHtmlService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.authValidationService = authValidationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        this.escapeHtmlService = escapeHtmlService;
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
            throw new UserRegistrationException(UserErrorMessages.USER_INCORRECT_INPUT);
        }

      // escapeHtmlInput(userRegisterServiceModel);
           User user = this.modelMapper.map(userRegisterServiceModel, User.class);
           user.setPassword(this.bCryptPasswordEncoder.encode(userRegisterServiceModel.getPassword()));


            return this.modelMapper.map(this.userRepository.saveAndFlush(user),
                UserServiceModel.class);


    }

 // private void escapeHtmlInput(UserRegisterServiceModel userModel) {
 //     userModel.setUsername(this.escapeHtmlService.escapeHtml(userModel.getUsername()));
 //     userModel.setFirstName(this.escapeHtmlService.escapeHtml(userModel.getFirstName()));
 //     userModel.setLastName(this.escapeHtmlService.escapeHtml(userModel.getLastName()));
 //     userModel.setEmail(this.escapeHtmlService.escapeHtml(userModel.getEmail()));
 //     userModel.setPassword(this.escapeHtmlService.escapeHtml(userModel.getPassword()));
 //     userModel.setConfirmPassword(this.escapeHtmlService.escapeHtml(userModel.getConfirmPassword()));

 // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(UserErrorMessages.USER_NOT_FOUND));

        return user;
    }
}
