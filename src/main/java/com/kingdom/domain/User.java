package com.kingdom.domain;

import java.io.Serializable;

/**
 * @author No.1412
 * @version 2018/5/23
 */
public class User implements Serializable {
    private static final long serialVersionUID = -7205163610470134743L;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
