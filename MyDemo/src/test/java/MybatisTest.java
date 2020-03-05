import com.chan.mybatis.dao.UserDao;
import com.chan.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    private final static String CONFIG_FILENAME = "mybatis.xml";

    private static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream inputstream = null;
        try {
            inputstream = Resources.getResourceAsStream(CONFIG_FILENAME);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputstream != null) inputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testSelectUserById() {
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.selectUserById(1);
        Assert.assertEquals(1, user.getId());
    }

    @Test
    public void testInsertUser() {
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("chan");
        user.setPassword("abcdef");
        userDao.insertUser(user);
        session.commit();
    }


    @Test
    public void testInsertUserByMap() {
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("username", "chan");
        userMap.put("password", "qwerty");

        try {
            userDao.insertUserByMap(userMap);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }

    @Test
    public void testUpdatePasswordById() {
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = new User();
        user.setId(3);
        user.setPassword("123456");
        try {
            userDao.updatePasswordById(user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }

    @Test
    public void testDeleteUserById() {
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        try {
            userDao.deleteUserById(3);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }

    @Test
    public void testInsertUserList() {
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setUsername("test" + i);
            user.setPassword("qwerty");
            userList.add(user);
        }

        try {
            userDao.insertUserList(userList);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }
    }
}