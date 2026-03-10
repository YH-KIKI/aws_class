package kr.hi.community.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.hi.community.model.util.CommentCriteria;
import kr.hi.community.model.vo.CommentVO;

public interface TestDAO {

	List<CommentVO> selectCommentList(@Param("poNum") int poNum, @Param("cri") CommentCriteria cri);

	int selectCommentCount(@Param("poNum") int poNum);

}
