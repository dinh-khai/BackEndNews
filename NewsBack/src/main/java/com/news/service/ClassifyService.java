package com.news.service;

import java.util.List;

import com.news.dto.resp.ClassifyDTOResp;

public interface ClassifyService {

	List<ClassifyDTOResp> findAll();

}
