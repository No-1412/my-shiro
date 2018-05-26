package com.kingdom.dao;

import com.kingdom.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @author No.1412
 * @version 2018/5/23
 */
public interface UserDao {
//    List<Map<String, Object>> findList();
    List<User> findList();

    User get(int id);
}
