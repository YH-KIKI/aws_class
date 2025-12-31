package kr.hi.boot.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hi.boot.dao.PostDAO;
import kr.hi.boot.model.dto.PostDTO;
import kr.hi.boot.model.util.CustomUser;
import kr.hi.boot.model.vo.Board;
import kr.hi.boot.model.vo.Member;
import kr.hi.boot.model.vo.Post;

@Service
public class PostService {
	
	@Autowired
	PostDAO postDAO;
	
	public ArrayList<Board> getBoardList(){
		return postDAO.getBoardList();
	}
	
	public void insertBoard(String name) {
		//시작문자 앞과 마지막문자 뒤의 공백을 제거
		name = name.trim();
		//공백을 제거한 문자열의 길이가 0일때=> 입력을 안하거나 공백으로만 이루어졌을때
		if(name.length() == 0) {
			return;
		}
		//게시판 추가
		try {
			//동일한 게시판을 추가하려고 할 때 예외가 발생
			postDAO.insertBoard(name);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteBoard(int num) {
		
		postDAO.deleteBoard(num);
		
	}

	public void updateBoard(int num, String name) {
		try {
			//변경할 이름을 다른 게시판이 사용중이면 예외 발생
			postDAO.updateBoard(num, name);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Post> getPostList() {
		return postDAO.getPostList();
	}

	public Post getPost(int num) {
		Post post = postDAO.getPost(num);
		return post;
	}

	public void insertPost(PostDTO post, CustomUser cUser) {
		//제목이 없으면 추가 안함
		if(post.getTitle().trim().length() == 0) {
			return;	
		}
		//내용이 없으면 추가 안함
		if(post.getContent().trim().length()== 0) {
			
		}
		//없는 게시판 번호면 추가 안함
		Board board = postDAO.getBoard(post.getBoard());
		//일치하는 게시판이 없으면 종료
		if(board == null) {
			return;
		}
		//사용자가 로그인 안되어 있으면 추가 안함
		if(cUser == null) {
			return;
		}
		
		//post에 작성자 정보 추가
		Member user = cUser.getMember();
		if(user == null) {
			return;
		}
		post.setId(user.getMe_id());
		//게시글 등록
		postDAO.insertPost(post);
		
	}
	public void updatePostView(int num) {
		postDAO.updatePostView(num);
	}

}
