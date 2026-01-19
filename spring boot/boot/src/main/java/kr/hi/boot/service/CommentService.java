package kr.hi.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.hi.boot.dao.CommentDAO;
import kr.hi.boot.model.util.Criteria;
import kr.hi.boot.model.util.CustomUser;
import kr.hi.boot.model.util.PageMaker;
import kr.hi.boot.model.vo.Comment;

@Service
public class CommentService {
	
	private final CommentDAO commentDAO;
	
	public CommentService(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}


	public List<Comment> getComments(int poNum, Criteria cri) {
		// 다오에게 게시글 번호와 페이지 정보를 주면서 댓글 목록을 가져오라고 요청
		List<Comment> list = commentDAO.selectComments(poNum, cri);
		return list; //가져온 댓글 목록을 반환
	}


	public PageMaker getPageMaker(int poNum, Criteria cri) {
		//한 페이지네이션에서 최대 페이지수를 3개로 선언
		int pageCount = 3;
		
		//다오에게 게시글 번호 주면서 전체 댓글 수를 가져오라고 요청
		int count = commentDAO.selectCommentsCount(poNum); 
		
		//최대 페이지수, 전체 게시글수, 현재 페이지 정보를 이용하여 PageMaker 객체를 생성
		PageMaker pm = new PageMaker (pageCount, cri, count);
		
		//생성된 객체를 반환 
		return pm;
	}


	public String insertComments(Comment comment, CustomUser user) {
		// 사용자가 로그인이 되어 있지 않으면 "로그인이 필요한 서비스입니다." 라고 반환 
		if(user == null) {
			return "로그인이 필요한 서비스입니다.";
		}
		// 댓글 내용이 비었으면 내용을 입력하세요 라고 반환 
		if(comment.getContent() == null || comment.getContent().isBlank()) {
			return "댓글을 입력하세요.";	
		}
		
		// 다오에게 게시글 번호, 댓글 내용을 주면서 게시글에 등록하라고 요청 후 등록 여부를 알려달라고 요청
		comment.setId(user.getUsername());
		
		//등록결과 = 다오야.게시글 등록해(댓글정보)
		boolean result = commentDAO.insertComments(comment);
		// 등록 했으면 "댓글을 등록했습니다" 라고 반환하고
		if(result) {
			return "댓글을 등록했습니다.";
		}
		// 못했으면 "댓글을 등록하지 못했습니다" 라고 반환
		return "댓글을 등록하지 못했습니다.";
	}


	public String deleteComments(int coNum, CustomUser user) {
		// 사용자가 로그인이 되어 있지 않으면 "로그인이 필요한 서비스입니다." 라고 반환 
		if(user == null) {
			return "로그인이 필요한 서비스입니다.";
		}

		
		//댓글 작성자가 아니면 "작성자가 아닙니다" 반환
		String id = user.getUsername(); //id가 없으니까 변수선언
		if(!isWriter(coNum, id)) { 		//isWriter = 아래 메서드 이름
			return "작성자가 아닙니다.";
		}
		
		// 다오에게 댓글 번호를 주면서 삭제하고 결과를 알려달라고 요청
		boolean res = commentDAO.deleteComments(coNum);
		// 삭제 했으면 "댓글을 삭제했습니다"를 반환
			if(res) {
				return "댓글을 삭제했습니다.";
			}
			// 삭제 못했으면 "댓글을 삭제하지 못했습니다"를 반환
			return "댓글을 삭제하지 못했습니다.";
		}
	
	private boolean isWriter(int coNum, String id) {
		// 다오에게 댓글번호 주면서 댓글 정보 가져오라고 요청 
		//String으로 정의하면 하나만 가져올수있어써 VO(Comment)를 불러옴
		Comment comment = commentDAO.selectComment(coNum);
		
		// 가져온 댓글 정보가 없으면 false를 반환 
		if(comment == null) {
			return false;
		}
		String writer = comment.getId();
		// 작성자와 아이디가 같으면 true, 다르면 false를 반환 
		return writer.equals(id);
		
	}


	public String updateComment(int coNum, Comment comment, CustomUser user) {
		//내용이 비었는지 체크하여 비었으면 '댓글을 입력하세요'를 반환
		if(comment == null || comment.getContent().isBlank()) {
			return "댓글을 입력하세요";
		}
		//로그인 안했으면 '로그인이 필요한 서비스입니다'를 반환
		if(user == null) {
			return "로그인이 필요한 서비스입니다.";
		}
		
		//작성자가 아니면 '작성자가 아닙니다'를 반환
		//String id = user.getUsername(); -> 변수선언해서 아래 user.getUsername()대신 id넣어도 ok
		if(!isWriter(coNum, user.getUsername())){ 		//isWriter = 위에 메서드 호출
			return "작성자가 아닙니다.";
		}

		//다오에게 댓글번호와 내용을 주면서 수정하라고 요청하고 결과를 가져옴
		boolean result = commentDAO.updateComment(coNum, comment);
			if(result) {
				return "댓글을 수정했습니다.";
			}
		return "댓글을 수정하지 못했습니다";

	}


}

