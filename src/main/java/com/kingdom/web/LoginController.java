package com.kingdom.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author No.1412
 * @version 2018/5/17
 */
public class LoginController extends HttpServlet {

    private static final transient Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        logger.info("get请求");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        logger.info("post请求");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("请求参数-> {}", req);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(new UsernamePasswordToken(req.getParameter("username"), req.getParameter("password")));
        } catch (AuthenticationException e) {
            logger.error("很遗憾,认证失败");
        }
//        req.getRequestDispatcher("index.jsp").forward(req, resp);
        resp.sendRedirect("index.jsp");
    }
}
