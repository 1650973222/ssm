package cn.hba.entity;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
    private Integer user_id;

    private String user_code;

    private String password;

    private String email;

    private String gender;

    private Date register_time;

    private Date last_logintime;

    public static String loginCount(String user_code){
        return "user:loginCount:"+user_code;
    }
    public static String loginTimeLock(String user_code){
        return "user:loginTimeLock:"+user_code;
    }





    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code == null ? null : user_code.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    public Date getLast_logintime() {
        return last_logintime;
    }

    public void setLast_logintime(Date last_logintime) {
        this.last_logintime = last_logintime;
    }
}