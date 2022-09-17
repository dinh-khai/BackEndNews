package com.news.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.news.dto.NewsDTO;
import com.news.dto.PaginationDTO;
import com.news.dto.create.NewsCreateDTO;
import com.news.entity.Comment;
import com.news.entity.News;
import com.news.mapper.MapperDTO;
import com.news.mapper.MapperEntity;
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
	
	@Autowired
	MapperDTO mapper;
	
	@Autowired
	MapperEntity mapperEntity;
	
	@Override
	public NewsDTO findById(long id) {
		News news=newsRepos.findById(id).orElse(null);
		if(news==null) {
			return null;
		}
		NewsDTO dto=mapper.mapperNewsDTO(news);
		return dto;
	}
	
	@Override
	public List<News> findAll() {
		return newsRepos.findAll();
	}

	@Override
	public News saveNews(NewsCreateDTO dto, MultipartFile file,HttpServletRequest request) {
		News news=mapperEntity.mapperNews(dto);
		String imageURL=upload.upload(file, "news", null, request);
		news.setImage(imageURL);
		return newsRepos.save(news);
		
	}

	@Override
	public void updateNews(long id, NewsCreateDTO dto,MultipartFile file,HttpServletRequest request) {
		News news = newsRepos.findById(id).orElse(null);
		if(news!=null) {
			String imageURL=upload.upload(file, "news", null, request);
			news=mapperEntity.mapperNews(dto);
			news.setImage(imageURL);
		}
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

	@Override
	public PaginationDTO getNewsByCategory(int page, int categoryId) {
		Pageable pageble=PageRequest.of(page, PaginationDTO.size);
		Page<News> paging=newsRepos.findAllByCategoryId(categoryId, pageble);
		List<NewsDTO> list=new ArrayList<>();
		for(News news:paging.getContent()) {
			NewsDTO newsDTO=mapper.mapperNewsDTO(news);
			list.add(newsDTO);
		}
		PaginationDTO dto=new PaginationDTO(paging.getTotalPages(),paging.getNumber(),list,paging.isFirst(),paging.isLast());
		return dto;
	}
}
