package pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component       // 将这个类标注为Spring的一个组件，放到容器中
public class Dog {
    @Value("maomao")
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Dog: " + name;
    }
}
