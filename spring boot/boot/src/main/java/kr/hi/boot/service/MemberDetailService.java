package kr.hi.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.hi.boot.dao.MemberDAO;
import kr.hi.boot.model.util.CustomUser;
import kr.hi.boot.model.vo.Member;

@Service
public class MemberDetailService implements UserDetailsService{

	@Autowired
	MemberDAO memberDao;
	
	@Override
	/* 화면에서 아이디, 비번을 입력 후 로그인을 클릭하면
	 * MemberDetailService로 옴(왜? SecurityConfig에서
	 * .loginProcessingUrl("/login")을 등록해서/login post일 때 
	 * UserDetailService 인터페이스를 구현한 구현 클래스를 자동(@service)로 선택해서 호출 함
	 * */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 아이디를 이용하여 회원 정보를 가져옴
		Member member = memberDao.getMember(username);
		// 회원 정보가 없으면 null을 반환하고, 있으면 CustomUser로 변환하여 반환
		// 왜냐하면 User클래스를 상속받은 클래스로 반환해야 이후에 자동으로 로그인이 진행됨
		return member == null ? null : new CustomUser(member);
	}

}