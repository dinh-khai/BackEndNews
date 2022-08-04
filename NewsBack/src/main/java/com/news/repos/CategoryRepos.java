package com.news.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.news.entity.NewsCategory;

@Repository
public interface CategoryRepos extends JpaRepository<NewsCategory, Integer>{
	
}
