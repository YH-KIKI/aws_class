package kr.hi.community.dao;

import org.apache.ibatis.annotations.Param;

public interface MemberDAO {

	String selectMember(@Param("id") String id);

}
