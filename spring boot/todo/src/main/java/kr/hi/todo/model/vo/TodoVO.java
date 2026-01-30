package kr.hi.todo.model.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TodoVO {
	
	int num;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	Date date;
	String text;
	int order;
	
	

}
