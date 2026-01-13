package kr.hi.community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.hi.community.model.dto.CommentDTO;
import kr.hi.community.model.util.Criteria;
import kr.hi.community.model.vo.CommentVO;

public interface CommentDAO {

	void insertComment(@Param("coDto") CommentDTO dto);
	
	List<CommentVO> selectCommentList(@Param("cri") Criteria cri);

	int selectCommentCount(@Param("cri") Criteria cri);
	
	CommentVO selectComment(@Param("coNum")int coNum);

	/* update,insert,delete는 반환값이 boolean 또는 int로 설정할 수 있는데,
	 * 설정하면 
	 * boolean일 때 해당 쿼리가 적용된 행이 있으면 true, 없으면 false를 반환하고,
	 * int일 때 해당 쿼리가 적용된 행의 개수를 반환
	 * */ 
	boolean deleteComment(@Param("coNum")int coNum);

	boolean updateComment(@Param("dto") CommentDTO dto);

}
