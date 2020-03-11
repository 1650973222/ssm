package cn.hba.controller;

import cn.hba.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wjq
 * 2020/2/25
 */
public class UserTest {




    @Test
    public void demo(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper d =(UserMapper) applicationContext.getBean("userMapper");
        System.out.println(d.login("11"));
    }

}

