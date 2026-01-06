package kr.hi.community.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.hi.community.model.dto.PostDTO;
import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.PostVO;

public interface PostDAO {
	
	//DAO는 매개변수가 있으면 @Param 붙여주면 됨

	ArrayList<PostVO> selectPostList();

	void updateView(@Param("num") int po_num);
	
	PostVO selectPost(@Param("num") int po_num);

	ArrayList<BoardVO> selectBoardList();

	void insertPost(@Param("post") PostDTO post);

	void insertBoard(@Param("name") String name);

	void deleteBoard(@Param("num") int num);

	void updateBoard(@Param("num") int num, @Param("name") String name);


}
