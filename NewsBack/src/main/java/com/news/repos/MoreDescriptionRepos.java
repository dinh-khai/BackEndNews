package com.news.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.news.entity.MoreDescription;

@Repository
public interface MoreDescriptionRepos extends JpaRepository<MoreDescription, Long>{

}
