package com.news.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.news.dto.NewsDTO;
import com.news.dto.PaginationDTO;
import com.news.dto.create.NewsCreateDTO;
import com.news.entity.Comment;
import com.news.entity.News;

public interface NewsService {
	
//	get News
	NewsDTO findById(long id);
	
	List<News> findAll();
	
//	get all comments of news
	Set<Comment> listComment(long id);

//	save news
	News saveNews(NewsCreateDTO dto,MultipartFile file,HttpServletRequest request);
	
//	update news
	void updateNews(long id, NewsCreateDTO dto,MultipartFile file,HttpServletRequest request);

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
	
//	get news by category and pagination
	PaginationDTO getNewsByCategory(int page,int categoryId);
}

