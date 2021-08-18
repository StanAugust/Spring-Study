import config.MyConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pojo.Dog;
import pojo.Person;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Dog dog = context.getBean("dog", Dog.class);
//        Dog dog2 = context.getBean("dog", Dog.class);
        System.out.println(dog);
//        Person person = context.getBean("person", Person.class);
//        System.out.println(person.toString());
    }
}
