package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Person {
    @Value("Stan")
    private String name;
    @Autowired
    private Dog dog;

    @Override
    public String toString() {
        return name + " with " + dog.getName();
    }
}
