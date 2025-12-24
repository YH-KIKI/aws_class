package Homework.hw10;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
	
	//다음 필드를 추가하세요.
	//학생의 학년, 반, 번호, 이름, 과목, 성적들
	
	private int grade, classNum, num;
	private String name;
	private List<SubjectScore> list;

	
	//toString 오버라이딩
	//1학년 1반 3번 홍길동
	
	@Override
	public String toString() {
		return grade + "학년" + classNum + "반" + num + "번" + name;
	}
	
	//결과만 출력하면 되니 void
	public void printScore() {
		/*
		 ===================
		 1학년 1반 1번 홍길동 성적
		 ===================
		 1학년 1학기 국어 90점
		 1학년 1학기 수학 30점
		 */
		System.out.println("===================");
		// System.out.print(grade + "학년" + classNum + "반" + num + "번" + name + "성적");
		System.out.println(toString() + " 성적"); // 위 주석과 결과 같음 
		System.out.println("===================");
		
		
		if(list.size() == 0) {
			System.out.println("등록된 성적이 없습니다.");
			System.out.println("===================");
		}
		
		
		//[1학년 1학기 국어 90점, 1학년 1학기 수학 30점, ...]
		//System.out.println("list"); 
		
		for(int i = 0; i < list.size(); i++) {
			SubjectScore tmp = list.get(i); // (tmp라는 변수를 지정해서 저장했다가 결과로 씀)
			System.out.println(tmp);  //윗줄업이 바로 System.out.println(list.get(i)) 해도 됨
		}
		/* 향상된 for문으로 표현  - for(타입 변수명 : 배열 또는 컬렉션)
		for(SubjectScore tmp : list){  // tmp라는 변수 임의 지정
			system.out.println(tmp);
		}
		*/
		System.out.println("===================");
		
	}
	
	//학년, 반, 번호, 이름을 이용한 생성자를 작성하세요.
	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
		//과목 성적을 지정할 빈 리스트 생성 - 위에 list 변수선언 한 곳 아래 선언해도 상관없음
		list = new ArrayList<SubjectScore>();
	}


}
