package com.news.dto.resp;

import java.io.Serializable;
import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDTOResp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String description;
	private Date createdTime;
	private UserDTOResp user;
	private NewsDTOResp news;
}
