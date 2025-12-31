package kr.hi.boot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.hi.boot.model.vo.Board;
import kr.hi.boot.service.PostService;

@Controller
//URL이 /admin으로 시작하면 AdminController에서 작업 되도록
@RequestMapping("/admin")
public class AdminController {
	
	//게시판, 게시글을 관리하는 서비스
	@Autowired
	PostService postService;
	
	@GetMapping("/board")
	public String board(Model model) {
		//게시글 전체를 가져옴
		ArrayList<Board> list = postService.getBoardList();
		//화면에 게시글 전체를 보내줌 		
		model.addAttribute("list", list);
		return"admin/board";
	}
	
	@PostMapping("/board/insert")
	public String boardInsert(
		@RequestParam("name")String name) {
		//게시판 등록 작업
		postService.insertBoard(name);
		//화면을 연결하는 것이 아니라 url을 /admin/board로 바꾸어서 게시판
		//목록으로 이동시킴
		return "redirect:/admin/board";
	}
	
	@PostMapping("/board/delete/{num}")
	public String boardDelete(
		@PathVariable("num")int num1) {
		postService.deleteBoard(num1);
		return "redirect:/admin/board";
	}
	
	@PostMapping("/board/update/{num}")
	public String boardUpdate(
		@PathVariable("num")int num,
		@RequestParam("name")String name) {
		
		postService.updateBoard(num, name);
		return "redirect:/admin/board";
	}


}
