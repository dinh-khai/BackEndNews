package com.news.controller.general;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.resp.NewsDTOResp;
import com.news.dto.resp.PaginationDTOResp;
import com.news.entity.News;
import com.news.service.NewsService;

@RestController
@RequestMapping("/api/news/general/news")
public class NewsGeneralController {
	@Autowired
	NewsService newsService;
	
	/**
	 * find news by id
	 * 
	 * @param id
	 * @return news found
	 */
	@GetMapping("/getNews")
	public NewsDTOResp findById(@RequestParam long id) {
		return newsService.findById(id);
	}
	
	/**
	 * api news most featured
	 * 
	 * @return list news found
	 */
	@GetMapping("/featured")
	public List<News> mostFeatured(){
		return newsService.mostFeatured();
	}
	
//	news new
	@GetMapping("/new")
	public List<News> getNewsNew(){
		return newsService.mostNews();
	}
	
//	top 6 by cate
	@GetMapping("/mostByCate/{id}")
	public List<News> mostByCate(@PathVariable int id){
		return newsService.listTop6NewsByCate(id);
	}
	
//	top 4 by classify
	@GetMapping("/mostByClassify/{id}")
	public List<News> mostByClassify(@PathVariable int id){
		return newsService.listTop4NewsByClassify(id);
	}
	
//	most views
	@GetMapping("/mostViews")
	public List<News> mostViews(){
		return newsService.mostViews();
	}
	
//	update view
	@PutMapping("/updateView")
	public void updateView(@RequestParam long id) {
		newsService.updateView(id);
		return;
	}
	
//	news by category 
	@GetMapping("/paging/{categoryId}/{page}")
	public PaginationDTOResp paging(@PathVariable Map<String, String> paging ) {
		int page=Integer.parseInt(paging.getOrDefault("page","0"));
		int categoryId=Integer.parseInt(paging.get("categoryId"));
		return newsService.getNewsByCategory(page, categoryId);
	}
}
