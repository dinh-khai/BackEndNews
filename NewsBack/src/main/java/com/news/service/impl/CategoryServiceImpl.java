package com.news.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.entity.NewsCategory;
import com.news.repos.CategoryRepos;
import com.news.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepos cateRepos;

	@Override
	public List<NewsCategory> findAll() {
		return cateRepos.findAll();
	}

	@Override
	public Optional<NewsCategory> findById(int id) {
		return cateRepos.findById(id);
	}
	
	
}
