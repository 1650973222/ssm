package cn.hba.controller;

import java.util.List;

import javax.annotation.Resource;

import cn.hba.service.BookInfoService;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hba.entity.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookInfoController {
	
	@Resource
	private BookInfoService bookInfoService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="book_type",required=false)Integer book_type,
					   @RequestParam(value="book_name",required=false)String book_name,
					   @RequestParam(value="is_borrow",required=false)Integer is_borrow,
					   @RequestParam(value="pageIndex",required=false)Integer pageIndex,
					   Model model){
		if(pageIndex==null){
			pageIndex=1;
		}
		Page<Object> pages = PageHelper.startPage(pageIndex, 5);
		List<BookInfo> bookInfoList = bookInfoService.getBookInfoList(book_type, book_name, is_borrow);
		model.addAttribute("bookInfoList",bookInfoList);
		model.addAttribute("pages",pages);
		model.addAttribute("book_type", book_type);
		model.addAttribute("book_name", book_name);
		model.addAttribute("is_borrow", is_borrow);
		
		return "list";
	}
	@RequestMapping("/update")
	public String update(@RequestParam("id")Integer id){
		bookInfoService.update(id);
		return "redirect:/list";
	}
	@RequestMapping("getAll/{id}")
	@ResponseBody
	public String getAll(@PathVariable Integer id){
		BookInfo info = bookInfoService.getAll(id);
		return JSON.toJSONString(info);
	}


	
}
