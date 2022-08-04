package com.news.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoreDescriptionDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long idNews;
	List<String> descriptions;
//	List<MultipartFile> listFiles;
}
