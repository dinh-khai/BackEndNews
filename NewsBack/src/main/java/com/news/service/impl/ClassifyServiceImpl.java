package com.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.entity.NewsClassification;
import com.news.repos.ClassifyRepos;
import com.news.service.ClassifyService;

@Service
public class ClassifyServiceImpl implements ClassifyService{
	@Autowired
	ClassifyRepos repos;

	@Override
	public List<NewsClassification> findAll() {
		return repos.findAll();
	}
	
	
}
