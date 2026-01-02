package kr.hi.community.dao;

import org.apache.ibatis.annotations.Param;

import kr.hi.community.model.dto.MemberDTO;
import kr.hi.community.model.vo.MemberVO;

public interface MemberDAO {

	MemberVO selectMember(@Param("id") String id);

	boolean insertMember(@Param("obj") MemberDTO member); //@Param("매퍼에서사용할이름")
}
