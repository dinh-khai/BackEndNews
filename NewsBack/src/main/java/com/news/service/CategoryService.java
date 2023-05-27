package com.news.service;

import java.util.List;
import java.util.Optional;

import com.news.dto.resp.CategoryDTOResp;
import com.news.entity.NewsCategory;

public interface CategoryService {

	List<CategoryDTOResp> findAll();

	Optional<NewsCategory> findById(int id);
	
	
}
