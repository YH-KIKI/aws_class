package kr.hi.community.model.dto;

import lombok.Data;

@Data
public class PostDTO {
	
	int postNum;
	String title;
	String content;
	String writer;
	int board;
	

}
