package com.news.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.news.entity.Comment;
import com.news.entity.News;
import com.news.repos.CategoryRepos;
import com.news.repos.ClassifyRepos;
import com.news.repos.NewsRepos;
import com.news.service.NewsService;
import com.news.service.UpLoadService;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsRepos newsRepos;
	
	@Autowired
	UpLoadService upload;
	
	@Autowired
	CategoryRepos cateRepos;
	
	@Autowired
	ClassifyRepos classifyRepos;
	
	@Override
	public News findById(long id) {
		return newsRepos.findById(id).orElse(null);
	}
	
	@Override
	public List<News> findAll() {
		return newsRepos.findAll();
	}

	@Override
	public List<News> listNewsByCategory(int id, int page) {
		return newsRepos.listNewsByCategory(id, page);
	}

	@Override
	public News saveNews(String title, String description, int cateId, int classifyId,
			boolean featured, MultipartFile file, String forder, String defaultImage) {
		News news=new News();
		news.setTime(Calendar.getInstance().getTime());
		news.setTitle(title);
		news.setDescription(description);
		news.setImage(upload.upload(file, forder, defaultImage));
		news.setCategory(cateRepos.findById(cateId).orElse(null));
		news.setClassify(classifyRepos.findById(classifyId).orElse(null));
		news.setViews(0);
		news.setFeatured(featured);
		return newsRepos.save(news);
		
	}

	@Override
	public void updateNews(long id, String title, String description, int cateId, int classifyId, boolean featured,
			MultipartFile file, String forder, String defaultImage) {
		News news = newsRepos.findById(id).orElse(null);
		if(news==null) return;
		String imageURL=upload.upload(file, forder, defaultImage);
		news.setTitle(title);
		news.setDescription(description);
		if(imageURL!=null) {
			news.setImage(imageURL);
		}
		news.setCategory(cateRepos.findById(cateId).orElse(null));
		news.setClassify(classifyRepos.findById(classifyId).orElse(null));
		news.setFeatured(featured);
		newsRepos.save(news);
	}

	@Override
	public void deleteNews(long id) {
		newsRepos.delete(newsRepos.findById(id).orElse(null));
		return;	
	}

	@Override
	public Set<Comment> listComment(long id) {
		return newsRepos.findById(id).orElse(null).getListComments();
	}

	@Override
	public List<News> mostFeatured() {		
		return newsRepos.mostFeatured();
	}

	@Override
	public List<News> mostNews() {
		return newsRepos.listNewNews();
	}

	@Override
	public List<News> listTop6NewsByCate(int id) {
		return newsRepos.listTop6NewsByCate(id);
	}

	@Override
	public List<News> listTop4NewsByClassify(int id) {
		return newsRepos.listTop4NewsByClassify(id);
	}

	@Override
	public List<News> mostViews() {
		return newsRepos.mostViews();
	}

	@Override
	public void updateView(long id) {
		newsRepos.updateView(id);
		return;	
	}
}