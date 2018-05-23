package com.kingdom.mybatis;

import com.kingdom.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

/**
 * @author No.1412
 * @version 2018/5/23
 */
public class simpleSql {

    @Test
    public void testQuery() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sessionFactory.openSession();
        List<User> userList = sqlSession.selectList("com.kingdom.dao.UserDao.findList");
        System.out.println("====================== " + userList.get(0).getUsername() + " ====================================");
        sqlSession.close();
    }

}
