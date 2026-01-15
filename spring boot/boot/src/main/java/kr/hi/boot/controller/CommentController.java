package kr.hi.boot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hi.boot.model.vo.Comment;
import kr.hi.boot.service.CommentService;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
	
	//@Autowired 대신
	private final CommentService commentService;
	
	// 생성자를 이용한 의존성 주입
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
		
	}
	
	@GetMapping("/posts/{num}/comments")
	public ResponseEntity<List<Comment>> getComments(
		//화면에서 보낸 게시글 번호를 가져옴
		@PathVariable("num") int poNum){
		//서비스에게 게시글 번호를 주면서 댓글 목록 가져오라고 요청
		List<Comment> list = commentService.getComments(poNum);
		
		return ResponseEntity.ok(list); //비동기는 바로 여기로 뿌려줘요
	}
	

}
