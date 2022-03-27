package com.example.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name="user_role",
	joinColumns= {@JoinColumn(name="fcr_key",referencedColumnName="ID")},
	inverseJoinColumns= {
	@JoinColumn(name="ROLE_ID",
	referencedColumnName="ID")
	})
	private List<Role> role;
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,targetEntity=Post.class)
	 @JoinColumn(name="user_id",referencedColumnName="id")
	private List<Post> post;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + ", post=" + post
				+ "]";
	}

	

	
	
	
	
	
	
	

}
