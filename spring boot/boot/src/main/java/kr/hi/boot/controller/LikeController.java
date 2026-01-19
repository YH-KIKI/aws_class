package kr.hi.boot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hi.boot.model.util.CustomUser;
import kr.hi.boot.service.LikeService;

@RestController //비동기일때 RestController
public class LikeController {
	
	private final LikeService likeService;
	// 생성자를 이용한 의존성 주입
	public LikeController(LikeService likeService) {
			this.likeService = likeService;	
	}
	
	
	@PostMapping("/api/v2/posts/{postNum}/like")
	public ResponseEntity<String> LikePost(
		//게시글 번호 가져옴
		@PathVariable("postNum") int postNum,
		//로그인한 사용자 정보 가져옴
		@AuthenticationPrincipal CustomUser user){
		
		//서비스에게 게시글번호, 사용자 정보를 주면서 게시글 추천하라고 요청하고 결과를 가져옴
		String result = likeService.upLike(postNum, user);
		
		//가져온 결과를 화면에 전달
		return ResponseEntity.ok(result);
	}

}
