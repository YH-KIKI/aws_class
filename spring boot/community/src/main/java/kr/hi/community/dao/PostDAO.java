package kr.hi.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.hi.community.model.dto.LikeDTO;
import kr.hi.community.model.dto.PostDTO;
import kr.hi.community.model.util.Criteria;
import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.FileVO;
import kr.hi.community.model.vo.LikeVO;
import kr.hi.community.model.vo.PostVO;

public interface PostDAO {
	
	//DAO는 매개변수가 있으면 @Param 붙여주면 됨
	//@Param("매퍼.xml에서 사용할 이름")

	ArrayList<PostVO> selectPostList(@Param("cri") Criteria cri);

	void updateView(@Param("num") int po_num);
	
	PostVO selectPost(@Param("num") int po_num);

	ArrayList<BoardVO> selectBoardList();

	void insertPost(@Param("post") PostDTO post);

	void insertBoard(@Param("name") String name);

	void deleteBoard(@Param("num") int num);

	void updateBoard(@Param("num") int num, @Param("name") String name);

	int selectTotalCount(@Param("cri") Criteria cri);

	void deletePost(@Param("num") int postNum);
	
	void updatePost(@Param("post") PostDTO post);

	void insertFile(@Param("file") FileVO fileVo);

	List<FileVO> selectFileList(@Param("num") int po_num);

	void deleteFile(@Param("fiNum") int fi_num);

	FileVO selectFile(@Param("fiNum") int num);

	LikeVO selectLike(@Param("like") LikeDTO like);

	void insertLike(@Param("like") LikeDTO like);

	void updateLike(@Param("like") LikeDTO like);

	void deleteLike(@Param("like") LikeDTO like);

	int selectLikeCount(@Param("postNum") int postNum, @Param("state") int state);

	void updateBoardLike(@Param("postNum") int postNum);



}
