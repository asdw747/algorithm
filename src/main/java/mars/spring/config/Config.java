package mars.spring.config;

import mars.spring.service.CService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
public class Config {

    @Bean
    public CService cService() {
        return new CService();
    }

}
