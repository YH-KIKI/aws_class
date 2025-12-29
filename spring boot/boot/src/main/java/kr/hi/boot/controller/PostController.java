package kr.hi.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
	
	@GetMapping("post/list")
	public String postList() {
		
		return"post/list";
	}
		

}
