package mapper;

import pojo.User;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Stan
 * @Date 2021年08月10日
 */
public interface UserMapper {
    public List<User> selectUser();
}
