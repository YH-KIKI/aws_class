package kr.hi.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hi.community.model.dto.CommentDTO;
import kr.hi.community.model.util.CommentCriteria;
import kr.hi.community.model.util.CustomUser;
import kr.hi.community.model.util.PageMaker;
import kr.hi.community.model.vo.CommentVO;
import kr.hi.community.service.CommentService;

@RestController //@RestController + @Controller
@RequestMapping("/comment") //이 컨트롤러에 있는 모든 메서드는 /comment로 시작
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/insert")
	public ResponseEntity<String> insert(
		@RequestBody CommentDTO commentDto,
		@AuthenticationPrincipal CustomUser customUser){
		// 서비스에게 댓글 정보와 사용자 정보를 주면서 댓글을 등록하고, 
		//등록 결과를 문자열로 가져오도록 요청
		// 결과 = 서비스야.댓글등록해(댓글정보, 사용자 정보);
		String result = commentService.insertComment(commentDto, customUser);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/list")
	public ResponseEntity<Map<String, Object>> list(
		@RequestBody CommentCriteria cri,
		HashMap<String, Object> map){
		cri.setPerPageNum(3);
		//서비스에게 게시글 번호를 주면서 댓글 목록을 가져오라고 요청
		//댓글목록 = 서비스야.댓글목록가져와(게시글번호);
		List<CommentVO> list = commentService.getCommentList(cri);
		
		//서비스에게 페이지 정보를 주면서 댓글 페이지에 맞는 페이지네이션 정보를 가져오라고 요청(서비스가 준거 pm에 저장해서 쓸꺼야!)
		PageMaker pm = commentService.getPageMarker(cri);
		
		map.put("list", list);
		map.put("pm", pm);
		
		return ResponseEntity.ok(map);
	}
	
	@PostMapping("/delete/{coNum}")
	//@ResponseBody //@RestController일때는 생략
	public ResponseEntity<String> delete(
		//- 댓글 번호를 가져옴
		@PathVariable("coNum") int coNum,
		//- 로그인한 사용자 정보를 가져옴
		@AuthenticationPrincipal CustomUser user){
		//- 서비스에게 댓글번호, 사용자 정보를 주면서 댓글을 삭제하고 결과를 가져오라고 요청
		//결과 = 서비스야.댓글삭제해(댓글번호, 사용자정보);
		String result = commentService.deleteComment(coNum, user);
		
		return ResponseEntity.ok(result);
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<String> update(
		//화면에서 보낸 수정 댓글 정보를 가져옴
		@RequestBody CommentDTO dto,
		//로그인한 사용자 정보 가져옴
		@AuthenticationPrincipal CustomUser user
		){
		//서비스에게 수정 댓글 정보와 사용자 정보 주면서 수정하고 결과를 가져오라고 요청
		// 수정결과 = 서비스야.댓글수정해(수정할댓글정보, 사용자정보); 
		String result = commentService.updateComment(dto, user);
		
		//가져온 결과를 화면에 전달
		return ResponseEntity.ok(result);
	}
	
	

}
