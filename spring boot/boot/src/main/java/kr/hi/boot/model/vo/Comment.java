package kr.hi.boot.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	int num;
	String content;
	Date date;
	int oriNum;
	String del;
	String id;
	int postNum;

}
