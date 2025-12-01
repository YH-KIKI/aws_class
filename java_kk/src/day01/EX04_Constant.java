package day01;

public class EX04_Constant {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* 상수
		 *	- 변하지 않은 수 
		 *	- 초기화를 1번만 할 수 있는 수
		 *	- final 키워드를 이용
		 *	- 선언 방법
		 *	 - final 자료형 변수명 = 초기값;
		 *	- 상수명은 대문자로, 문자 사이는 _로 구분
		 * 리터럴
		 * 	- 값 : 1, 'a'
		 * - 변수에 값을 초기화 할 때 사용하는 값
		 * 상수풀
		 * - 상수와 리터럴은 상수풀에 저장되어 사용
		 *
		 **/
		
		final int MAX_STUDENT;
		
		MAX_STUDENT = 10;  // 리터럴 10을 이용하여 상수 MAX_STYDENT의 값을 초기화
		
		System.out.println(MAX_STUDENT);

	}

}
