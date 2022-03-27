package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

@CrossOrigin(origins="*",allowedHeaders={"*"})
@RestController
public class SpringController {

	@Autowired
	User_Repository user_repository;
	
	@Autowired
	Post_Repository post_repository;
	
	
	
	
	  @Autowired 
	  Comment_Repository comment_repository;
	  
	
	
	
	@ResponseBody
	@PostMapping("/{id}/createpost")
	public  void createPost(@PathVariable("id") int id,@RequestBody Post post) {
		User user = user_repository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("No post found with id="+id));

					
	
		user.getPost().add(post);
		user_repository.save(user);

	System.out.println(user);
	System.out.println(post);
		
		
	
	}
	
	@ResponseBody
	@PostMapping("{id}/createcomment")
	public  void createPostComment(@PathVariable("id") Integer id,@RequestBody Comment comment) {
		
	Post post=post_repository.findById(id).orElseThrow(()-> 
	new ResourceNotFoundException("No post found with id="+id));
	
	
	post.getComment().add(comment);
	post_repository.save(post);
	
	}
	
	

	
    @GetMapping("/User")
    public Map<String, Object> getEmail() {
      Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", authentication.getUsername());
        userMap.put("error", false);
        return userMap;
    }
	
@GetMapping("/allUsers")
public List <User> getUsers(){
	
	return  user_repository.findAll();
}

@GetMapping("/getpostsofanotheruser/{id}")
public List<Post> getPostsOfAnotherUser(@PathVariable("id") int id){
	
    List<Post> postList= post_repository.findByUser( user_repository.findById(id).get());
  
   
	return postList;
}


@GetMapping("/getAllposts")
public List<Post> getAllPost(){
    return post_repository.findAllByOrderByCreatedDateDesc();
}

@GetMapping("/getcommentsofposts/{id}")
public List<Comment> getCommentOfPost(@PathVariable("id")int id){
	
    List<Comment> commentList= comment_repository.findCommentByPost( post_repository.findById(id).get());
   
    return commentList;
}

@PutMapping("/updatepost/{id}/{idPost}")
public Post updatePost(@PathVariable("id")Integer id,@PathVariable("idPost") int idPost,@RequestBody Post post) {
	Post postList=post_repository.findByUserAndId( user_repository.findById(id).get(),idPost);
	
	
		if(postList.getId().equals(idPost)) {
		
			postList.setTitle(post.getTitle());

			postList.setContent(post.getContent());
			postList.setCreatedDate(post.getCreatedDate());
			
			post_repository.save(postList);
		}
		
		return postList;
		}
	


@PutMapping("/updatecomment/{id}/{idComment}")
public Comment updateComment(@PathVariable("id")Integer id,@PathVariable("idComment") int idComment,@RequestBody Comment comment) {
	Comment commentList=comment_repository.findByPostAndId( post_repository.findById(id).get(),idComment);
	
		if(commentList.getId().equals(idComment)) {
			commentList.setContent(comment.getContent());
			
			commentList.setCreatedDate(comment.getCreatedDate());
			commentList.setName(comment.getName());
			
			comment_repository.save(commentList);
		}
		return commentList;
		
}
	
	@DeleteMapping("deletePost/{userId}/{postId}")
	public void deletePost(@PathVariable("userId")int userId,@PathVariable("postId") int postId,@RequestBody Post post){
		Post postList=post_repository.findByUserAndId( user_repository.findById(userId).get(),postId);
		
		
		if(postList.getId().equals(postId)) {
		post_repository.delete(postList);
			}
			}
	
	
	


	@DeleteMapping("deletePost/{postId}/{commentId}")
	public void deleteComment(@PathVariable("postId")int postId,@PathVariable("commentId") int commentId,@RequestBody Comment comment){
		Comment commentList=comment_repository.findByPostAndId( post_repository.findById(postId).get(),commentId);
		
		if(commentList.getId().equals(commentId)) {
		comment_repository.delete(commentList);
			}
			}
	
	}



	
	

