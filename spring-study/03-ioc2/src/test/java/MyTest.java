import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;
import pojo.UserInfo;

public class MyTest {
    @Test
    public void test(){
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 导入总的xml即可
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        User user = (User) context.getBean("user");
//        User user2 = (User) context.getBean("user");
//
//        System.out.println(user==user2);

//        UserInfo userInfo = (UserInfo) context.getBean("userInfo");
        UserInfo info = (UserInfo) context.getBean("i");
        info.show();
    }
}
