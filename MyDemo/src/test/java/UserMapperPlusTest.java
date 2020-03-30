import com.chan.mybatis.dao.UserMapperPlus;
import com.chan.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class UserMapperPlusTest {

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
    public void testSelectUserById() {
        SqlSession session = sqlSessionFactory.openSession();
        UserMapperPlus userMapperPlus = session.getMapper(UserMapperPlus.class);
        User user = userMapperPlus.selectUserById(1);
        System.out.println(user);
        session.close();
    }
}
