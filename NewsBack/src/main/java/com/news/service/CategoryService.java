package com.news.service;

import java.util.List;
import java.util.Optional;

import com.news.entity.NewsCategory;

public interface CategoryService {

	List<NewsCategory> findAll();

	Optional<NewsCategory> findById(int id);
	
	
}
