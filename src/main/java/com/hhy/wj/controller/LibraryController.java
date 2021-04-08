package com.hhy.wj.controller;

import com.hhy.wj.pojo.Book;
import com.hhy.wj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {
	@Autowired
	BookService bookService;
	
	@GetMapping("/api/books")
	public List<Book> list() {
		return bookService.list();
	}
	
	@PostMapping("/api/books")
	public Book addOrUpdate(@RequestBody Book book) throws Exception {
		bookService.addOrUpdate(book);
		return book;
	}
	
	@PostMapping("/api/delete")
	public void delete(@RequestBody Book book) throws Exception {
		bookService.deleteById(book.getId());
	}
	
	@GetMapping("/api/categories/{cid}/books")
	public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception {
		if(cid != 0) {
			return bookService.listByCategory(cid);
		}else {			// cid为0时表示全部，代码见前端。
			return list();
		}
	}
	
	@GetMapping("/api/search")
	public List<Book> serchResult(@RequestParam("keywords") String keywords) {
		// 关键词为空时查询出所有书籍
		if("".equals(keywords)) {
			return bookService.list();
		}else {
			return bookService.Search(keywords);
		}
	}
}
