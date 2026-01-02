package kr.hi.community.model.util;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.hi.community.model.vo.MemberVO;
import lombok.Data;

@Data
public class CustomUser extends User {
	//필드에 user를 추가해서 로그인했을 때 록읜한 회원 정보를 가져와서 활용하기 위해
	//User를 상속받아 새로 만듬
	private MemberVO user;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	public CustomUser(MemberVO vo) {
		super(	vo.getMe_id(),
				vo.getMe_pw(), 
				Arrays.asList(new SimpleGrantedAuthority(vo.getMe_role()))); //role로 변경
		this.user = vo;
	}
}
