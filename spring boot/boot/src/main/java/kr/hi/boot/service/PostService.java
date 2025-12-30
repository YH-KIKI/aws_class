package kr.hi.boot.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hi.boot.dao.PostDAO;
import kr.hi.boot.model.vo.Board;

@Service
public class PostService {
	
	@Autowired
	PostDAO postDAO;
	
	public ArrayList<Board> getBoardList(){
		return postDAO.getBoardList();
	}

}
