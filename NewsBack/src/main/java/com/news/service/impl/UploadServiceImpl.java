package com.news.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.news.service.UpLoadService;

@Service
public class UploadServiceImpl implements UpLoadService{

	@Override
	public String upload(MultipartFile file,String forder,String defaultImage,String serverName,int port) {
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
			imageURL="http://localhost:8080/image/"+forder+"/"+saveFile.getName();
			out.close();
			
		} catch (IOException e) {
			imageURL=defaultImage;
		}
		return imageURL;
	}

}
