package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import pojo.Dog;
import pojo.Person;

@Configuration  // 代表这是一个配置类
@Import(DevConfig.class)
public class MyConfig {

    @Bean   //通过方法注册一个bean，这里的返回值就是Bean的类型，方法名就是bean的id
    public Dog dog(){
        return new Dog();
    }
}
