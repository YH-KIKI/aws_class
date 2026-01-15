package kr.hi.boot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.hi.boot.model.vo.Comment;

public interface CommentDAO {

	List<Comment> selectComments(@Param("poNum") int poNum);

}
