package kr.hi.post.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hi.post.model.vo.PostVO;
import kr.hi.post.service.PostService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
public class PostController {
		
	private final PostService postService;
		
	public PostController(PostService postService) {
		this.postService = postService;
	
}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostVO>> postList() {

		List<PostVO> 게시글목록 = postService.getPosts();
		
		return ResponseEntity.ok(게시글목록);
	}
	
	@GetMapping("/posts/{num}")
	public ResponseEntity<PostVO> postDetail(
		@PathVariable("num") int num) {
		PostVO post = postService.getPost(num);
		if(post != null) {
			return ResponseEntity.ok(post);			
		}
		//내용이 없어서 204 전송
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/posts")
	public ResponseEntity<Boolean> postInsertPost(
		@RequestBody PostVO post){
		//서비스에게 게시글 정보를 주면서 등록하고 결과를 알려달라고 요청		
		boolean result = postService.insertPost(post);
		if(result) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
	
	@DeleteMapping("/posts/{num}")
	public ResponseEntity<Boolean> postDelete(
		@PathVariable("num") int num){
		boolean result = postService.deletePost(num);
		
		return ResponseEntity.ok(false);
	}
}
