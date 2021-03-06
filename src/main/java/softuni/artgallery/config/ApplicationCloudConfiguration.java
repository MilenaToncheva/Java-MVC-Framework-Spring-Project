package softuni.artgallery.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ApplicationCloudConfiguration {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;
    @Value("${cloudinary.api-key}")
    private String apiKey;
    @Value("${cloudinary.api-secret}")
    private String apiSecret;
    @Bean
    public Cloudinary cloudinary() {
        HashMap<String,Object> config=new HashMap<>();
        return new Cloudinary(new HashMap<String,Object>(){{
            put("cloud_name",cloudName);
            put("api_key",apiKey);
            put("api_secret",apiSecret);
        }});
    }
}
