package kr.hi.boot.model.dto;

import java.util.List;

import kr.hi.boot.model.util.PageMaker;
import kr.hi.boot.model.vo.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponseDTO {
	List<Comment> list;
	PageMaker pm;
	
}
