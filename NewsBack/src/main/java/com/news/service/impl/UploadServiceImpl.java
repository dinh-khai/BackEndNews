package com.news.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.news.common.Constants;
import com.news.common.Utils;
import com.news.exception.ErrMessage;
import com.news.exception.MyException;
import com.news.service.UpLoadService;

@Service
public class UploadServiceImpl implements UpLoadService{
	
	/**
	 * 
	 */
	@Override
	public String upload(MultipartFile file,String folderName,ErrMessage errMessage,HttpServletRequest request) {
		String baseUrl=null;					//
		String imageURL=null;					// url of image
		String folderUrl =null;					// 
		File folder=null;						// 
		String dateFormat=null;
		String fileName=null;
		
		try {
			
			if (!Utils.checkFileExtention(file.getOriginalFilename(),Constants.IMAGE_EXTENTIONS)) {
				errMessage.setMsg("Err file name extention");
				errMessage.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
				return null;
			}
			
			baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
					.replacePath(null)
					.build()
					.toUriString();
			dateFormat="ddMMyyyy";
			folderName=folderName+"/"+Utils.converDateToStr(dateFormat, new Date());
			folderUrl=new ClassPathResource("/static").getFile().getAbsolutePath()
					  +"/"+folderName;
			folder=new File(folderUrl);
			if(!folder.exists()) {
				folder.mkdirs();
			}
			
			dateFormat="ddMMyyyyhhmmss";
			fileName=getNewFileName(file.getOriginalFilename(),dateFormat);
			// create file upload
			File saveFile=new File(folder + File.separator
								   +fileName);
			BufferedOutputStream out= new BufferedOutputStream(new FileOutputStream(saveFile));
			out.write(file.getBytes());
			imageURL=baseUrl+"/"+folderName+"/"+saveFile.getName();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageURL;
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	private String getNewFileName(String fileName,String dateFormat) {
		try {
			String[] strArr=fileName.split("[.]");
			if(strArr.length>=2) {
				fileName=fileName.substring(0,fileName.length()-strArr[strArr.length-1].length())
						 +Utils.converDateToStr(dateFormat, new Date())
						 +"."+strArr[strArr.length-1];
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
		return fileName;
	}

}
