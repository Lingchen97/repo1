package com.lagou.test;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    @Test
    public void testFindAll() throws IOException {
        //加载核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //获取SqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取SqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //执行sql
        List<User> list = sqlSession.selectList("userMapper.findAll");
        for (User user : list) {
            System.out.println(user);
        }

        //释放资源
        sqlSession.close();
    }

    @Test
    public void testSave() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsername("tom");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("Shanghai");
        sqlSession.insert("userMapper.saveUser",user);

        // DML语句，手动提交事务
        sqlSession.commit();

        sqlSession.close();


    }
}
