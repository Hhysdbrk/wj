package com.hhy.wj.service;

import com.hhy.wj.dao.CategoryDAO;
import com.hhy.wj.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
	@Autowired
	CategoryDAO categaryDAO;
	
	public List<Category> list() {
		Sort sort =Sort.by(Sort.Direction.DESC, "id");
		return categaryDAO.findAll(sort);
	}
	
	public Category get(int id) {
		Category c = categaryDAO.findById(id).orElse(null);
		return	c;
	}
}
