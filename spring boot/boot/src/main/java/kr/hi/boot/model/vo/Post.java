package kr.hi.boot.model.vo;

import lombok.Data;

@Data
public class Post {
	int po_num; 
	String po_title; 
	String po_content; 
	String po_date; 
	int po_view; 
	int po_up; 
	int po_down; 
	String po_me_id; 
	int po_bo_num; 
	String po_del;
}
