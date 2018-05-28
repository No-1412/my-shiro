package com.kingdom.mybatis;

import com.kingdom.dao.MenuDao;
import com.kingdom.dao.SysRoleDao;
import com.kingdom.dao.UserDao;
import com.kingdom.domain.Menu;
import com.kingdom.domain.SysRole;
import com.kingdom.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
//        List<User> userList = sqlSession.selectList("com.kingdom.dao.UserDao.findList");
        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        List<Map<String, Object>> userList = userDao.findList();
        List<User> userList = userDao.findList();
//        System.out.println("====================== " + userList.get(0).get("username") + " ====================================");
        System.out.println("====================== " + userList.get(0).getLoginName() + " ====================================");
        System.out.println(userDao.get(56).getPassword());
        SysRoleDao sysRoleDao = sqlSession.getMapper(SysRoleDao.class);
        SysRole sysRole = sysRoleDao.selectByPrimaryKey(1);
        System.out.println(sysRole.getName());

        MenuDao menuDao = sqlSession.getMapper(MenuDao.class);
        Menu menu = menuDao.selectByPrimaryKey(1);
        System.out.println(menu.getName());

        sqlSession.close();
    }

    public static void main(String[] args) {
//        List<String> warnings = new ArrayList<>();
//        boolean overwrite = true;
//        InputStream in = MyBatisGene.class.getClassLoader().getResourceAsStream("mybatis-generator-config.xml");
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = cp.parseConfiguration(in);
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        myBatisGenerator.generate(null);
    }

}
