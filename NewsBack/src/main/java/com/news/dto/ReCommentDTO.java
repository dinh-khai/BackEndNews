package com.news.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("serial")
public class ReCommentDTO implements Serializable{
	private long id;
	private String description;
	private UserDTO user;
	private CommentDTO comment;
}
