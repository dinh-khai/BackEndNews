package com.news.repos;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.news.entity.News;

@Repository
public interface NewsRepos extends JpaRepository<News, Long> {

	@Transactional
	@Modifying
	@Query("update News n set n.views=n.views+1 where n.id=?1")
	void updateView(long id);
	
//	get all news by category and pagination
	Page<News> findAllByCategoryId(int id,Pageable pageable);
	
	// get all news by category and pagination
    Page<News> findAllByClassifyId(int id, Pageable pageable);
    
    @Query("Select n from News n where n.title like %:q% or n.description like %:q%")
    Page<News> search(String q, Pageable pageable);
    
    @Query("Select n from News n where n.featured = true")
    Page<News> getNewsByFeatured(Pageable pageable);
	
}