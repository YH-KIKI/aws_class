package kr.hi.community.model.dto;

import lombok.Data;

@Data
public class CommentDTO {
	
	
	String content;
	int postNum;
	int coOriNum; //댓글이면 기본키와 같은 값. 대댓이면 기본키와 다른 값
	String id;

		
}

