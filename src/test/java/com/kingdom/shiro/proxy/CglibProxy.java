package com.kingdom.shiro.proxy;


import com.kingdom.service.UserService;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author No.1412
 * @version 2018/5/28
 */
public class CglibProxy implements org.springframework.cglib.proxy.MethodInterceptor {

    private static final transient Logger logger = LoggerFactory.getLogger(CglibProxy.class);

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        logger.info("进入cglib代理 -> {}", method.getName());

        methodProxy.invokeSuper(o, objects);

        logger.info("结束cglib代理 -> {}", methodProxy.getSuperName());
        return null;
    }

    public Object intercept(Object o, Method method, Object[] objects, org.springframework.cglib.proxy.MethodProxy methodProxy) throws Throwable {
        logger.info("进入spring-cglib代理 -> {}", method.getName());
        UserService userService = (UserService) o;

        Object result = methodProxy.invokeSuper(o, objects);
//        PropertyDescriptor pd = new PropertyDescriptor("session", o.getClass());
//        Method readMethod = pd.getReadMethod();
//        SqlSession session = (SqlSession) readMethod.invoke(o);
        if (!"close".equals(method.getName()))
            userService.close();

        logger.info("结束spring-cglib代理 -> {}", methodProxy.getSuperName());
        return result;
    }
}
