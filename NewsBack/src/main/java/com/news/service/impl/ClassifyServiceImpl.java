package com.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.news.dto.resp.ClassifyDTOResp;
import com.news.mapper.MapperDTO;
import com.news.repos.ClassifyRepos;
import com.news.service.ClassifyService;

@Service
public class ClassifyServiceImpl implements ClassifyService{
	@Autowired
	ClassifyRepos repos;
	@Autowired
	MapperDTO mapper;

	@Override
	public List<ClassifyDTOResp> findAll() {
		return mapper.mapperClassify(repos.findAll());
	}
	
	
}
