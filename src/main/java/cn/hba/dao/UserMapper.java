package cn.hba.dao;

import org.apache.ibatis.annotations.Param;

import cn.hba.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

public interface UserMapper {
	public Users login(@Param("user_code") String user_code);
}
