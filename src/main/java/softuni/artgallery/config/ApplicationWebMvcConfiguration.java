package softuni.artgallery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import softuni.artgallery.web.interceptors.FaviconInterceptor;
import softuni.artgallery.web.interceptors.WelcomeInterceptor;

@Configuration
public class ApplicationWebMvcConfiguration implements WebMvcConfigurer {


    private final FaviconInterceptor faviconInterceptor;
    private final WelcomeInterceptor welcomeInterceptor;

    @Autowired
    public ApplicationWebMvcConfiguration(FaviconInterceptor faviconInterceptor, WelcomeInterceptor welcomeInterceptor) {

        this.faviconInterceptor = faviconInterceptor;
        this.welcomeInterceptor = welcomeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(this.faviconInterceptor).addPathPatterns("/","/users/create","/users/login");
        registry.addInterceptor(this.welcomeInterceptor).excludePathPatterns("/","/users/login","/users/create");
    }

}
