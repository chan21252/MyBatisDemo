import com.chan.mybatis.dao.UserDao;
import com.chan.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTest {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream inputstream = null;
        try {
            inputstream = Resources.getResourceAsStream("mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream, "oracle");
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
        InputStream inputstream = null;
        try {
            inputstream = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputstream);
            SqlSession session = sqlSessionFactory.openSession();
            UserDao userDao = session.getMapper(UserDao.class);
            User user = userDao.selectUserById(1);
            System.out.println(user);
            session.close();
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
}
