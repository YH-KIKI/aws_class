package kr.hi.community2.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.hi.community2.dao.UserDAO;
import kr.hi.community2.model.dto.LoginDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private final UserDAO userDAO;
	
	private final BCryptPasswordEncoder encoder;

	public boolean signup(LoginDTO user) {
		//아이디 유효성 체크
		
		//비번 유효성 체크
		
		//메일 유효성 체크
		try {
			String encodedPw = encoder.encode(user.pw());
			LoginDTO nUser = new LoginDTO(user.id(), encodedPw, user.email());
			return userDAO.insertUser(nUser);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
