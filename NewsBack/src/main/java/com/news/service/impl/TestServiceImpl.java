package com.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.news.entity.News;
import com.news.entity.User;
import com.news.repos.NewsRepos;
import com.news.repos.UserRepos;
import com.news.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	@Autowired
	NewsRepos repos;
	
	@Autowired
	UserRepos uRepos;

	@Override
	public List<News> findAll() {
		return repos.findAll();
	}

	@Override
	public void save(User user) {
		uRepos.save(user);
		return;
	}
	
	
	
}
