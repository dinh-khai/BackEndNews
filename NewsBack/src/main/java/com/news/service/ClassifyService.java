package com.news.service;

import java.util.List;

import com.news.entity.NewsClassification;

public interface ClassifyService {

	List<NewsClassification> findAll();

}
