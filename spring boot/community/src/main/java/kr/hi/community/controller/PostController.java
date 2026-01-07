package kr.hi.community.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.hi.community.model.dto.PostDTO;
import kr.hi.community.model.util.Criteria;
import kr.hi.community.model.util.CustomUser;
import kr.hi.community.model.util.PageMaker;
import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.PostVO;
import kr.hi.community.service.PostService;


@Controller
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/post/list/{num}")
	public String postList(Model model,
		@PathVariable("num") int boardNum,
		//기본생성자를 이용하여 객체를 생성한 후 ?뒤에 변수값이 필드와 일치하면 값을 수정
		Criteria cri) {
		//Criteria에 게시판 번호를 넣어줌
		cri.setBoardNum(boardNum);

		//서비스에게 게시판 번호에 맞는 게시글 목록을 가져오라고 요청 
		//가져온 게시글 목록을 'list'에 저장
		ArrayList<PostVO> list = postService.getPostList(cri); 
		
		//서비스에게 게시판 목록을 가져오라고 요청 
		ArrayList<BoardVO> boardList = postService.getBoardList();
		
		//서비스에게 페이지 정보(검색어, 게시판, 타입)을 주면서 일치하는 게시글 수를 가져오라고 요청 
		int totalCount = postService.getTotalCount(cri);
		//페이지메이커를 생성
		PageMaker pm = new PageMaker (3, cri, totalCount);
		
		//게시글 목록을 화면에 전송
		model.addAttribute("list", list);
		model.addAttribute("boardNum", boardNum);
		//게시판 목록을 화면에 전송 
		model.addAttribute("boardList", boardList);
		
		model.addAttribute("pm", pm);
		return "/post/list"; //post폴더에 list.html을 화면으로 보내줌
		
	}
	
	
	@GetMapping("/post/detail/{num}")
	public String postDetail(Model model,
		@PathVariable("num") int po_num) {
		// System.out.println(po_num); 
		// num이 잘 넘어오는지 확인 써놓고 url에 http://localhost:8080/post/detail/숫자 아무거나 쓰고 콘솔에 숫자 넘어오는지 확인
		// 게시글 번호를 이용해서 조회수 증가
		postService.updateView(po_num);	//서비스에게 일을시켜!
		// 게시글 번호를 이용해서 게시글 가져옴
		// 게시글 번호(기본키)를 이용하여 게시글을 조회하면? -> 최대 1개(왜?) 기본키니까
		// 기본키니까 -> 기본키의 정의 -> 기본키로 검색하면 최대 1행이 조회되는 컬럼
		PostVO post = postService.getPost(po_num);
		// System.out.println(post);  // 잘 가져오는지 확인 -> 매퍼까지 다 작성한다음에 게시글 클릭하면 콘솔에 정보가 넘어옴
		// 가져온 게시글을 화면에 전달
		model.addAttribute("post", post);
	    return "/post/detail";
	}
	
	@GetMapping("/post/insert")
	public String postInsert(Model model) {
		//서비스에게 게시글 목록을 가져오라고 요청
		ArrayList<BoardVO> list = postService.getBoardList();
		//가져온 게시판 목록을 화면에 전달
		model.addAttribute("list", list);
		return "/post/insert";
	}
	
	@PostMapping("/post/insert")
	//만약 DTO 사용안할 경우 @RequestParam("title") String title 이렇게 하나씩  
	public String postInsertPost(
			//게시글 등록에 필요한 정보를 받아옴
			PostDTO post, //제목, 내용, 게시판번호
			@AuthenticationPrincipal CustomUser customUser //작성자(로그인한 사용자 정보) 
			) {
		// System.out.println(customUser); //화면확인 -> 다른정보 post / 유저정보 customUser넣고 하면됨
		// 게시글 정보와 작성자 정보를 서비스에게 주면서 등록하라고 요청
		boolean result = postService.insertPost(post, customUser); //일 시킨결과값을 알려줘 불리언 변수(result)에 저장해서 활용하게!
		//등록에 성공하면 /post/list로 이동, 실패하면 /post/insert로 이동
		if(result) {
			return "redirect:/post/list/" + post.getBoard();
		} 
		return "redirect:/post/insert"; 
	}
	
	@PostMapping("/post/delete/{num}")
	public String postDelete(
		//게시글 번호 가져와줘
		@PathVariable("num") int postNum,
		//로그인한 회원 정보를 가져옴
		@AuthenticationPrincipal CustomUser customUser){
		// 서비스에게 게시글 번호를 주면서 게시글 정보를 가져오라고 요청
		PostVO post = postService.getPost(postNum);
		// 서비스에게 게시글 번호와 사용자 정보를 주면서 게시글을 삭제하라고 요청
		postService.deletePost(postNum, customUser);
		return"redirect:/post/list/" + post.getPo_bo_num(); //삭제한 게시판으로 가기 위해 po_bo_num()
	}
	
	@GetMapping("/post/update/{num}")
	public String postUpdate(
		@PathVariable("num") int postNum,
		//로그인한 회원 정보를 가져옴
		@AuthenticationPrincipal CustomUser customUser) {
		return "/post/update";
	}
}
