package kr.hi.community.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.service.PostService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    PostService postService;  // 필드 주입

    @GetMapping("/board/list")
    public String boardList(Model model) {
    	//서비스에게 게시판 목록을 가져오라고 요청
    	ArrayList<BoardVO> list = postService.getBoardList(); 
    	//화면에 전송
    	model.addAttribute("list", list);
        return "board/list";
    }
    
    @PostMapping("/board/insert")
    public String boardInsert(@RequestParam("board") String name) {
    	//서비스에게 name을 주면서 게시판을 등록하라고 요청
    	postService.insertBoard(name);
    	return "redirect:/admin/board/list";  
    	// URL 바꿀땐 redirect
    	//RequestMapping이 들어올땐 자동체크되지만 나갈땐 안되므로 admin써야함
    }
    
    @PostMapping("/board/delete/{num}")
    public String boardDelete(@PathVariable("num") int num) {
    	//서비스에게 게시판 번호 주면서 삭제하라고 일시키기
    	postService.deleteBoard(num);
    	return "redirect:/admin/board/list"; 
    }
    
    
    @PostMapping("/board/update/{num}")
    public String boardUpdate(
    		@PathVariable("num") int num,
    		@RequestParam("name") String name) {
    	//서비스에게 게시판 번호,이름을 주면서 수정하라고 일시키기
    	postService.updateBoard(num, name);
    	return "redirect:/admin/board/list"; 
    }
    
    
}