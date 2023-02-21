package com.news.repos;

import java.util.List;

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
	
	 /**
	  * 
	  * @param categoryId
	  * @return
	  */
	 List<News> findByCategoryId(int categoryId);
////	get list news by id classification
//	List<News> findAllByIdClassif(int idClassif);
////	get most featured news
	@Query(value="select * from News n where n.featured=true order by n.time DESC limit 4",nativeQuery = true)
	List<News> mostFeatured();
	
	@Query(value="select * from News n where n.featured=true limit 2",nativeQuery = true)
	News secondFeatured();
	
	@Query(value="select * from News n where n.featured=true limit 1,2",nativeQuery = true)
	List<News> otherFeatured();
	
	
//	get new news
	@Query(value="select * from News n order by n.time DESC limit 6",nativeQuery = true)
	List<News> listNewNews();
	
//	get list 6 news by category
	@Query(value="select * from News n where category_id=? order by n.time DESC limit 6",nativeQuery = true)
	List<News> listTop6NewsByCate(int id);
	
//	get list 4 news by classify
	@Query(value="select * from News n where classify_id=? order by n.time DESC limit 3",nativeQuery = true)
	List<News> listTop4NewsByClassify(int id);
	
//	get news by category
	@Query(value="select * from News n where n.category_id=? order by n.id DESC limit ?,1",nativeQuery = true)
	List<News> listNewsByCategory(int id,int page);
	
//	most view
	@Query(value="select * from News n order by n.views DESC limit 3",nativeQuery = true)
	List<News> mostViews();
	
//	update view
	@Transactional
	@Modifying
	@Query("update News n set n.views=n.views+1 where n.id=?1")
	void updateView(long id);
	
//	get all news by category and pagination
	Page<News> findAllByCategoryId(int id,Pageable pageable);
	
	
}