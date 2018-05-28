package com.kingdom.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author No.1412
 * @version 2018/5/28
 */
public abstract class BaseService {

    private SqlSessionFactory factory;

    protected SqlSession session;

    public BaseService() throws IOException {
        this.factory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"));
        this.session = factory.openSession();
    }

    public SqlSession getSession() {
        return session;
    }

    public void close() {
        if (this.session != null) {
            this.session.close();
            System.out.println("威斯克-> sqlSession 已关闭");
        }
    }
}
