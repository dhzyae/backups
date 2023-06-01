import com.mouse.mapper.UserMapper;
import com.mouse.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class Testqq {
    @Test
    public void test1() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取 Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 调用方法
        String username = "11";
        String password = "112";
        User user = userMapper.select(username, password);
        // 释放资源
        sqlSession.close();
    }
}
