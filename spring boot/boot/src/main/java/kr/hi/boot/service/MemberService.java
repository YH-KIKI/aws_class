package kr.hi.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.hi.boot.dao.MemberDAO;
import kr.hi.boot.model.dto.SignupDTO;
import kr.hi.boot.model.vo.Member;

/* - 규모가 크거나 구조가 자주 바뀌는 경우
 * 	 service 인터페이스와 구현 클래스로 나누어서 작업
 * - 소규모 프로젝트이거나 구조가 자주 안 바뀌는 경우
 *   굳이 나누지 말고 service 클래스를 이용해서 작업
 */
@Service
public class MemberService {
	@Autowired
	MemberDAO memberDAO;
	
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    public boolean signup(SignupDTO signupDto) {
		//아이디, 비번, 이메일 정규표현식 검사
		
		//아이디를 이용하여 회원 정보를 가져옴
		Member member = memberDAO.getMember(signupDto.getId());
		//회원 정보가 있으면 -> 가입 X -> false
		if(member != null) {
			return false;
		}
		//암호화
		String encodedPw = encoder.encode(signupDto.getPw());
		//암호화된 비번으로 수정
		signupDto.setPw(encodedPw);
		
		//없으면 -> 가입O -> 회원가입 진행 후 true 반환
		memberDAO.insertMember(signupDto);
		return true;

	}
	    
}