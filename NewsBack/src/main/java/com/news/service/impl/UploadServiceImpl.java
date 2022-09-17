package com.news.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.news.service.UpLoadService;

@Service
public class UploadServiceImpl implements UpLoadService{

	@Override
	public String upload(MultipartFile file,String forder,String defaultImage,HttpServletRequest request) {
		String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
	            .replacePath(null)
	            .build()
	            .toUriString();
		String imageURL=null;
		try {
			String dir=new ClassPathResource("/static/image").getFile().getAbsolutePath()+"/"+forder;
			File fileDir=new File(dir);
			if(!fileDir.exists()) {
				fileDir.mkdirs();
			}
			
			File saveFile=new File(fileDir + File.separator+file.getOriginalFilename());
			BufferedOutputStream out= new BufferedOutputStream(new FileOutputStream(saveFile));
			out.write(file.getBytes());
			imageURL=baseUrl+"/"+forder+"/"+saveFile.getName();
			out.close();
			
		} catch (IOException e) {
			if(defaultImage==null) {
				imageURL=null;
			}
			else {
				imageURL=baseUrl+"/"+ defaultImage;
			}
		}
		return imageURL;
	}

}
