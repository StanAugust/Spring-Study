import dao.UserDaoMysqlImpl;
import dao.UserDaoOracleImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserServiceImpl;
import service.UserServiceImpl2;

public class UserServiceTest {

    @Test
    public void userServiceTest() {
        // 用户实际调用的是业务层，不需要接触dao层
        UserServiceImpl userService = new UserServiceImpl();
        userService.getUser();
    }

    @Test
    public void userService2Test(){
        UserServiceImpl2 userService = new UserServiceImpl2();

        userService.setUserDao(new UserDaoMysqlImpl());
        userService.getUser();
        // 现在又想用Oracle去实现
        userService.setUserDao(new UserDaoOracleImpl());
        userService.getUser();
    }

    @Test
    public void xmlTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl2 serviceImpl = (UserServiceImpl2) context.getBean("serviceImpl");
        serviceImpl.getUser();
    }
}
