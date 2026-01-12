package kr.hi.community.model.util;

import lombok.Data;

@Data
// Criteria 클래스를 만들면 일을 시킬 때 다른 기능(검색, 페이지)들이 추가되도
// 넘겨주는 건 Criteria 하나만 넘겨주면 됨
public class Criteria {
	//게시판 관련 필드
	int boardNum;
	
	//검색 관련 필드
	int type;
	String search = "";
	
	//페이지 관련 필드
	int page = 1;
	int perPageNum = 5;	//한 페이지에 컨텐츠를 최대 몇개 보여줄지
	
	//결과에서 limit을 이용하여 잘라올 때 시작 번지를 계산해서 알려주는 메서드
	public int getPageStart() {
		//(페이지 - 1) * 개수
		return (page - 1) * perPageNum;
	}
}
