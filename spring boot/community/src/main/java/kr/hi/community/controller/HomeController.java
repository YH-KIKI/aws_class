package kr.hi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.hi.community.model.dto.MemberDTO;
import kr.hi.community.service.MemberService;

@Controller
public class HomeController {
	
	/* Get : URL을 직접 입력하거나, 링크를 클릭해서 이동했을 때 대부분 Get. // URL에 정보가 노출이 됨 
	 * Post : 값을 입력하여 중요한 정보를 전송할때 (form태그 + method = post) // 비밀번호처럼 노출 되면 안되는건 post
	 * 
	 * */
	
	//객체를 생성해서 연결. 단, 빈(bean)에 등록된 클래스/인터페이스만
	//@Bean, @Service, @Repository 등
	@Autowired
	MemberService memberService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
		
	}
	
	@PostMapping("/signup")
	//get방식 메서드와 겹치지않게 Post붙여서 처리
	public String signupPost( /* 화면이 보낸 회원 정보를 받아옴 */ // @리퀘스트파람("id정보")
		MemberDTO member){
		
		boolean result = memberService.signup(member);
		//회원 가입이 성공하면 메인페이지로
		if(result) {
			return "redirect:/";
		}
		else {
			return "redirect:signup";
		}
		
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
 }
