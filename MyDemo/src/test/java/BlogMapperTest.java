import com.chan.mybatis.dao.BlogMapper;
import com.chan.mybatis.pojo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class BlogMapperTest {

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
    public void testSelectBlogByIdReturnResultMap() {
        SqlSession session = sqlSessionFactory.openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectBlogByIdReturnResultMap(4);
        System.out.println(blog);
        session.commit();
        session.close();
    }

    @Test
    public void testSelectBlogByIdStep() {
        SqlSession session = sqlSessionFactory.openSession();
        BlogMapper blogMapper = session.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectBlogByIdStep(4);
        System.out.println(blog.getTitle());
        System.out.println(blog);
        session.commit();
        session.close();
    }
}
