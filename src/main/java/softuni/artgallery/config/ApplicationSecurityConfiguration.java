package softuni.artgallery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               // .cors().disable()
                .csrf()
                .csrfTokenRepository(csrfTokenRepository())
                .and()
                //.csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**","/css/**","/images/**").permitAll()
                .antMatchers("/","/users/register","/users/login").anonymous()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter("username")
                .passwordParameter("password")
            .defaultSuccessUrl("/home")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
               .and()
                .exceptionHandling()
               .accessDeniedPage("/unauthorized");
    }

    private CsrfTokenRepository csrfTokenRepository(){
        HttpSessionCsrfTokenRepository repository=new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}
