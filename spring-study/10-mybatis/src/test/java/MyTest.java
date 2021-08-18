import mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

/**
 * @ClassName MyTest
 * @Description TODO
 * @Author Stan
 * @Date 2021年08月10日
 */
public class MyTest {

    @Test
    public void test() throws IOException {
        //InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //SqlSession sqlSession = factory.openSession(true);
        //
        //UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 将mybatis整合到spring中
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper mapper = context.getBean("userMapper", UserMapper.class);
        //mapper = context.getBean("userMapper2", UserMapper.class);

        for (User user : mapper.selectUser()) {
            System.out.println(user);
        }

        //sqlSession.close();

    }
}
