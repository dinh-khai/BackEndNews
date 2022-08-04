package com.news.service;

import java.util.List;

import com.news.entity.News;
import com.news.entity.User;

public interface TestService {
	List<News> findAll();

	void save(User user);
}
