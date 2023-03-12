package com.news.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.news.common.Constants;
import com.news.dto.req.NewsDTOReq;
import com.news.dto.resp.NewsDTOResp;
import com.news.dto.resp.PaginationDTOResp;
import com.news.entity.Comment;
import com.news.entity.News;
import com.news.exception.Error;
import com.news.exception.MyException;
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
	
	/**
	 * 
	 */
	@Override
	public NewsDTOResp findById(long id) {
		News news=newsRepos.findById(id).orElseThrow(()->new MyException(new Error( HttpStatus.NOT_FOUND,"Not found")));
		NewsDTOResp dto=mapper.mapperNewsDTO(news);
		return dto;
	}
	
	/**
	 * o
	 */
	@Override
	public List<News> findAll() {
		return newsRepos.findAll();
	}

	@Transactional(rollbackFor = {SQLException.class})
	@Override
	public void saveNews(NewsDTOReq dto, MultipartFile file,HttpServletRequest request) {		
		News news=mapperEntity.mapperNews(dto);
		String imageURL=upload.upload(file, Constants.FOLDER_IMAGE_NEWS, request);
		news.setImage(imageURL);
		newsRepos.save(news);	
	}

	@Override
	public void updateNews(long id, NewsDTOReq dto,MultipartFile file,HttpServletRequest request) {
//		News news = newsRepos.findById(id).orElse(null);
//		if(news!=null) {
//			String imageURL=upload.upload(file, Constants.FOLDER_IMAGE_NEWS, request);
//			news=mapperEntity.mapperNews(dto);
//			news.setImage(imageURL);
//		}
//		newsRepos.save(news);
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
	
	/**
	 * get latest news
	 * 
	 * @param num
	 * @return list news latest
	 */
	@Override
	public List<News> getLatestNews(int num) {
			
		return newsRepos.getListLatestNews(num);
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
	public PaginationDTOResp getNewsByCategory(int page, int categoryId) {
		Pageable pageble=PageRequest.of(page, PaginationDTOResp.size);
		Page<News> paging=newsRepos.findAllByCategoryId(categoryId, pageble);
		List<NewsDTOResp> list=new ArrayList<>();
		for(News news:paging.getContent()) {
			NewsDTOResp newsDTO=mapper.mapperNewsDTO(news);
			list.add(newsDTO);
		}
		PaginationDTOResp dto=new PaginationDTOResp(paging.getTotalPages(),paging.getNumber(),list,paging.isFirst(),paging.isLast());
		return dto;
	}

}
