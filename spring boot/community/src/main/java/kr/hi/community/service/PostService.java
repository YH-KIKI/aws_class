package kr.hi.community.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.hi.community.dao.PostDAO;
import kr.hi.community.model.dto.LikeDTO;
import kr.hi.community.model.dto.PostDTO;
import kr.hi.community.model.util.Criteria;
import kr.hi.community.model.util.CustomUser;
import kr.hi.community.model.util.UploadFileUtils;
import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.FileVO;
import kr.hi.community.model.vo.LikeVO;
import kr.hi.community.model.vo.PostVO;

@Service
public class PostService {
	
	@Autowired
	PostDAO postDAO;
	
	@Value("${file.upload-dir}")
	String uploadPath;

	
	public ArrayList<PostVO> getPostList(Criteria cri){ //리스트 가져와서 리스트 반환
		//다오에게 게시판 번호에 맞는 게시글 목록을 가져오라고 요청
		ArrayList<PostVO> list = postDAO.selectPostList(cri);
		//게시글 목록을 반환
		return list;
	}

	public void updateView(int po_num) {
		postDAO.updateView(po_num);	//다오에게 일을 시켜줘!
		
	}

	public PostVO getPost(int po_num) {
		PostVO post = postDAO.selectPost(po_num);
		return post;
	}
	
	public ArrayList<BoardVO> getBoardList(){
		//다오에게 게시판 목록을 가져오라고 요청
		ArrayList<BoardVO> list = postDAO.selectBoardList();
		//게시판 목록을 반환
		return list;
	}
	
	//메서드 생성해서 null / 공백 체크해서 활용하는 방법
	private boolean checkEmpty(String str) {
		//null이거나
		if(str == null) {
			return true;	
		}
		//공백으로 이루어져 있으면 false를리턴
		if(str.trim().isEmpty()) {
			return true;
		}
		//공백이 아닌 한글자라도 있는 경우 false를 리턴
		return false;
	}

	public boolean insertPost(PostDTO post, CustomUser customUser, List<MultipartFile> files) {
		//게시글 정보 확인 -> 입력 안된 값 있는지 확인해서 잘못된게 있으면 false를 반환
		if(checkEmpty(post.getTitle()) || 
		   checkEmpty(post.getContent()) || 
		   post.getBoard() == 0) {
		   return false;
		}
		
		//사용자 정보를 확인 -> 로그인 됐는지 확인해서 안했으면 false를 반환
		if(customUser == null || customUser.getUser() == null) {
			return false;
		}
		//게시글 작성자를 로그인한 회원의 아이디로 수정
		post.setWriter(customUser.getUsername());
		
		//다오에게 게시글 정보를 주면서 등록하라고 시킴
		try {
			postDAO.insertPost(post);
			
		}catch(Exception e) {
			//잘못된 게시판 번호를 입력한 경우 게시글 등록에 실패
			e.printStackTrace();
			return false;
		}
		
		// 게시글 등록 후 첨부파일 추가
		// 첨부파일 목록이 없는 경우
		if(files == null || files.isEmpty()) {
			return true;
		}
		for(MultipartFile file : files) {
			//DB에 추가하고 서버에 첨부파일 업로드해
			insertFile(post.getPostNum(), file);
			
	}	
	return true;
}

	private void insertFile(int postNum, MultipartFile file) {
		try {
			String fileName = UploadFileUtils.uploadFile(uploadPath, file);
			String oriFileName = file.getOriginalFilename();
			
			FileVO fileVo = new FileVO(postNum, oriFileName, fileName); // "업로드전파일명", "업로드후파일명"
			//DB에 업로드한 파일 정보를 추가
			postDAO.insertFile(fileVo);
					
		}catch(Exception e) {
			System.err.println(e.getMessage()); // 업로드 파일 없다고 1줄로 
		} 	
		
	}

	public void insertBoard(String name) {
		if(checkEmpty(name)) {
			return;
		}
		//예외 처리 -> 게시판명이 중복되면 예외 발생 하기 때문에 
		try {
			postDAO.insertBoard(name);
		}catch(Exception e) {
			//잘못된 게시판 번호를 입력한 경우 게시글 등록에 실패
			e.printStackTrace();
		}
	}

	public void deleteBoard(int num) {	
		try {
			postDAO.deleteBoard(num);
		}catch(Exception e) {
			//게시글이 있는 게시판을 삭제하려고 하면 예외가 발생
			// -> 외래키 옵션에서 게시판 번호를 참조하는 게시글이 있는 경우
			//	  해당 게시판을 삭제 하지 못하도록 (restrict)로 설정되어있기 때문
			e.printStackTrace();
		}

	}

	public void updateBoard(int num, String name) {
		if(checkEmpty(name)) {
			return; //이름이 비어있으면 종료해줘요
		}
		try {
			postDAO.updateBoard(num, name);
		}catch(Exception e) {
			//수정하려는 게시판 명이 중복되면 예외 발생
			e.printStackTrace();
		}
	
	}

	public int getTotalCount(Criteria cri) {
		if(cri == null) {
		return 0;
		}
		return postDAO.selectTotalCount(cri); //페이지 정보 줄테니까 일치하는 페이지 개수 가져와
	}

	
	public void deletePost(int postNum, CustomUser cUser) {
		//로그인이 안된 경우 종료
		if (cUser == null || cUser.getUsername() == null) {
			return;
		}
		//작성자 정보를 가져오기 위해 게시글 정보를 가져옴 
		PostVO post = postDAO.selectPost(postNum);
		
		//작성자가 다르면
		if (post == null || !post.getPo_me_id().equals(cUser.getUsername())) {
			return;
		}
		
		//게시글 번호를 이용하여 첨부파일 목록을 가져옴
		List<FileVO> files = postDAO.selectFileList(postNum);
		
		for (FileVO file : files) {
			deleteFile(file);
		}
		//게시글 삭제(실제 삭제하는거 아니고 po_del을 Y로 처리)
		postDAO.deletePost(postNum);
		
	}
		
		
	private void deleteFile(FileVO file) {
		if(file == null) {
			return;
		}
			
		// 첨부파일 삭제 
		// 1. 실제 첩부파일을 삭제
		UploadFileUtils.deleteFile(uploadPath, file.getFi_name());
		// 2. DB에 있는 첨부파일 정보를 삭제
		// 다오에게 첨부파일 번호를 주면서 첨부파일을 삭제하라고 요청
		// 다오.첨부파일 삭제해줘 (첨부파일번호를 주면서)
		postDAO.deleteFile(file.getFi_num());	
	}
	

	public void updatePost(PostDTO post, CustomUser customUser, List<MultipartFile> files, List<Integer> delFileNums) {
		//- 사용자가 로그인 안햇으면 종료
		if(customUser == null || customUser.getUsername().isEmpty()) {
			return;
		}
		//- 게시글 정보가 없거나, 게시글 제목 또는 내용이 비어있으면 종료
		if(post == null || 
			checkEmpty(post.getTitle()) ||
			checkEmpty(post.getContent())) {
			return;
		}
		
		//- 사용자와 작성자가 같은지 확인해서 다르면 종료
		//   - 다오에게 게시글 번호를 주면서 게시글을 가져오라고 요청
		PostVO dbPost = postDAO.selectPost(post.getPostNum());
		//   - 게시글 없거나 게시글 작성자가 사용자와 다르면 종료 
		if(dbPost == null || 
			!dbPost.getPo_me_id().equals(customUser.getUsername())) {
			return;
		}
		//- 다오에게 게시글 정보를 주면서 수정하라고 요청
		postDAO.updatePost(post);
		
		//새 첨부파일을 추가
		if(files != null) {
			for(MultipartFile file : files) {
				insertFile(post.getPostNum(), file);
			}
		}
		if(delFileNums != null) {
			for(int num : delFileNums) {
				FileVO fileVo = postDAO.selectFile(num);
				deleteFile(fileVo);
			}
		}
	}

	public List<FileVO> getFilelist(int po_num) {
		return postDAO.selectFileList(po_num);
	}

	public String updateLike(LikeDTO like, CustomUser customUser) {
		if(like == null) {
			throw new RuntimeException("추천 정보가 없습니다.");
		}
		if(customUser == null || customUser.getUsername() == null) {
			throw new RuntimeException("로그인이 필요한 서비스입니다.");
		}
		//화면에서 보낸 추천 정보에 로그인한 사용자 아이디를 추가
		like.setId(customUser.getUsername());
		// 게시글번호와 사용자 아이디를 이용하여 추천 정보를 가져옴
		LikeVO likeVo = postDAO.selectLike(like);
		
		System.out.println(likeVo);
		
		//추천 정보가 없으면
		if(likeVo == null) {
			//다오에게 (추천정보(게시글번호, 아이디, 상태))주면서 추천/비추천을 추가하라고 요청 
			postDAO.insertLike(like);
			// 추천/비추천 했습니다를 반환
			// 추천 상태가 1이면 추천, 아니면 비추천 
			if(like.getState() == 1) {
				return "추천했습니다.";
			}	
			return "비추천했습니다.";  //이미 return값 있어서 else 안써도 ok
		}
		// - 있으면
		//  - 기존 상태와 현재 상태가 다른 경우면 (추->비추 또는 비추-> 추)
		if(likeVo.getLi_state() != like.getState()){
		//  - 다오에게 (추천정보(게시글번호, 아이디, 상태))를 주면서 추천/비추천을 수정하라고 요청
			postDAO.updateLike(like);
			// 추천/비추천 했습니다를 반환
			// 추천 상태가 1이면 추천, 아니면 비추천 
			if(like.getState() == 1) {
				return "추천했습니다.";
			}	
			return "비추천했습니다.";  //이미 return값 있어서 else 안써도 ok
		}
		// - 같으면(추천/비추천을 취소)
		// - 다오에게 게시글번호, 아이디를 주면서 추천/비추천을 삭제하라고 요청
			postDAO.deleteLike(like);
			if(like.getState() == 1) {
				return "추천을 취소했습니다.";
			}	
			return "비추천을 취소했습니다.";  //이미 return값 있어서 else 안써도 ok
		}

	public int getLikeCount(int postNum, int state) {
		return postDAO.selectLikeCount(postNum, state);
	}

	public int getLikeState(int postNum, CustomUser customUser) {
		//사용자가 로그인 안했으면 0을 반환
		if(customUser == null || customUser.getUsername()==null) {
			return 0;
		}
		LikeDTO likeDto = new LikeDTO(postNum, customUser.getUsername());
		LikeVO like = postDAO.selectLike(likeDto);
		// 추천/비추천을 한적이 없으면
		if(like == null) {
			return 0;
		}
		return like.getLi_state();
	}

	public void updateBoardLike(int postNum) {
		//게시글의 추천/비추천 수를 계산해서 변경
		postDAO.updateBoardLike(postNum);
		
	}
	
}
