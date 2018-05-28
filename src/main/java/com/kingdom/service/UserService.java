package com.kingdom.service;

import com.kingdom.dao.UserDao;
import com.kingdom.domain.User;

import java.io.IOException;

/**
 * @author No.1412
 * @version 2018/5/28
 */
public class UserService extends BaseService {

    private UserDao userDao = this.session.getMapper(UserDao.class);

    public UserService() throws IOException {
    }

    public Integer generateUser(User user) {
        int i = userDao.insert(user);
        this.session.commit();
        this.session.close();
        return i;
    }

    public User findByUsername(String username) {
        User user = userDao.getByName(username);
//        this.session.close();
        return user;
    }

}
