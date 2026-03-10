package kr.hi.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hi.community.model.util.CommentCriteria;
import kr.hi.community.model.util.PageMaker;
import kr.hi.community.model.vo.CommentVO;
import kr.hi.community.service.TestService;

@RestController
@RequestMapping("/api/v2/posts")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	// 문항3을 위해 통합형식으로 구현
	@GetMapping("/{po_num}/comments")
	public ResponseEntity<Map<String, Object>> getCommentlist(
		@PathVariable("po_num") int poNum,
		CommentCriteria cri){
		
		//댓글목록 + 페이지정보 한꺼번에 받아오기 
		//문항3번 통합처리 하기위해서
		Map<String, Object> result = testService.getComments(poNum, cri);

		return ResponseEntity.ok(result);
	}
	

}
