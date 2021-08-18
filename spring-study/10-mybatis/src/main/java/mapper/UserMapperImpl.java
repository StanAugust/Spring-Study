package mapper;

import org.mybatis.spring.SqlSessionTemplate;
import pojo.User;

import java.util.List;

/**
 * @ClassName UserMapperImpl
 * @Description TODO
 * @Author Stan
 * @Date 2021年08月10日
 */
public class UserMapperImpl implements UserMapper{

    // 原来所有的操作都用 sqlSession 来执行，现在都用 SqlSessionTemplate
    // sqlSession不用我们自己创建了，Spring来管理
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> selectUser() {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.selectUser();
    }
}
