package com.kingdom.shiro;

import com.kingdom.domain.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author No.1412
 * @version 2018/5/17
 */
public class SampleShiro {

    private static final transient Logger logger = LoggerFactory.getLogger(SampleShiro.class);

    public static void main(String[] args) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        logger.info("此时当前用户应为空-> {}", currentUser.getPrincipal());
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            try {
                currentUser.login(token);
                Object principal = currentUser.getPrincipal();
                Principal p = (Principal) principal;
                System.out.println(p);
                logger.info("恭喜{}登录成功", principal);
            } catch (UnknownAccountException e) {
                logger.error("抱歉,用户不存在", e);
                e.printStackTrace();
            } catch (IncorrectCredentialsException e) {
                logger.error("抱歉,密码不正确", e);
                e.printStackTrace();
            } catch (AuthenticationException e) {
                logger.error("抱歉,用户认证失败", e);
                e.printStackTrace();
            }
        }
        //say who they are:
        //print their identifying principal (in this case, a username):
        logger.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");

        //test a role:
        if (currentUser.hasRole("schwartz")) {
            logger.info("May the Schwartz be with you!");
        } else {
            logger.info("Hello, mere mortal.");
        }

        //test a typed permission (not instance-level)
        if (currentUser.isPermitted("lightsaber:weild")) {
            logger.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            logger.info("Sorry, lightsaber rings are for schwartz masters only.");
        }

        //a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
            logger.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            logger.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }

        //all done - log out!
        currentUser.logout();

        System.exit(0);

    }

}
