package com.news.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.news.dto.CommentDTO;
import com.news.dto.NewsDTO;
import com.news.dto.ReCommentDTO;
import com.news.dto.RoleDTO;
import com.news.dto.UserDTO;
import com.news.entity.Comment;
import com.news.entity.News;
import com.news.entity.ReComment;
import com.news.entity.Role;
import com.news.entity.User;

@Component
public class MapperDTO {
//	mapper news entity to news dto
	public NewsDTO mapperNewsDTO(News news) {
		NewsDTO dto = new NewsDTO();
		dto.setId(news.getId());
		dto.setTitle(news.getTitle());
		dto.setDescription(news.getDescription());
		dto.setCategory(news.getCategory());
		dto.setClassify(news.getClassify());
		dto.setImage(news.getImage());
		dto.setFeatured(news.isFeatured());
		dto.setList(news.getListMoreDes());
		dto.setCreatedTime(news.getTime());
		int count=0;
		for(Comment comment:news.getListComments()) {
			count+=comment.getListReComment().size();
		}
		dto.setNumberOfComment(count+news.getListComments().size());
		dto.setViews(news.getViews());
		return dto;
	}
	
//	mapper user entity to userDTO
	public UserDTO mapperUserDTO(User user) {
		UserDTO dto =new UserDTO();
		dto.setUserName(user.getUserName());
		dto.setFullName(user.getFullName());
		dto.setAvatar(user.getAvatar());
		dto.setEmail(user.getEmail());
		dto.setCreatedTime(user.getCreatedTime());
		
		Set<RoleDTO> roles=new HashSet<>();
		for(Role role:user.getRoles()) {
			RoleDTO roleDto=mapperRoleDTO(role);
			roles.add(roleDto);
			
		}
		
		dto.setRoles(roles);
		return dto;
	}
	
//	mapper comment entity to comment dto
	public CommentDTO mapperCommentDTO(Comment comment) {
		CommentDTO dto=new CommentDTO();
		dto.setId(comment.getId());
		dto.setDescription(comment.getContent());
		dto.setCreatedTime(comment.getCreatedTime());
		dto.setUser(mapperUserDTO(comment.getUserCreator()));
		dto.setNews(mapperNewsDTO(comment.getNews()));
		return dto;
	}
	
//	mapper recomment entity to recomment dto
	public ReCommentDTO mapperReCommentDTO(ReComment reComment) {
		ReCommentDTO dto=new ReCommentDTO();
		dto.setId(reComment.getId());
		dto.setDescription(reComment.getContent());
		dto.setUser(mapperUserDTO(reComment.getUserCreator()));
		dto.setComment(mapperCommentDTO(reComment.getComment()));
		return dto;
	}
	
	public RoleDTO mapperRoleDTO(Role role) {
		RoleDTO dto=new RoleDTO();
		dto.setId(role.getId());
		dto.setName(role.getName());
		return dto;
	}
}
