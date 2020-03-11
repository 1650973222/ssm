package cn.hba.service;

import cn.hba.entity.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BookInfoService {
	public List<BookInfo>getBookInfoList(Integer book_type, String book_name, Integer is_borrow);
	public int update(Integer id);

	public List<BookInfo>selectAll();

	public int delById(Integer id);

	public int getAllById(Integer id);

	public BookInfo getAll(Integer id);
	void getTest();

}
