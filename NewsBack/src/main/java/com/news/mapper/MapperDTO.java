package com.news.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.news.dto.resp.CommentDTOResp;
import com.news.dto.resp.NewsDTOResp;
import com.news.dto.resp.ReCommentDTOResp;
import com.news.dto.resp.RoleDTOResp;
import com.news.dto.resp.UserDTOResp;
import com.news.entity.Comment;
import com.news.entity.News;
import com.news.entity.ReComment;
import com.news.entity.Role;
import com.news.entity.User;

@Component
public class MapperDTO {
	/**
	 * mapper news entity to news dto
	 * @param news
	 * @return
	 */
	public NewsDTOResp mapperNewsDTO(News news) {
		NewsDTOResp dto = new NewsDTOResp();
		dto.setId(news.getId());
		dto.setTitle(news.getTitle());
		dto.setDescription(news.getDescription());
		dto.setCategory(news.getCategory());
		dto.setClassify(news.getClassify());
		dto.setImage(news.getImage());
		dto.setFeatured(news.isFeatured());
		dto.setUserInsert(news.getUserInsert());
		dto.setUserUpdate(news.getUserUpdate());
		dto.setTimeInsert(news.getTimeInsert());
		dto.setTineUpdate(news.getTimeUpdate());
		int count=0;
		for(Comment comment:news.getListComments()) {
			count+=comment.getListReComment().size();
		}
		dto.setNumberOfComment(count+news.getListComments().size());
		dto.setViews(news.getViews());
		return dto;
	}
	
	/**
	 * mapper user entity to userDTO
	 * 
	 * @param user
	 * @return
	 */
	public UserDTOResp mapperUserDTO(User user) {
		UserDTOResp dto =new UserDTOResp();
		dto.setUserName(user.getUserName());
		dto.setFullName(user.getFullName());
		dto.setAvatar(user.getAvatar());
		dto.setEmail(user.getEmail());
		dto.setCreatedTime(user.getCreatedTime());
		dto.setAdmin(user.isAdmin());
		
		Set<RoleDTOResp> roles=new HashSet<>();
		for(Role role:user.getRoles()) {
			RoleDTOResp roleDto=mapperRoleDTO(role);
			roles.add(roleDto);
			
		}
		
		dto.setRoles(roles);
		return dto;
	}
	
	/**
	 * mapper comment entity to comment dto
	 * @param comment
	 * @return
	 */
	public CommentDTOResp mapperCommentDTO(Comment comment) {
		CommentDTOResp dto=new CommentDTOResp();
		dto.setId(comment.getId());
		dto.setDescription(comment.getContent());
		dto.setCreatedTime(comment.getCreatedTime());
		dto.setUser(mapperUserDTO(comment.getUserCreator()));
		dto.setNews(mapperNewsDTO(comment.getNews()));
		return dto;
	}
	
	/**
	 * mapper recomment entity to recomment dto
	 * @param reComment
	 * @return
	 */
	public ReCommentDTOResp mapperReCommentDTO(ReComment reComment) {
		ReCommentDTOResp dto=new ReCommentDTOResp();
		dto.setId(reComment.getId());
		dto.setDescription(reComment.getContent());
		dto.setUser(mapperUserDTO(reComment.getUserCreator()));
		dto.setComment(mapperCommentDTO(reComment.getComment()));
		return dto;
	}
	
	/**
	 * 
	 * @param role
	 * @return
	 */
	public RoleDTOResp mapperRoleDTO(Role role) {
		RoleDTOResp dto=new RoleDTOResp();
		dto.setId(role.getId());
		dto.setName(role.getName());
		return dto;
	}
}
