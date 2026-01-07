package kr.hi.community.model.util;

import lombok.Data;

@Data
// Criteria 클래스를 만들면 일을 시킬 때 다른 기능(검색, 페이지)들이 추가되도
// 넘겨주는 건 Criteria 하나만 넘겨주면 됨
public class Criteria {
	
	int boardNum;

}
