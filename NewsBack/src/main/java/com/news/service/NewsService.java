package com.news.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.news.entity.Comment;
import com.news.entity.News;

public interface NewsService {
	
//	get News
	News findById(long id);
	
	List<News> findAll();
	
//	paging 
	List<News> listNewsByCategory(int id, int page);
	
//	get all comments of news
	Set<Comment> listComment(long id);

//	save news
	News saveNews(String title,String description,int cateId,int classifyId
				 ,boolean featured,MultipartFile file,String serverName,int port);
	
//	update news
	void updateNews(long id,String title,String description,int cateId,int classifyId
			 ,boolean featured,MultipartFile file,String serverName,int port);

// delete news
	void deleteNews(long id);
	
//	
	List<News> mostFeatured();
	
//	get new news
	List<News> mostNews();
	
//	top 6 news by category
	List<News> listTop6NewsByCate(int id);
	
// 	top 4 news by classify
	List<News> listTop4NewsByClassify(int id);
	
//	most views
	List<News> mostViews();
	
//	update view
	void updateView(long id);
}

