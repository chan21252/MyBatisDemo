import com.chan.mybatis.dao.UserMapper;
import com.chan.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream inputstream = null;
        try {
            inputstream = Resources.getResourceAsStream("mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream, "mysql");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testDataSource() {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectUserById(1);
        System.out.println(user);
        session.close();
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("adtest");
        user.setPassword("123456");
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        userMapper.addUser(user);
        session.commit();
        session.close();
    }

    @Test
    public void testSelectUserByIdAndUserName() {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectUserByIdAndUserName(1, "admin");
        System.out.println(user);
        session.commit();
        session.close();
    }

    @Test
    public void testSelectUserByList() {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(1);
        userMapper.selectUserByList(idList);
        session.commit();
        session.close();
    }

    @Test
    public void testSelectUserByUsername() {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> userList = userMapper.selectUserByUsernameReturnList("a%");
        for (User user : userList) {
            System.out.println(user);
        }
        session.commit();
        session.close();
    }

    @Test
    public void testSelectUserByIdReturnMap() {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        Map<String, Object> userMap = userMapper.selectUserByIdReturnMap(1);
        for (Map.Entry<String, Object> entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        session.commit();
        session.close();
    }

    @Test
    public void testSelectUserByUsernameReturnMap() {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        Map<Integer, Object> userMap = userMapper.selectUserByUsernameReturnMap("a%");
        for (Map.Entry<Integer, Object> entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        session.commit();
        session.close();
    }
}
