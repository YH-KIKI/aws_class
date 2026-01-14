package kr.hi.boot.model.util;

import lombok.Data;

@Data
//게시글에서 사용할 현재 페이지 정보
public class PostCriteria extends Criteria {

	int boNum;
}