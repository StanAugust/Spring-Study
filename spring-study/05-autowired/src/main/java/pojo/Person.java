package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class Person {

    @Resource
    private Cat cat;
    @Autowired
    @Qualifier(value = "dog2")
    private Dog dog;

    public Cat getCat() {
        return cat;
    }

    @Override
    public String toString() {
        return "Person{" +
                "cat=" + cat +
                ", dog=" + dog +
                '}';
    }
}
