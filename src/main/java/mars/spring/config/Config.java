package mars.spring.config;

import mars.spring.service.CService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CService getCService() {
        return new CService();
    }

}
