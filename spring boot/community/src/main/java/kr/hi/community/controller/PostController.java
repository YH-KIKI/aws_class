package kr.hi.community.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.hi.community.model.dto.LikeDTO;
import kr.hi.community.model.dto.PostDTO;
import kr.hi.community.model.util.Criteria;
import kr.hi.community.model.util.CustomUser;
import kr.hi.community.model.util.PageMaker;
import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.FileVO;
import kr.hi.community.model.vo.PostVO;
import kr.hi.community.service.PostService;


@Controller
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/post/list/{num}")
	public String postList(Model model,
		@PathVariable("num") int boardNum,
		//기본생성자를 이용하여 객체를 생성한 후 ?뒤에 변수값이 필드와 일치하면 값을 수정
		Criteria cri) {
		//Criteria에 게시판 번호를 넣어줌
		cri.setBoardNum(boardNum);

		//서비스에게 게시판 번호에 맞는 게시글 목록을 가져오라고 요청 
		//가져온 게시글 목록을 'list'에 저장
		ArrayList<PostVO> list = postService.getPostList(cri); 
		
		//서비스에게 게시판 목록을 가져오라고 요청 
		ArrayList<BoardVO> boardList = postService.getBoardList();
		
		//서비스에게 페이지 정보(검색어, 게시판, 타입)을 주면서 일치하는 게시글 수를 가져오라고 요청 
		int totalCount = postService.getTotalCount(cri);
		//페이지메이커를 생성
		PageMaker pm = new PageMaker (3, cri, totalCount);
		
		//게시글 목록을 화면에 전송
		model.addAttribute("list", list);
		model.addAttribute("boardNum", boardNum);
		//게시판 목록을 화면에 전송 
		model.addAttribute("boardList", boardList);
		
		model.addAttribute("pm", pm);
		return "/post/list"; //post폴더에 list.html을 화면으로 보내줌
		
	}
	
	
	@GetMapping("/post/detail/{num}")
	public String postDetail(Model model,
		@PathVariable("num") int po_num,
		@AuthenticationPrincipal CustomUser customUser) {
		// System.out.println(po_num); 
		// num이 잘 넘어오는지 확인 써놓고 url에 http://localhost:8080/post/detail/숫자 아무거나 쓰고 콘솔에 숫자 넘어오는지 확인
		// 게시글 번호를 이용해서 조회수 증가
		postService.updateView(po_num);	//서비스에게 일을시켜!
		// 게시글 번호를 이용해서 게시글 가져옴
		// 게시글 번호(기본키)를 이용하여 게시글을 조회하면? -> 최대 1개(왜?) 기본키니까
		// 기본키니까 -> 기본키의 정의 -> 기본키로 검색하면 최대 1행이 조회되는 컬럼
		PostVO post = postService.getPost(po_num);
		// System.out.println(post);  // 잘 가져오는지 확인 -> 매퍼까지 다 작성한다음에 게시글 클릭하면 콘솔에 정보가 넘어옴
		
		//서비스에게 게시글 번호를 주면서 첨부파일을 가져오라고 오청 
		List<FileVO> files = postService.getFilelist(po_num);
		
		// 가져온 게시글을 화면에 전달
		model.addAttribute("post", post);
		// 가져온 첨부파일 목록을 화면에 전달
		model.addAttribute("files", files);
		model.addAttribute("user", customUser == null? "" : customUser.getUsername());
	    return "/post/detail";
	}
	
	@GetMapping("/post/insert")
	public String postInsert(Model model) {
		//서비스에게 게시글 목록을 가져오라고 요청
		ArrayList<BoardVO> list = postService.getBoardList();
		//가져온 게시판 목록을 화면에 전달
		model.addAttribute("list", list);
		return "/post/insert";
	}
	
	@PostMapping("/post/insert")
	//만약 DTO 사용안할 경우 @RequestParam("title") String title 이렇게 하나씩  
	public String postInsertPost(
			//게시글 등록에 필요한 정보를 받아옴
			PostDTO post, //제목, 내용, 게시판번호
			@AuthenticationPrincipal CustomUser customUser, //작성자(로그인한 사용자 정보) 
			@RequestParam("files") List<MultipartFile> files
			) {
		// System.out.println(customUser); //화면확인 -> 다른정보 post / 유저정보 customUser넣고 하면됨
		// 게시글 정보와 작성자 정보와 첨부파일 정보를 서비스에게 주면서 등록하라고 요청
		boolean result = postService.insertPost(post, customUser, files); //일 시킨결과값을 알려줘 불리언 변수(result)에 저장해서 활용하게!
		//등록에 성공하면 /post/list로 이동, 실패하면 /post/insert로 이동
		if(result) {
			return "redirect:/post/list/" + post.getBoard();
		} 
		return "redirect:/post/insert"; 
	}
	
	@PostMapping("/post/delete/{num}")
	public String postDelete(
		//게시글 번호 가져와줘
		@PathVariable("num") int postNum,
		//로그인한 회원 정보를 가져옴
		@AuthenticationPrincipal CustomUser customUser){
		// 서비스에게 게시글 번호를 주면서 게시글 정보를 가져오라고 요청
		PostVO post = postService.getPost(postNum);
		// 서비스에게 게시글 번호와 사용자 정보를 주면서 게시글을 삭제하라고 요청
		postService.deletePost(postNum, customUser);
		return"redirect:/post/list/" + post.getPo_bo_num(); //삭제한 게시판으로 가기 위해 po_bo_num()
	}
	
	@GetMapping("/post/update/{num}")
	public String postUpdate(
		@PathVariable("num") int postNum, Model model) {
		//서비스에게 게시글 번호(postNum)를 주면서 게시글을 가져오라고 요청
		//게시글 객체 = 서비스.메서드명(게시글번호);
		//- 게시글에 해당하는 클래스 : PostVO
		//- 게시글 번호 : postNum
		//- 메서드명은 적당히 작성하면 되는데, 이미 구현된 getPost를 가져오면 새로 추가
		//  하지 않아도 됨
		//- 객체명은 적당히 작성하면 됨
		PostVO post = postService.getPost(postNum);
		
		//서비스에게 게시글 번호를 주면서 첨부파일 목록을 가져오라고 요청
		List<FileVO> files = postService.getFilelist(postNum);
		
		//서비스에게 A와 B를 주면서 ~~을 시킴
		//=> 서비스.메서드명(A, B);
		//서비스에게 A와 B를 주면서 ~~을 가져오라고 요청
		//=> 클래스명 객체 = 서비스.메서드(A, B); 
				
		model.addAttribute("post", post);
		model.addAttribute("files", files);
		return "/post/update";
	}
	
	@PostMapping("/post/update/{num}")
	public String postUpdatePost(
		//- 게시글 번호를 가져옴
		@PathVariable("num")int postNum,
		//- 화면에서 보낸 제목과 내용을 가져옴
		//방법1. 각각 가져옴
		//@RequestParam("title")String title,
		//@RequestParam("content")String content,
		//화면에서 보낸 title과 PostDTO에 멤버변수 title이 이름이 같기 때문에
		//자동으로 화면에서 보낸 제목을 넣어줌
		//방법2. DTO에 한번에 가져옴
		PostDTO post,
		//- 로그인한 사용자 정보와 첨부파일 정보를 가져옴
		@AuthenticationPrincipal CustomUser customUser,
		@RequestParam("files") List<MultipartFile> files,
		@RequestParam(value="delFileNums", 
			required = false) /*삭제할 첨부가 없는 경우를 처리*/
			List<Integer> delFileNums) {		
		/*for(MultipartFile file : files) {
			System.out.println(file.getOriginalFilename()); // 변경한 파일명 잘 넘어오는지 중간체크
			System.out.println(delFileNums); */ //파일번호 확인
			
		//- 서비스에게 게시글정보와(게시글번호, 제목, 내용) 사용자 정보를 
		//   주면서 수정하라고 요청
		//서비스에게 A와 B를 주면서 ~~을 시킴
		//서비스에게 게시글정보와 사용자 정보를 주면서
		//서비스.메서드명(게시글정보, 사용자정보);
		
		//방법1. 제목, 내용 각각
		//postService.메서드(postNum, title, content, customUser);
		
		//방법2. 제목, 내용을 DTO에 담아서. PostDTO에 postNum을 추가
		post.setPostNum(postNum);
		postService.updatePost(post, customUser, files, delFileNums);
		return "redirect:/post/detail/{num}";
	}
	
	@PostMapping("/post/like")
	//리턴값을 뷰리졸버로 분석하지 않고 리턴값을 순수하게 화면으로 전송
	@ResponseBody
	public ResponseEntity<String> postLike(
		@RequestBody LikeDTO like,	// 추천/비추천수 가져오기
		@AuthenticationPrincipal CustomUser customUser //로그인한 사용자 정보 가져오기
		) {
		// 서비스에게 게시글 번호와 상태, 사용자 정보를 주면서 추천/비추천을 진행하고 결과를 가져오라고 요청
		try{String result = postService.updateLike(like, customUser);
			//게시글의 추천/비추천 수를 계산해서 변경
			postService.updateBoardLike(like.getPostNum());
			return ResponseEntity.ok(result);
		}catch(Exception e){
			//로그인 안했을때 예외 발생
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}
	
	@GetMapping("/post/like/count/{num}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> postLikeCount (
		HashMap<String, Object> map,
		@PathVariable("num")int postNum) {
		//HashMap<String, Object> map = new HashMap<String, Object>(); 와 동일
		//매개변수에 넣으면 기본생성자(????)를 자동으로 만들어주기 때문에?
		// 서비스에게 게시글 번호와 추천(1)을 주면서 추천수를 가져오라고 요청
		// 서비스에게 게시글 번호와 비추천(-1)을 주면서 비추천수를 가져오라고 요청
		// 방법1 (각각 처리하는 방식)
		// 추천수 = 서비스.추천수가져와(게시글번호);
		// 비추천수 = 서비스.비추천수가져와(게시글번호);
		
		//방법2 메서드 한개로 한꺼번에 처리 가능
		// 추천수 = 서비스.일치하는추천정보수가져와(게시글번호, 상태(1));
		int up = postService.getLikeCount(postNum, 1);
		// 비추천수 = 서비스.일치하는비추천정보수가져와(게시글번호, 상태(-1));
		int down = postService.getLikeCount(postNum, -1);
		map.put("up", up);
		map.put("down", down);
		return ResponseEntity.ok(map);
	}
	
	@GetMapping("/post/like/check/{num}")
	public ResponseEntity<Integer> postLikeCheck(
		@PathVariable("num")int postNum,
		@AuthenticationPrincipal CustomUser customUser){
		
		//서비스에게 게시글 번호와 사용자 정보를 줄테니 해당 게시글의 추천상태(state)를 가져와줘(가져오는건 왼쪽!)
		int state = postService.getLikeState(postNum, customUser);
		
		return ResponseEntity.ok(state);
	}
}




