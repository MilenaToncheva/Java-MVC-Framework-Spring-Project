package softuni.artgallery.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    public class ValidationUtil {
        private Validator validator;

        public ValidationUtil() {
            this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        }

        public <M> boolean isValid(M model) {
            return this.validator.validate(model).size()==0;
        }
    }
}
