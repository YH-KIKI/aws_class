package kr.hi.boot.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.hi.boot.model.dto.Human;
import kr.hi.boot.model.dto.SignupDTO;
import kr.hi.boot.service.MemberService;
import lombok.extern.log4j.Log4j2;

/* URL 확인할 때 컨트롤러 안에 있는 URL들을 확인
 *  -> 여기에 없으면 없는 URL
 *  -> 404에러 발생
 */
@Controller
@Log4j2
public class MainController {
	
	@Autowired
	MemberService memberService;
	
	
	/*주어진 URL이 get 방식일 때 처리*/
	@GetMapping("/")
	/* 리턴 타입
	 * - String 또는 void
	 * - void이면 URL이 리턴됨
	 * - 리턴 값을 이용하여 연결할 화면을 찾음*/
	public String main(Model model,
		@RequestParam(name = "role", defaultValue="GUEST") String role) {
		log.info("메인페이지 : " + role);
		
		//th:each를 활용할 샘플 데이터
		ArrayList<Human> list = new ArrayList<Human>();
		list.add(new Human("홍길동", 20));
		list.add(new Human("둘리", 25));
		list.add(new Human("고길동", 40));
		
		model.addAttribute("화면에서 사용할 이름", "데이터");
		model.addAttribute("data", "홍길동");	//index에서 "$(data)" 이름과 맞추기 위해 data
		model.addAttribute("role", role);
		model.addAttribute("list", list);
		model.addAttribute("date", new Date());	//java.util.Date
		return "index";
	}
	/* 사용자가 요청한 URL이 /abc이고, get방식이면 동작
	 * abc라는 문자열을 리턴(반환)해서 abc.html을 연결
	 * -> abc.html이 있으면 화면이 출력
	 * -> 없으면 500에러 페이지 -> 화면이 업어서
	 */
	
	@GetMapping("/abc")
	public String abc(
			//화면에서 "name"으로 데이터를 보낸 데이터를 가져와서 저장
			@RequestParam(name = "name", required = false) String name1,	// name1로 한이유는 Param("")과 이름이 안맞아도 상관없다는걸 보여주려고 
			@RequestParam(name = "age", defaultValue = "0") int age1) {
		log.info("abc입니다.");
		log.info(name1);
		log.info(age1);		
		return "abc";
	}
	@PostMapping("/abc")
	// 화면에서 보낸 정보를 클래스의 객체로 받을 때는 
	// 클래스의 필드 이름과 화면에서 보낸 이름(name)이 같아야 자동으로 맵핑되어 저장됨.
	public String abcPost(Human human) {
		log.info("abc:post입니다.");
		log.info(human);
		return "abc";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}
	
	@PostMapping("/signup")
	public String signupPost(
			/* SignupDTO의 기본 생성자를호출해서 객체를 생성 후,
			 * 화면에서 보낸 name과 이름이 같은 필드들의 setter를 호출해서 값을 변경
			 */
			SignupDTO signupDto) {
		boolean res = memberService.signup(signupDto);
		return "user/signup";
	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	


}
