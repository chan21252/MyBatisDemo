import com.chan.mybatis.dao.UserDao;
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

public class MybatisTest {

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
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.selectUserById(1);
        System.out.println(user);
        session.close();
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("chan");
        user.setPassword("123456");
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        userDao.addUser(user);
        session.commit();
        session.close();
    }

    @Test
    public void testSelectUserByIdAndUserName() {
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.selectUserByIdAndUserName(1, "admin");
        System.out.println(user);
        session.commit();
        session.close();
    }

    @Test
    public void testSelectUserByList() {
        SqlSession session = sqlSessionFactory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(1);
        userDao.selectUserByList(idList);
        session.commit();
        session.close();
    }
}
