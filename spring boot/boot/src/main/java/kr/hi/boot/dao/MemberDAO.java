package kr.hi.boot.dao;

import org.apache.ibatis.annotations.Param;

import kr.hi.boot.model.dto.SignupDTO;
import kr.hi.boot.model.vo.Member;

public interface MemberDAO {
	
	//@Param("mapper에서 사용할 이름")
	Member getMember(@Param("id") String id);

	void insertMember(@Param("dto") SignupDTO signupDto);

}
