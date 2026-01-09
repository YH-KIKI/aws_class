package kr.hi.community.model.dto;

public class CommentDTO {
	
	String id;
	String content;
	int postNum;
	int coOriNum; //댓글이면 기본키와 같은 값. 대댓이면 기본키와 다른 값

}
