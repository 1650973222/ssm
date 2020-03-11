package cn.hba.service;

import javax.annotation.Resource;

import cn.hba.dao.UserMapper;
import cn.hba.entity.Users;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper usersMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Users login(String user_code) {
    	return usersMapper.login(user_code);
    }

    //验证登陆次数
    @Override
    public String loginValdate(String user_code) {
    	//查看记录登陆次数的key是否存在
		String key = Users.loginCount(user_code);
		long num=5;
		if(redisTemplate.hasKey(key)){
			//如果存在并且小于4次，就加一，不存在就插入key设置超时时间
			 long count=Long.parseLong(redisTemplate.opsForValue().get(key).toString());
			 if(count<4){
			 	 redisTemplate.opsForValue().increment(key,1);
				 Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
				 return user_code+"登陆失败在"+expire+"秒之内还可以登陆"+(num-count-1)+"次";
			 }else{
				 //超过指定的登陆次数，插入key限制登陆5分钟
				 String timeLock = Users.loginTimeLock(user_code);
				 redisTemplate.opsForValue().set(timeLock,"1");
				 redisTemplate.expire(timeLock,5,TimeUnit.MINUTES);
				 return "超过登陆次数5次，已被限制5分钟";
			 }
		}else{
			redisTemplate.opsForValue().set(key,"1");
			redisTemplate.expire(key,2,TimeUnit.MINUTES);
			return "登陆失败在2分钟之内还可以登陆"+(num-1)+"次";
		}

    }

    //查看当前用户是否被限制登陆
    @Override
    public Map loginUserLock(String user_code) {
    	Map<String,Object> map=new HashMap<String,Object>();
        String key = Users.loginTimeLock(user_code);
		Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
		if (redisTemplate.hasKey(key)) {
			map.put("falg",true);
			map.put("lockTime",expire);
        }else{
        	map.put("falg",false);
		}
		return map;
    }

}
