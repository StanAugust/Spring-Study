package pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 相当于配置文件中 <bean id="user" class="当前注解的类"/>
//@Component("user")
@Component
@Scope(value = "prototype")
public class User {

    @Value("Stan")
    private String name;

    @Override
    public String toString() {
        return "Hello, " + name;
    }
}
