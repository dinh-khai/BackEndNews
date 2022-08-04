package com.news.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="news")
public class News implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="time")
	private Date time;
	
	@Column(name="image")
	private String image;
	
	@ManyToOne
	@JoinColumn(name="category_id",nullable = false)
	private NewsCategory category;
	
	@ManyToOne
	@JoinColumn(name="classify_id",nullable = true)
	private NewsClassification classify;
	
	@Column(name="views")
	private int views;
	
	@Column(name="featured")
	private boolean featured;
	
	@JsonIgnore
	@OneToMany(mappedBy = "news",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Comment> listComments;
	
	@JsonIgnore
	@OneToMany(mappedBy = "news",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<MoreDescription> listMoreDes; 
	
}
