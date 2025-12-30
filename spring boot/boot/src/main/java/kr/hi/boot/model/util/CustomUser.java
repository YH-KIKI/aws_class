package kr.hi.boot.model.util;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import kr.hi.boot.model.vo.Member;
import lombok.Data;

@Data
public class CustomUser extends User {
	
	private Member member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	public CustomUser(Member vo) {
		/* User의 생성자 중 아이디, 비번, 권한들이 필요한 생성자가 있음
		 * 사용자의 권한을 여러 종류료 관리할 때 권한들이 여러 권한을 넣어줌
		 * 
		 * 우리가 구현한 사이트의 회원은 USER나 ADMIN 중 하나를 가짐
		 * 구현 방법에 따라 관리자는 USER, ADMIN 권한을 가지고
		 * 사용자는 USER만 가지도록 설계할 수도 있음.
		 * 그래서 권한들에 해당 사용자의 권한들을 넣어주어 한 사용자의 여러 권한들을 처리할 수 있게 함
		 */
		super(	vo.getMe_id(),
				vo.getMe_pw(), 
				//Arrays.asList(값1, 값2, ...)을 하면 값들을 배열로 만들어서 반환
				Arrays.asList(new SimpleGrantedAuthority(vo.getMe_role())));
		this.member = vo;
	}
}