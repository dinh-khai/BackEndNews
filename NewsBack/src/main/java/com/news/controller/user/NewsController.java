package com.news.controller.user;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.entity.Comment;
import com.news.entity.MoreDescription;
import com.news.entity.News;
import com.news.service.NewsService;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {
	@Autowired
	NewsService newsService;
	
	@GetMapping("/getNews")
	public News findById(@RequestParam long id) {
		return newsService.findById(id);
	}
	
	@GetMapping("/all")
	public List<News> findAllNews(){
		return newsService.findAll();
	}
	
	@GetMapping("/newsByCate/{id}/{page}")
	public List<News> getListNewsByCate(@PathVariable int id,@PathVariable int page){
		return newsService.listNewsByCategory(id, page);
	}
	
	@GetMapping("/allCommentOfNews/{id}")
	public Set<Comment> getAllCommentOfNews(@PathVariable long id) throws Exception{
			return newsService.listComment(id);
	}
	
//	list most fetured
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
	
	@GetMapping("/loadMoreDes")
	public Set<MoreDescription> loadMoreDes(@RequestParam long id){
		return newsService.findById(id).getListMoreDes();
	}
}
