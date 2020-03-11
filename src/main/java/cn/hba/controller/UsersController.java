package cn.hba.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.hba.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hba.entity.*;

@Controller
public class UsersController {

    @Resource
    private UserService usersService;

    @RequestMapping("/login")
    public String login() {

        return "login";
    }

    @RequestMapping("/dologin")
    @ResponseBody
    public String dologin(@RequestParam("user_code") String user_code,
                          @RequestParam("password") String password,
                          HttpSession session,
                          Map<String, Object> map) {
        //查看是否被限制登陆
        Map lockMap = usersService.loginUserLock(user_code);
        if ((Boolean) lockMap.get("falg")) {
            map.put("error","登陆失败" + user_code + "登陆被限制还剩" + lockMap.get("lockTime"));
        } else {
            Users user = usersService.login(user_code);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    session.setAttribute("users", user);
                    map.put("msg", "success");
                } else {
                    //验证登陆次数
                    String valdate = usersService.loginValdate(user_code);
                    map.put("msg", "pwderror");
                    map.put("errorMsg",valdate);
                }
            } else {
                //验证登陆次数
                String valdate = usersService.loginValdate(user_code);
                map.put("msg", "yhmerror");
                map.put("errorMsg",valdate);
            }
        }

        return JSON.toJSONString(map);
    }

    @RequestMapping("/main")
    public String main() {

        return "redirect:/list";
    }
}
