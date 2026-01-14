package kr.hi.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.hi.boot.model.dto.PostDTO;
import kr.hi.boot.model.util.CustomUser;
import kr.hi.boot.model.util.PageMaker;
import kr.hi.boot.model.util.PostCriteria;
import kr.hi.boot.model.vo.Board;
import kr.hi.boot.model.vo.Post;
import kr.hi.boot.service.PostService;

@Controller
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("post/list")
	public String postList(Model model, 
			// 화면에서 보낸 페이지 정보를 가져옴
			PostCriteria cri) {
		
		//한페이지에 게시글 10개
		cri.setPerPageNum(3);
		
		//서비스에게 현재 페이지 정보를 주면서(cri) 게시글 목록을 가져오라고 함
		ArrayList<Post> list = postService.getPostList(cri);
		
		//서비스에게 게시판 목록 전체를 가져오라고 요청
		// 게시판목록저장 = 서비스야.게시판목록전체를가져와
		List<Board> boardList = postService.getBoardList();
		
		//서비스에게 현재 페이지 정보를 주면서 PageMaker 객체를 가져오라고 요청
		//PageMaker객체 = 서비스야.PageMaker객체가져와(현재페이지정보);
		PageMaker pm = postService.getPageMaker(cri);
		
		//가져온 게시글 목록을 화면에 전달 
		model.addAttribute("list", list);
		model.addAttribute("bList", boardList); //길어서 줄여보기
		model.addAttribute("pm", pm);		
		return "post/list";
	}
	
	@GetMapping("/post/detail/{num}")
	public String postDetail(
		Model model,
		@PathVariable("num")int num) {
		postService.updatePostView(num);
		//번호에 맞는 게시글을 가져와서 화면에 전달
		//컨트롤러, 서비스, 다오, 매퍼
		Post post = postService.getPost(num);
		model.addAttribute("post", post);
		return "post/detail";
	}
	
	@GetMapping("post/insert")
	public String postInsert(Model model) {
		//게시판 목록을 가져옴
		ArrayList<Board> list = postService.getBoardList();
		//화면에 게시판 목록을 전달
		model.addAttribute("list", list);
		return "post/insert";
	}
	
	@PostMapping("/post/insert")
	public String postInsertPost(PostDTO post, 
			@AuthenticationPrincipal CustomUser cUser){
		//화면에서 넘겨준 제목(title), 게시판(board), 내용(content)을 받아옴
		// @RequestParam 으로 하나씩 하는 방법도 있음 
		postService.insertPost(post, cUser); //게시글 , 회원정보 줄테니 등록좀 해
		return "redirect:/post/list";
	}
	
		
}
