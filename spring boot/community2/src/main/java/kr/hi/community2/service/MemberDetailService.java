package kr.hi.community2.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.hi.community2.dao.UserDAO;
import kr.hi.community2.model.vo.UserVO;
import kr.hi.community2.util.CustomUser;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberDetailService implements UserDetailsService{

	private final UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO user = userDAO.selectUser(username);

		return user == null ? null : new CustomUser(user);
	}

}