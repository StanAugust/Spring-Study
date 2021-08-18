import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Student;
import pojo.User;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        Student student = context.getBean("student", Student.class);
//        System.out.println(student.toString());
        User user = context.getBean("user", User.class);
        System.out.println(user.toString());
    }
}
