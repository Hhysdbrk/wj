package com.hhy.wj.dao;

import com.hhy.wj.pojo.Book;
import com.hhy.wj.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookDAO extends JpaRepository<Book, Integer> {
	List<Book> findAllByCategory(Category category);
	List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}
