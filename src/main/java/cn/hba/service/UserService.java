package cn.hba.service;

import cn.hba.entity.Users;

import java.util.Map;

/**
 * @author wjq
 * 2020/2/25
 */
public interface UserService {
    public Users login(String user_code);

    public String loginValdate(String user_code);

    public Map loginUserLock(String user_code);

}
