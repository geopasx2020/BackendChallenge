package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

@Entity
	@Table(name = "post")
	public class Post {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;

	   
	    
	    @Column(columnDefinition="TEXT")
	    private String title;

	    @Column(columnDefinition="TEXT")
	    private String content;

	    @Column(name="createdDate")
	    private Date createdDate=new Date();
	    @JsonIgnore
		@OneToMany(cascade=CascadeType.ALL,targetEntity=Comment.class)
	    @JoinColumn(name="post_id",referencedColumnName="id")
		private List<Comment> comment;
	    
	    @ManyToOne(cascade=CascadeType.ALL,targetEntity=User.class)
	    private User user;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public List<Comment> getComment() {
			return comment;
		}

		public void setComment(List<Comment> comment) {
			this.comment = comment;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "Post [id=" + id + ", title=" + title + ", content=" + content + ", createdDate=" + createdDate
					+ ", comment=" + comment + ", user=" + user + "]";
		}

		
	

	    

	   
}
