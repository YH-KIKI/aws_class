package kr.hi.community.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hi.community.dao.CommentDAO;
import kr.hi.community.model.dto.CommentDTO;
import kr.hi.community.model.util.CommentCriteria;
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

}
