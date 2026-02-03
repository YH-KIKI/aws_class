package kr.hi.community2.dao;

import kr.hi.community2.model.dto.LoginDTO;
import kr.hi.community2.model.vo.UserVO;

public interface UserDAO {

	boolean insertUser(LoginDTO user);

	UserVO selectUser(String id);


}
