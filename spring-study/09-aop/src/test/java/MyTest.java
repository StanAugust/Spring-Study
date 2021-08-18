import springAPI.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName MyTest
 * @Description aop测试类
 * @Author Stan
 * @Date 2021年08月07日
 */
public class MyTest {
    @Test
    public void testAOP(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = context.getBean("userServiceImpl", UserService.class);
        userService.select();
    }
}
