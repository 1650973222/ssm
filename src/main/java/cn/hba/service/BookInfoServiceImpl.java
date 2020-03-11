package cn.hba.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import cn.hba.dao.BookInfoMapper;
import cn.hba.entity.BookInfo;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Resource
    private BookInfoMapper bookInfoMapper;

    @Resource
    private RedisTemplate<String ,Object>redisTemplate;


    @Override
    public List<BookInfo> getBookInfoList(Integer book_type, String book_name, Integer is_borrow) {

        return bookInfoMapper.getBookInfoList(book_type, book_name, is_borrow);
    }

    @Override
    public int update(Integer id) {

        return bookInfoMapper.update(id);
    }

    @Override
    public List<BookInfo> selectAll() {

        return bookInfoMapper.selectAll();
    }

    @Override
    public int delById(Integer id) {
        return bookInfoMapper.delById(id);
    }

    @Override
    public int getAllById(Integer id) {


        return bookInfoMapper.getAllById(id);
    }

    @Override
    public BookInfo getAll(Integer id) {
        HashOperations<String, Integer, BookInfo> hashOperations = redisTemplate
                .opsForHash();
        String keyName = BookInfo.getKeyName();
        BookInfo bookInfo;
        if(hashOperations.hasKey(keyName, id)){
            bookInfo=hashOperations.get(keyName,id);
            System.out.println("redis中查询"+bookInfo);
        }else {
             bookInfo = bookInfoMapper.getAll(id);
             System.out.println("数据库中查询"+bookInfo);
            hashOperations.put(keyName,bookInfo.getBook_id(),bookInfo);
        }
        return bookInfo;
    }

    @Test
    public void getTest() {
        ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext("string-redis.xml");
        RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) path.getBean("redisTemplate");
        if (redisTemplate.hasKey("username")) {
            redisTemplate.expire("username", 2, TimeUnit.DAYS);
            Object username = redisTemplate.opsForValue().get("username");
            System.out.println("redis中查询" + username.toString());
        } else {
            System.out.println("数据库中查询:张三");
            redisTemplate.opsForValue().set("username", "张三");
        }
    }

}
