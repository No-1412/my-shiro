package com.kingdom.dao;

import com.kingdom.domain.User;

import java.util.List;

/**
 * @author No.1412
 * @version 2018/5/23
 */
public interface UserDao {
    List<User> findList();
}
