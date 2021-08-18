package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pojo.Person;

@Configuration
public class DevConfig {

    @Bean
    public Person person(){
        return new Person();
    }
}
