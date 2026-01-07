package kr.hi.community.model.util;

import lombok.Data;

//페이지네이션을 관리하는 클래스
//현재 페이지네이션을 관리해줌
//시작 페이지를 몇페이지로 할건지, 마지막 페이지를 몇페이지로 할건지 
//이전 버튼을 활성화 할건지, 다음 버튼을 활성화 할건지
@Data
public class PageMaker {
	
	private int totalCount; //전체 컨텐츠 개수 => 마지막 페이지네이션의 마지막 페이지를 계산하기 위해
	
	private int startPage;//페이지네이션 시작 페이지번호
	private int endPage;//페이지네이션 마지막 페이지번호
	
	private boolean prev;//이전버튼 활성화
	private boolean next;//다음버튼 활성화
	
	private int displayPageNum;//한 페이지네이션에서 보여준 페이지의 최대 숫자 개수
	
	// 현재 페이지 정보를 관리, 검색어, 검색 타입, 게시판등
	private Criteria cri;
	
	//totalCount, diplayPageNum, perPageNum(cri)를 이용하여 
	//endPage, startPage, prev, next를 계산하는 메서드
	//예시 : 전체 게시글이 : 131개, 한페이지에 게시글이 10, 14페이지
	public void calculate() {
		//현재 페이지에 대한 최대 페이지번호
		//현재 페이지 : 4, 한페이지에 컨텐츠 개수가 10, 한 페이지네이션의 페이지 개수 : 10
		//endPage = (int)(Math.ceilt(0.4) * 10);
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		
		startPage = endPage - displayPageNum + 1;
		//컨텐츠 개수를 이용하여 계산한 최대 페이지 번호 : 14
		//tmpEndpage -> 마지막 페이지네이션의 페이지번호 
		int tmpEndPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		//endPage와 tmpEndPage 중 작은 값을 endPage로 설정
		//마지막 페이지네이션이면서 계산된 마지막 페이지와 실제 마지막 페이지가 다르면
		if(endPage > tmpEndPage) {
			endPage = tmpEndPage;
		}
		//첫번째 페이지네이션이면 false 아니면 true
		prev = startPage == 1 ? false : true;
		//마지막 페이지네이션이면 false 아니면 true
		next = endPage == tmpEndPage ? false : true;
	}
	public PageMaker(int displayPageNum, Criteria cri, int totalCount) {
		this.displayPageNum = displayPageNum;
		this.cri = cri;
		this.totalCount = totalCount;
		calculate();
	}

}
