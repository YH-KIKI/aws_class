package kr.hi.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.hi.boot.model.dto.CommentResponseDTO;
import kr.hi.boot.model.util.Criteria;
import kr.hi.boot.model.vo.Comment;

public interface CommentDAO {

	List<Comment> selectComments(@Param("poNum") int poNum, @Param("cri") Criteria cri);

	int selectCommentsCount(@Param("poNum") int poNum);

	boolean insertComments(@Param("comment") Comment comment);

	Comment selectComment(@Param("coNum") int coNum);

	boolean deleteComments(@Param("coNum") int coNum);

	boolean updateComment(@Param("coNum") int coNum, @Param("comment") Comment comment);


}
