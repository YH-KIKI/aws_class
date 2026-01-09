package kr.hi.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hi.community.dao.CommentDAO;

@Service
public class CommentService {
	
	@Autowired
	CommentDAO commentDAO;

}
