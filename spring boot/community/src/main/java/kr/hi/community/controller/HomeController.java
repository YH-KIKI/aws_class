package kr.hi.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	/* Get : URL을 직접 입력하거나, 링크를 클릭해서 이동했을 때 대부분 Get. // URL에 정보가 노출이 됨 
	 * Post : 값을 입력하여 중요한 정보를 전송할때 (form태그 + method = post) // 비밀번호처럼 노출 되면 안되는건 post
	 * 
	 * */
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

}
