package com.example.demo;


import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="comment")
	public class Comment {

		
		    @Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    private Integer id;

		 @Column(nullable=false, unique=true)
			private String name;
		 
		    @Column(columnDefinition="TEXT")
		    private String content;

		    @Column(name="createdDate")
		    private Date createdDate= new Date();
		    
		    @ManyToOne(cascade=CascadeType.ALL,targetEntity=Post.class)
		    private Post post;

			public Integer getId() {
				return id;
			}

			public void setId(Integer id) {
				this.id = id;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
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

			public Post getPost() {
				return post;
			}

			public void setPost(Post post) {
				this.post = post;
			}

			@Override
			public String toString() {
				return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", createdDate=" + createdDate
						+ ", post=" + post + "]";
			}
		    
		  


		
		    
}
