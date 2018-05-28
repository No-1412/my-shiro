package com.kingdom.shiro;

import com.kingdom.domain.User;
import com.kingdom.service.SecurityUtils;
import com.kingdom.service.UserService;
import com.kingdom.shiro.proxy.CglibProxy;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;

import java.io.IOException;

/**
 * @author No.1412
 * @version 2018/5/28
 */
public class UserTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void generateUser() {
//        SqlSession session = sqlSessionFactory.openSession();
//        UserDao userDao = session.getMapper(UserDao.class);
//        User user = new User();
//        user.setName("No.1412");
//        user.setLoginName("Sora");
//        String password = "123456";
//        byte[] salt = generateRandom();
//        Sha256Hash hash = new Sha256Hash(password, ByteSource.Util.bytes(salt), hashIterations);
//        user.setPassword(Hex.encodeToString(salt) + hash.toHex());
//        userDao.insertSelective(user);
        org.springframework.cglib.proxy.Enhancer springEnhancer = new org.springframework.cglib.proxy.Enhancer();
//        Enhancer enhancer = new Enhancer();
        springEnhancer.setSuperclass(UserService.class);
        springEnhancer.setCallback(new CglibProxy());
        UserService userService = (UserService) springEnhancer.create();
        User user = new User();
        user.setLoginName("Riku");
        user.setPassword(SecurityUtils.encryptPassword("123456"));

        userService.generateUser(user);
        System.out.println(user.getLoginName());
//        session.commit();
//        session.close();
    }

    @Test
    public void getUser() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new CglibProxy());
        UserService userService = (UserService) enhancer.create();
        System.out.println(userService.findByUsername("Sora").getName());
        System.out.println("============> > >"+userService.getSession());
    }

    // TODO: 2018/5/29 封装代理方法

    public static void main(String[] args) {

//        byte[] salt = generateRandom();
//        Sha256Hash sha256Hash = new Sha256Hash("123456", salt, 512);
//        logger.info("salt-> {}", Hex.encodeToString(salt));
//        logger.info("加密后的密码-> {}", sha256Hash);
//        logger.info("存入数据库的最终密码-> {}", Hex.encodeToString(salt) + sha256Hash);


//        Random random = new Random();
//        System.out.println();
//        for(int i=0;i<8;i++)
//        {
//            int i1 = random.nextInt();
//            System.out.println(i1);
//            System.out.println(Integer.toHexString(i1));
//        }

//        byte[] bytes = {127};
//        System.out.println(Hex.encodeToString(bytes));
//        System.out.println(Integer.toHexString(1));
//        for (;;) {
//            byte[] bytes = new byte[16];
//            SecureRandom secureRandom = new SecureRandom();
//            secureRandom.nextBytes(bytes);
//            System.out.println(Hex.encodeToString(bytes));
//        }

//        raw-> 64339827
//        hex-> 3d5bf73
//        String hex = Integer.toHexString(64339827);
//        int i = Integer.parseInt(hex, 16);
//        System.out.println(i);
//        for (;;) {
//            int i = generateRandom();
//            System.out.println("raw-> "+i);
//            System.out.println("hex-> "+Integer.toHexString(i));
//        }

//        System.out.println("===========================================================================================================================");
//        System.out.println((char) 97);
//        System.out.println("威斯克-> "+ Integer.toHexString(15));
//        // TODO: 2018/5/18 生成随机数加在密码前做盐
//        String string = generateRandom().toString();
//        System.out.println("随机数-> "+string);
//        System.out.println(Integer.toHexString(Integer.parseInt(string)));
//        ByteSource salt = ByteSource.Util.bytes(string);
//        Sha256Hash sha256Hash = new Sha256Hash("123456", salt, 512);
//        SystemAuthorizingRealm.password = salt.toBase64() + sha256Hash.toBase64();
//        System.out.println("密码生成-> " + SystemAuthorizingRealm.password);
//        System.out.println("============================================== 开始登陆 ===================================================");
//        String password = "123456";
//        String newSalt = SystemAuthorizingRealm.password.substring(0, 16);
//        byte[] decode = Base64.decode(newSalt);
//        Sha256Hash sha256Hash1 = new Sha256Hash(password, decode, 512);
//        System.out.println(newSalt + sha256Hash1.toBase64());
//        System.out.println(SystemAuthorizingRealm.password);
    }

}
