package softuni.artgallery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.artgallery.web.interceptors.LogoInterceptor;
import softuni.artgallery.web.interceptors.WelcomeInterceptor;

@Configuration
public class ApplicationWebMvcConfiguration implements WebMvcConfigurer {


    private final LogoInterceptor logoInterceptor;
    private final WelcomeInterceptor welcomeInterceptor;

    @Autowired
    public ApplicationWebMvcConfiguration(LogoInterceptor logoInterceptor, WelcomeInterceptor welcomeInterceptor) {

        this.logoInterceptor = logoInterceptor;
        this.welcomeInterceptor = welcomeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(this.logoInterceptor).addPathPatterns("/","/users/register","/users/login");
        registry.addInterceptor(this.welcomeInterceptor).excludePathPatterns("/","/users/register","/users/login");
    }

}
