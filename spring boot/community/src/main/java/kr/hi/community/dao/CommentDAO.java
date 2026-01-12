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

}
