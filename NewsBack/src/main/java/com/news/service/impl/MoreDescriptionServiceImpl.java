package com.news.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dto.MoreDescriptionDto;
import com.news.entity.MoreDescription;
import com.news.repos.MoreDescriptionRepos;
import com.news.repos.NewsRepos;
import com.news.service.MoreDescriptionService;
@Service
public class MoreDescriptionServiceImpl implements MoreDescriptionService{
	@Autowired
	MoreDescriptionRepos desRepos;
	
	@Autowired
	NewsRepos newsRepos;

	@Override
	public void save(MoreDescriptionDto desMore) {
		for(int i=0;i<desMore.getDescriptions().size();i++) {	
			MoreDescription des=new MoreDescription();
			des.setDescription(desMore.getDescriptions().get(i));
			des.setNews(newsRepos.findById(desMore.getIdNews()).orElse(null));
			desRepos.save(des);
		}
		return;
		
	}
	
}
