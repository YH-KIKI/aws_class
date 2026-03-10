package kr.hi.community.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hi.community.dao.TestDAO;
import kr.hi.community.model.util.CommentCriteria;
import kr.hi.community.model.util.PageMaker;
import kr.hi.community.model.vo.CommentVO;

@Service
public class TestService {
	
	@Autowired
	TestDAO testDAO;

	 public Map<String, Object> getComments(int poNum, CommentCriteria cri) {
		 
		 Map<String, Object> result = new HashMap<>();
		 
		 List<CommentVO> list = testDAO.selectCommentList(poNum, cri);
		 int totalCount = testDAO.selectCommentCount(poNum);
		 PageMaker pm = new PageMaker(cri, totalCount);
		 
		 result.put("list", list);
	     result.put("pm", pm);
		
	     return result;
	 }
}

