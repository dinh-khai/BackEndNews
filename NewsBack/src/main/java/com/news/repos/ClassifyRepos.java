package com.news.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.news.entity.NewsClassification;

@Repository
public interface ClassifyRepos extends JpaRepository<NewsClassification, Integer>{

}
