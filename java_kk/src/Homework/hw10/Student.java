package Homework.hw10;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student implements Serializable {
	
	private static final long serialVersionUID = 123L;
	
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
	//학생의 성적을 출력하는 메서드
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
			return;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(classNum, grade, num);
	}

	//학생의 학년,반,번호가 같으면 같다고 판별하는 equals를 오버라이딩하세요.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return classNum == other.classNum 
				&& grade == other.grade 
				&& num == other.num;
	}

	public boolean addSubjectScore(SubjectScore subjectScore) {
		//등록된 성적인지 확인하기 위해 성적 목록에서 몇번지에 있는지 확인
		int index = list.indexOf(subjectScore);
		
		//없으면(- 1번지이면) 성적을 추가 -> true를 반환 (추가했으니까)
		if(index < 0) {
			list.add(subjectScore);
			return true;
			//return list.add(subjectScore); 이렇게 한줄로 써도 OK
		}
		
		//있으면 기존 성적에 새 점수로 수정 -> false를 반환(추가가 아닌 수정을 했으니까)
		
		//기존 성적 정보
		SubjectScore selectedScore = list.get(index);
		//기존 성적을 새 성적으로 수정
		selectedScore.setScore(subjectScore.getScore());
		// list.set(index, selectedScore); 위 2줄과 같음 
		return false;
	}

	public boolean removeSubjectScore(Subject subject) {
		
		// 과목 정보를 이용하여 삭제할 성적 정보를 생성
		// 0학년 0학기 (과목명 없음) 0점인 성적 정보를 생성
		SubjectScore subjectScore = new SubjectScore(0, 0, "", 0);
		// ?학년 ?학기 ?? 0점인 성적으로 변경 
		subjectScore.setSubject(subject);
				
		// 삭제할 성적 정보를 이용하여 성적 목록에서 제거하고 성공하면 true를 반환
		// 실패하면 false를 반환
		return list.remove(subjectScore);
		
		// 아래 코드로 하면 원하는 결과가 안나옴
		//remove Objects.equals를 호출 -> 두 객체의 클래스가 다르면 무조건 false
		// return list.remove(subject);	
		
	}
	
	


}
