package com.kingdom.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

/**
 * @author No.1412
 * @version 2018/5/16
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

    private static final transient Logger logger = LoggerFactory.getLogger(AuthorizingRealm.class);

    private static final SecureRandom random = new SecureRandom();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("开始授权了-> {}", principals);

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("开始认证了 Credentials-> {}, Principal-> {}",
                token.getCredentials(), token.getPrincipal());
        logger.info("name-> {}", getName());
//        Object credentials = token.getCredentials();
//        Sha256Hash sha256Hash = new Sha256Hash(credentials);
//        String s = sha256Hash.toBase64();
        String s = password.substring(0, 16);
        ByteSource salt = ByteSource.Util.bytes(Hex.decode(s));
        return new SimpleAuthenticationInfo(token.getPrincipal(), password.substring(16), salt, getName());
    }

    private static final String password = "d6f19f1e0a0102a759af2ca552225b598f1e9218e4853024c4b19dba6f3ef36b1969bea08eeb3112";

    public static void main(String[] args) {

        byte[] salt = generateRandom();
        Sha256Hash sha256Hash = new Sha256Hash("123456", salt, 512);
        logger.info("salt-> {}", Hex.encodeToString(salt));
        logger.info("加密后的密码-> {}", sha256Hash);
        logger.info("存入数据库的最终密码-> {}", Hex.encodeToString(salt) + sha256Hash);


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

    /**
     * 生成8位的随机字节-> salt
     */
    private static byte[] generateRandom() {
//        return (int) ((Math.random() * 9 + 1) * 10000000);
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        return bytes;
    }
}

