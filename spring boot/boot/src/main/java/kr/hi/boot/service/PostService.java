package kr.hi.boot.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hi.boot.dao.PostDAO;
import kr.hi.boot.model.dto.PostDTO;
import kr.hi.boot.model.util.Criteria;
import kr.hi.boot.model.util.CustomUser;
import kr.hi.boot.model.util.PageMaker;
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

	public ArrayList<Post> getPostList(Criteria cri) {
		return postDAO.getPostList(cri);
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


	public PageMaker getPageMaker(Criteria cri) {
		
		//한 페이지네이션의 최대 페이지수는 3개로 설정
		int displayPageNum = 3;	//아래 PageMAker만들때 걍 3써도 됨
		
		//다오에게 현재 페이지 정보를 주면서 페이지 정보와 일치하는 게시글 전체를 가져오라고 요청
		// 게시글 수 = 다오야.게시글전체수를가져와(현재페이지정보)
		int totalCount = postDAO.selectPostListCount(cri);
		
		//최대 페이지수, 총 게시글 수, 현재 페이지 정보를 이용하여 PageMaker클래스 객체를 생성 후 반환 
		PageMaker pm = new PageMaker(displayPageNum, cri, totalCount);
		
		return pm;
	}

	public void postDelete(int poNum, CustomUser user) {
		// 사용자 정보가 로그인이 안되어 있으면 종료
		if (user == null || user.getUsername() == null) {
			return;
		}
		// 게시글 번호를 이용하여 게시글을 가져옴 (구현되어있음?)
		Post post = postDAO.getPost(poNum);
		
		// 게시글이 없으면 종료
		if(post == null) {
			return;
		}
		// 게시글 작성자와 사용자 아이디를 비교하여 다르면 종료
		//게시글 작성자 아이디
		String writer = post.getPo_me_id();
		
		//사용자 아이디
		String id = user.getUsername();
		
		//(!게시글작성자아이디.equals(사용자아이디))
		//if(!post.getPo_me_id.equals(user.getUsername)을 따로 정리해서 아래코드처럼 표현 
		if(!writer.equals(id)) {
			return;
		}
		
		//다오에게 게시글 번호를 주면서 삭제하라고 요청
		postDAO.postDelete(poNum);
		
		
	}

	public void postUpdatepost(PostDTO dto, int poNum, CustomUser user) {
		//제목, 내용이 비었는지 확인해서 비었으면 종료
		if(dto == null || 
		   dto.getTitle().isBlank() ||
		   dto.getContent().isBlank()) {
			return;
		}
		//사용자가 로그인 안되어 있으면 종료
		if(user == null || user.getUsername() == null) {
			return;
		}
		//다오에게 게시글 번호 주면서 게시글 가져오라고 요청
		Post post = postDAO.getPost(poNum);
		
		// 게시글이 없으면 종료
		if(post == null) {
			return;
		}
		//게시글의 작성자와 사용자 아이디가 다르면 종료 (= if(!writer.equals(id)))
		if(!post.getPo_me_id().equals(user.getUsername())) {
			return;
		}
		
		// 다오에게 게시글 번호, 제목, 내용을 주면서 수정하라고 요청
		postDAO.postUpdatepost(poNum, dto);
		
		
		
	}



}
