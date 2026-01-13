package kr.hi.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hi.community.dao.CommentDAO;
import kr.hi.community.model.dto.CommentDTO;
import kr.hi.community.model.util.Criteria;
import kr.hi.community.model.util.CustomUser;
import kr.hi.community.model.util.PageMaker;
import kr.hi.community.model.vo.CommentVO;

@Service
public class CommentService {
	
	@Autowired
	CommentDAO commentDAO;

	public String insertComment(CommentDTO dto, CustomUser customUser) {
		//매개변수 체크
		//로그인 여부 체크
		if(customUser == null || customUser.getUsername() == null) {
			return "로그인이 필요한 서비스입니다.";
		}
		//댓글 정보 체크
		if(dto == null || dto.getContent().trim().isEmpty() || 
		   dto.getPostNum() == 0 ) {
		   return "댓글 정보가 없습니다.";
		}
		
		//댓글 등록
		try {
			//댓글 작성자를 로그인한 사용자 아이디로 수정
			dto.setId(customUser.getUsername());
			commentDAO.insertComment(dto);
			return "댓글을 등록했습니다.";
			
		}catch(Exception e) {
			e.printStackTrace();
			return "댓글을 등록하지 못했습니다.";
		}
	}

	public List<CommentVO> getCommentList(Criteria cri) {
		List<CommentVO> list = commentDAO.selectCommentList(cri);
		return list;
	}

	public PageMaker getPageMarker(Criteria cri) {
		//다오에게 페이지 정보 주면서 전체 댓글 수를 가져오라고 요청
		int totalcount = commentDAO.selectCommentCount(cri);
		return new PageMaker(3, cri, totalcount);	//(페이지네이션 바에 보여줄 페이지 번호 개수, 현재페이지 조건 , 전체게시글 개수)
	}
	
	public String deleteComment(int coNum, CustomUser user) {
		//- 로그인한 사용자가 아니면 로그인이 필요한 서비스입니다라고 반환
		if(user == null || user.getUsername() == null) {
			return "로그인이 필요한 서비스입니다.";
		}
		//- 작성자인지 확인
		if(!isWriter(coNum, user)) {
			return "작성자가 아닙니다.";
		}
		
		//- 다오에게 댓글 번호를 주면서 삭제하고 삭제 여부를 가져오라고 요청
		//삭제여부 = 다오야.댓글삭제해(댓글번호);
		boolean result = commentDAO.deleteComment(coNum);
		
		//- 댓글을 삭제했으면 댓글을 삭제했습니다를 반환
		if(result) {
			return "댓글을 삭제했습니다";
		}
		//- 삭제하지 못했으면 댓글을 삭제할 수 없습니다를 반환
		return "댓글을 삭제할 수 없습니다.";
	}

	public String updateComment(CommentDTO dto, CustomUser user) {
		// 로그인 안했으면 '로그인 필요한 서비스입니다' 라고 반환
		if(user == null || user.getUsername() == null) {
			return "로그인이 필요한 서비스입니다.";
		}
		// 작성자 확인해서 아니면 작성자가 아닙니다를 반환
		if(!isWriter(dto.getCoNum(), user)) {
			return "작성자가 아닙니다.";
		}
		// 다오에게 수정댓글 정보를 주면서 수정하고 수정여부를 가져오라고 요청
		// 수정여부 = 다오야.댓글수정해(수정댓글정보);
		boolean result = commentDAO.updateComment(dto);
		// 수정했으면 '수정을 했습니다' 라고 반환
		if(result) {
			return "댓글을 수정했습니다.";
		}
		// 수면 못했으면 '수정을 하지 못했습니다' 라고 반환
		return "댓글을 수정하지 못했습니다.";
	}
	//coNum 댓글의 작성자가 user가 맞는지 알려주는 메서드
	private boolean isWriter (int coNum, CustomUser user) {
		if(user == null || user.getUsername() == null) {
			return false;
		}
		// 다오에게 댓글 번호를 부면서 댓글 정보를 가져오라고 요청
		//댓글정보 = 다오야.댓글정보가져와(댓글번호);
		CommentVO comment = commentDAO.selectComment(coNum);
		
		// 댓글이 없거나 댓글작성자와 로그인한 사용자 아이디가 다르면 
		// '작성자가 아닙니다' 라고 반환
		if(comment == null || 
			!comment.getCo_me_id().equals(user.getUsername())) {
			return false;
		}
		return true;
	}

}
