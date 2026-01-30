package kr.hi.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.hi.post.dao.PostDAO;
import kr.hi.post.model.vo.PostVO;

@Service
public class PostService {
	
	private final PostDAO postDAO;
	
	public PostService(PostDAO postDAO) {
		this.postDAO = postDAO;
	}
	
	public List<PostVO> getPosts() {
		
		return postDAO.selectPosts();
	}

	public PostVO getPost(int num) {
		// 다오에게 게시글 번호 주면서 게시글 정보 가져오라고 요청
		return postDAO.selectPost(num);
	}

	public boolean insertPost(PostVO post) {
		if(post == null || 
			post.getTitle().isBlank() || 
			post.getContent().isBlank() ||
			post.getWriter().isBlank()) {
			return false;
		}
		boolean result = postDAO.insertPost(post);
		
	return result;

	}

	public boolean deletePost(int num) {
		return postDAO.deletePost(num);
	
	}


}
