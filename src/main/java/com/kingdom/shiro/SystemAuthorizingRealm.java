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

    private static final String password = "d6f19f1e0a0102a759af2ca552225b598f1e9218e4853024c4b19dba6f3ef36b1969bea08eeb3112";

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
        // TODO: 2018/5/29 使用mybatis获取已存在用户完成认证
        String s = password.substring(0, 16);
        ByteSource salt = ByteSource.Util.bytes(Hex.decode(s));
        return new SimpleAuthenticationInfo(token.getPrincipal(), password.substring(16), salt, getName());
    }

}

