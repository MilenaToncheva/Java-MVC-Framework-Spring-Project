package softuni.artgallery.web.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import softuni.artgallery.constants.artistMessages.ArtistErrorMessages;
import softuni.artgallery.constants.artworksMessages.ArtworkErrorMessages;
import softuni.artgallery.error.*;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserRegistrationException.class)
    public ModelAndView handleUserRegistrationException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(UserLoginException.class)
    public ModelAndView handleUserLoginException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(UserIllegalArgumentsException.class)
    public ModelAndView handleUserIllegalArgumentException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }


    @ExceptionHandler(ArtworkNotFoundException.class)
    public ModelAndView handleArtworkNotFoundException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ArtworkErrorMessages.ARTWORK_WITH_ID_NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(ArtworkIllegalArgumentsException.class)
    public ModelAndView handleArtworkIllegalArgumentException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    public ModelAndView handleArtistNotFoundException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ArtistErrorMessages.ARTIST_NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(ArtistIllegalArgumentsException.class)
    public ModelAndView handleArtistIllegalArgumentException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(ArtistAlreadyExistsException.class)
    public ModelAndView handleArtistAlreadyExistsException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(EventIllegalArgumentException.class)
    public ModelAndView handleEventIllegalArgumentException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ModelAndView handleEventNotFoundException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(GreetingAlreadyExistsException.class)
    public ModelAndView HandleGreetingAlreadyExistsException(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(GreetingNotFoundException.class)
    public ModelAndView HandleGreetingNotFoundException(RuntimeException ex){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ModelAndView handleIncorrectDataInput(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }


    @ExceptionHandler(Throwable.class)
    public ModelAndView handleSQLException(Throwable e) {
        Throwable throwable = e;
        while (throwable.getCause() != null) {
            throwable = throwable.getCause();
        }
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", throwable.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessException(AccessDeniedException e, ModelAndView modelAndView) {
        modelAndView.setViewName("unauthorized");
        return modelAndView;
    }

    @ExceptionHandler(ArtistNotDeletedException.class)
    public ModelAndView handleArtistNotDeletedException(Throwable e, ModelAndView modelAndView) {
        modelAndView.setViewName("error");
        modelAndView.addObject("message", ArtistErrorMessages.ARTIST_NOT_DELETED);
        return modelAndView;
    }
}
