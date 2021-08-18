import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Hello;

public class HelloSpringTest {
    @Test
    public void test() {
        // 解析xml文件，生成管理相应的bean对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // getBean：参数即为配置文件中bean的id
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
    }
}
