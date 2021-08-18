package mapper;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import pojo.User;


import java.util.List;

/**
 * @ClassName UserMapperImpl2
 * @Description TODO
 * @Author Stan
 * @Date 2021年08月10日
 */
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
    @Override
    public List<User> selectUser() {
        //SqlSession sqlSession = getSqlSession();
        //UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //return mapper.selectUser();

        return  getSqlSession().getMapper(UserMapper.class).selectUser();
    }
}
