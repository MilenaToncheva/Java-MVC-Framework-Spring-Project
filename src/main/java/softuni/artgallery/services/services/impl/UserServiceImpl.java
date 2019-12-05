package softuni.artgallery.services.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.artgallery.constants.userMessages.UserErrorMessages;
import softuni.artgallery.data.models.User;
import softuni.artgallery.data.repository.UserRepository;
import softuni.artgallery.error.UserNotDeletedException;
import softuni.artgallery.error.UserNotFoundException;
import softuni.artgallery.services.models.UserServiceModel;
import softuni.artgallery.services.services.ArtworkService;
import softuni.artgallery.services.services.RoleService;
import softuni.artgallery.services.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final ArtworkService artworkService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, ArtworkService artworkService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;

        this.modelMapper = modelMapper;
        this.artworkService = artworkService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel findById(String id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(UserErrorMessages.USER_NOT_FOUND_Message));
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {

        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(UserErrorMessages.USER_NOT_FOUND_Message));

        return this.modelMapper.map(user, UserServiceModel.class);


    }


    @Override
    public UserServiceModel editUserProfile(String oldPassword, UserServiceModel userServiceModel) {
        User user = this.userRepository.findByUsername(userServiceModel.getUsername()).orElseThrow(() -> new UserNotFoundException(UserErrorMessages.USER_NOT_FOUND_Message));
        if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException(UserErrorMessages.USER_INCORRECT_PASSWORD);
        }
        if (userServiceModel.getPassword().equals("")) {   //it will never be "" as there is passwords validation with @
            user.setPassword(this.bCryptPasswordEncoder.encode(oldPassword));
        } else {
            user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        }

        user.setFirstName(userServiceModel.getFirstName());
        user.setLastName(userServiceModel.getLastName());
        userServiceModel.setLastName(userServiceModel.getLastName());
        user.setEmail(userServiceModel.getEmail());

        this.userRepository.saveAndFlush(user);

        return userServiceModel;
    }

    @Override
    public void delete(String id) throws UserNotDeletedException {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(UserErrorMessages.USER_NOT_FOUND_Message));
        try {
            this.userRepository.delete(user);
        } catch (Exception e) {
            throw new UserNotDeletedException(UserErrorMessages.USER_NOT_DELETED);
        }
    }

    @Override
    public List<UserServiceModel> findAll() {
        return Arrays.stream(this.modelMapper.map(this.userRepository.findAll(), UserServiceModel[].class)).collect(Collectors.toList());
    }

    @Override
    public void setUserRole(String id, String role) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(UserErrorMessages.USER_NOT_FOUND_Message));
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        userServiceModel.getAuthorities().clear();
        switch (role) {
            case "user":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                break;
            case "moderator":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                break;
            case "admin":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));
                break;
        }

        this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
    }


    @Override
    public UserServiceModel isUsernameUnique(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }
        return this.modelMapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel isEmailUnique(String email) {
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        return
                this.modelMapper.map(this.userRepository.findByEmail(email), UserServiceModel.class);
    }
}
