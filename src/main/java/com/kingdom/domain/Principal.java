package com.kingdom.domain;


import org.apache.shiro.SecurityUtils;

import java.io.Serializable;

/**
 * @author No.1412
 * @version 2018/5/17
 */
public class Principal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id; // 编号
    private String loginName; // 登录名
    private String name; // 姓名
    private boolean mobileLogin; // 是否手机登录

//		private Map<String, Object> cacheMap;

    public Principal(boolean mobileLogin) {
        this.id = "1";
        this.loginName = "威斯克";
        this.name = "Sora";
        this.mobileLogin = mobileLogin;
    }

    public String getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }

    public boolean isMobileLogin() {
        return mobileLogin;
    }

//		@JsonIgnore
//		public Map<String, Object> getCacheMap() {
//			if (cacheMap==null){
//				cacheMap = new HashMap<String, Object>();
//			}
//			return cacheMap;
//		}

    /**
     * 获取SESSIONID
     */
    public String getSessionid() {
        try{
            return (String) SecurityUtils.getSubject().getSession().getId();
        }catch (Exception e) {
            return "";
        }
    }

    @Override
    public String toString() {
        return id;
    }

}
