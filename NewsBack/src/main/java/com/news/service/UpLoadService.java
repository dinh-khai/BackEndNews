package com.news.service;

import org.springframework.web.multipart.MultipartFile;

public interface UpLoadService {
	String upload(MultipartFile file,String forder,String defaultImage,String serverName,int port);
}
