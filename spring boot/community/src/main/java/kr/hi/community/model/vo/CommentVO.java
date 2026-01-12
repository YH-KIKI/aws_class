package kr.hi.community.model.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class CommentVO {
	
	int co_num;
	String co_content;
	@JsonFormat(
			shape = Shape.STRING,
			pattern = "yyyy.MM.dd HH:mm", 
			timezone = "Asia/Seoul")
	Date co_date;
	int co_ori_num;
	String co_del;
	String co_me_id;
	int co_po_num;

}
