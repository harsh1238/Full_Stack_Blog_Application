package com.yash.blogger.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.internal.build.AllowPrintStacktrace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;
	
	@Column(name="Post_title",length = 100, nullable=false)
	private String title;
	
	@NotEmpty
	private String content;
	
	private String imagename;
	
	
	private LocalDateTime addedDate;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	

}
