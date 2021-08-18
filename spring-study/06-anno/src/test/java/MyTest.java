import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        User user = context.getBean(User.class);
        User user2 = context.getBean(User.class);
        System.out.println(user.toString());
        System.out.println(user2.toString());
        System.out.println(user==user2);
    }
}
