package com.news.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private String userName;
	private long id;
}
