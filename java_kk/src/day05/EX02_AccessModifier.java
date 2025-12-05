package day05;

public class EX02_AccessModifier {

	public static void main(String[] args) {
		/* 접근 제어자 (교재 p.162)
		 * - 정보를 은닉하기 위해 접근 제어자를 활용
		 * - 직접 접근해야 하는 정보와 기능을 통해 접근해야 하는 정보로 구분지어 사용
		 *   -> 기능을 통해 접근해야 하는 정보들을 정보 은닉이라고 함
		 * - 종류
		 * 	 - public : 모두 접근이 가능
		 * 	   - 클래스에 public을 붙이는 조건
		 * 		 - 클래스명과 파일명이 같은 경우면 public
		 *   - protected : 나 + 자식 클래스 + 같은 패키지 
		 * 	 - 디폴트(접근 제어자가 없음) : 나 + 같은 패키지 
		 *   - private : 나 
		 * - 보통 필드(멤버 변수)는 private로 만들고, 메서드는 public으로 만듬
		 * 	=> 필드를 private로 하고, 필드를 접근할  수 있는 메서드를 추가
		 *  => getter(필드 값을 가져오는 메서드), setter(필드 값으 수정하는 메서드) 교재 p.163
		 *  => 보통 IDE에서 getter/setter 만드는 기능을 제공
		 *  => lombok 라이브러리를 이용하면 어노테이션(@)을 이용하여 getter, setter를 쉽게 추가할 수 있음
		 * */
		Student std = new Student();
		std.print();
		std.setGrade(1);
		std.print();
		System.out.println(std.getGrade());

		
	}

}

class Student{
	
	private int grade, classNum, num;
	private String name;
	
	//getter
	/*public 리턴타입 메서드명() {
		return 값;
	}
	리턴타입 : 필드의 타입과 동일
	메서드명 : get필드명으로 작성. 필드명은 대문자로 시작
	값 : 해당 필드
	*/
	public int getGrade() {
		return grade;
	}
	
	//setter
	/*public void 메서드명(매개변수) {
		필드 = 매개변수;
	}
	메서드명 : set필드명. 필드명은 대문자로
	매개변수는 보통 필드와 같은 자료형의 변수가 옴
	*/
	public void setGrade(int grade1) {
		grade = grade1;
	}
	
	public void print(){
		System.out.println(grade + "학년 " + classNum + "반 " + num + "번 " + name);
	}

	
}