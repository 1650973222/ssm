package cn.hba.dao;

import java.util.List;

import cn.hba.entity.BookInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

public interface BookInfoMapper {
	public List<BookInfo>getBookInfoList(@Param("book_type") Integer book_type, @Param("book_name") String book_name,
										 @Param("is_borrow") Integer is_borrow);
	public int update(@Param("id") Integer id);

	public List<BookInfo>selectAll();

	public int delById(@Param("id") Integer id);

	public int getAllById(@Param("id") Integer id);

	public BookInfo getAll(@Param("id") Integer id);

}
