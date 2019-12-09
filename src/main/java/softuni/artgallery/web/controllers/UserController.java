package softuni.artgallery.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.constants.common.CommonConstants;
import softuni.artgallery.error.UserNotDeletedException;
import softuni.artgallery.services.models.UserServiceModel;
import softuni.artgallery.services.services.UserService;
import softuni.artgallery.web.models.user.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;




    @ModelAttribute("userViewModel")
    public UserViewModel userViewModel() {
        return new UserViewModel();
    }

    @ModelAttribute("userProfileEditModel")
    public UserProfileEditModel userProfileEditModel() {
        return new UserProfileEditModel();
    }

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getProfile(Principal principal, ModelAndView modelAndView) {

        UserProfileViewModel user = this.modelMapper.map(this.userService.findByUsername(principal.getName()),
                                                            UserProfileViewModel.class);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/users/user-profile");
        return modelAndView;
    }


    @GetMapping("/profile/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getUserEditForm(Principal principal,
                                        @ModelAttribute("userProfileEditModel") UserProfileEditModel userProfileEditModel,
                                        ModelAndView modelAndView) {
        UserProfileEditModel user = this.modelMapper.map(this.userService.findByUsername(principal.getName()),
                                                                    UserProfileEditModel.class);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("users/user-profile-edit");
        return modelAndView;
    }

    @PostMapping("/profile/edit")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView editUserProfile(Principal principal, @Valid @ModelAttribute("userProfileEditModel")
                                                                        UserProfileEditModel userProfileEditModel,
                                        BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors() || !userProfileEditModel.getPassword().equals(userProfileEditModel.getConfirmPassword())) {
            modelAndView.addObject("user",userProfileEditModel);
            modelAndView.setViewName("users/user-profile-edit");
            return modelAndView;
        }
        userProfileEditModel.setUsername(principal.getName());
        this.userService.editUserProfile(userProfileEditModel.getOldPassword(),
                this.modelMapper.map(userProfileEditModel, UserServiceModel.class));

        modelAndView.setViewName("redirect:/users/profile");
        return modelAndView;
    }


  //  @GetMapping("delete/{id}")
  //  @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
  //  public ModelAndView getDeleteForm(@PathVariable String id, @ModelAttribute("userDeleteModel") UserDeleteModel userDeleteModel,
  //                                    ModelAndView modelAndView) {
//
  //      userDeleteModel = this.modelMapper.map(this.userService.findById(id), UserDeleteModel.class);
  //      modelAndView.addObject("user", userDeleteModel);
  //      modelAndView.setViewName("users/user-delete");
  //      return modelAndView;
  //  }

  //   @PostMapping("delete/{id}")
  //  @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
  //  public ModelAndView deleteUser(@PathVariable String id,ModelAndView modelAndView) throws UserNotDeletedException {
  //      this.userService.delete(id);
  //      modelAndView.setViewName("redirect:/users/all");
  //      return modelAndView;
  //  }

    @PostMapping("disable/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ModelAndView disableUser(@PathVariable String id,ModelAndView modelAndView) throws UserNotDeletedException {
        this.userService.disableUser(id);
        modelAndView.setViewName("redirect:/users/all");
        return modelAndView;
    }
    @PostMapping("enable/{id}")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ModelAndView enableUser(@PathVariable String id,ModelAndView modelAndView) throws UserNotDeletedException {
        this.userService.enableUser(id);
        modelAndView.setViewName("redirect:/users/all");
        return modelAndView;
    }

    @GetMapping("/all")
    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    public ModelAndView getAllUsers( ModelAndView modelAndView) {
        List<UserAllViewModel> users = this.userService.findAll().stream().map(u -> {
            UserAllViewModel uvm = this.modelMapper.map(u, UserAllViewModel.class);
            uvm.setAuthorities(u.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toSet()));
            return uvm;
        })
                .collect(Collectors.toList());
        modelAndView.addObject("users", users);
        modelAndView.setViewName("users/users-all");
        return modelAndView;
    }

    @PostMapping("/set-role-user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setUserRole( @PathVariable String id, ModelAndView modelAndView) {
        this.userService.setUserRole(id, CommonConstants.ROLE_NAME_USER);
        modelAndView.setViewName("redirect:/users/all");
        return modelAndView;
    }

    @PostMapping("/set-role-moderator/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setModeratorRole( @PathVariable String id,ModelAndView modelAndView) {
        this.userService.setUserRole(id,CommonConstants.ROLE_NAME_MODERATOR);
        modelAndView.setViewName("redirect:/users/all");
        return modelAndView;
    }

    @PostMapping("/set-role-admin/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setAdminRole( @PathVariable String id ,ModelAndView modelAndView) {
        this.userService.setUserRole(id,CommonConstants.ROLE_NAME_ADMIN);
        modelAndView.setViewName("redirect:/users/all");
        return modelAndView;
    }

}