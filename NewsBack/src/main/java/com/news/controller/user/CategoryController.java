package com.news.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.entity.NewsCategory;
import com.news.service.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class CategoryController {
	@Autowired
	CategoryService cateService;
	
	@GetMapping("/category/all")
	public List<NewsCategory> getAllCategory(){
		return cateService.findAll();
	}
	
	@GetMapping("/category/page/{id}")
	public int getPage(@PathVariable int id) throws Exception{
		try {
			return cateService.findById(id).get().getListNews().size();
		} catch (Exception e) {
			throw new Exception("Không tìm thấy");
		}
	}
}
