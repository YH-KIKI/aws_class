package kr.hi.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.hi.boot.dao.CommentDAO;
import kr.hi.boot.model.vo.Comment;

@Service
public class CommentService {
	
	private final CommentDAO commentDAO;
	
	public CommentService(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}


	public List<Comment> getComments(int poNum) {
		// 다오에게 게시글 번호를 주면서 댓글 목록을 가져오라고 요청
		List<Comment> list = commentDAO.selectComments(poNum);
		return list; //가져온 댓글 목록을 반환
	}


}

