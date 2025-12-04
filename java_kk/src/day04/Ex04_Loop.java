package day04;

public class Ex04_Loop {

	public static void main(String[] args) {
		/* 반복문
		 * - 왜 쓸까?
		 *   - 단순 반복할 작업을 효율적으로 작업할 수 있게 해줌
		 *   - 예 : Hello world 100번 출력 예제
		 *     - 반복문 없이 Hello world 출력문 100줄 추가
		 *     - 반복문 이용하면 Hello world 출력문을 활용하여 3줄 추가
		 * - 언제?
		 *   - 규칙성이 있는 작업을 반복적으로 할 때
		 *   - 1부터 10까지 숫자를 셀 때 
		 *   - 수강 정원이 10명이 될 때까지 상담
		 * - 종류
		 *   - for문, while문
		 *     - 조건에 따라 한번도 실행되지 않을 수 있음
		 *   - do while문 
		 *     - 무조건 한번은 실행
		 * - for문 문법
		 * 	for(초기화 ; 조건식 ; 증감식){
		 * 		실행문;
		 * 	}
		 *   - 초기화 : 생략 가능. 조건식이나 실행문에서 사용하는 변수를 초기화. 한번만 실행(처음)
		 *            초기화위치에서 변수 선언 가능 
		 *            => 여기서 선언한 변수는 for문에서만 사용
		 *            => for문 밖에서는 사용할 수 없음
		 *   - 조건식 : 생략 가능. 생략하면 참. 참이면 실행문으로, 거짓이면 반복문을 종료
		 *   - 증감식 : 생략 가능. 조건식에서 사용하는 변수를 증가, 감소 시킴 => 반복횟수를 조절
		 *   - 실행순서
		 *     초기화 => 조건식(거짓) => 반복문 종료
		 *     초기화 => 조건식(참) => 실행문 => 증감식
		 *           => 조건식(참) => 실행문 => 증감식
		 *           => 조건식(거짓) => 반복문 종료
		 *   
		 * - 조건식이란?
		 *   - 결과가 true 또는 false가 나오는 식
		 *   - 보통 비교 연산자와 논리 연산자를 활용한 식
		 * */
		/* 반복횟수 : 1부터 5까지 1씩증가
		 * 반복횟수 : 2부터 10까지 2씩증가
		 * 반복횟수 : 5부터 1까지 1씩 감소
		 * */
		
		int i;
		for( i = 1; i <= 5; ++i) {
			System.out.println("Hello world");
		}
		System.out.println("==============");
		for( i = 2; i <= 10; i = i +2) {
			System.out.println("Hello world");
		}
		System.out.println("==============");
		for(i = 5; i >= 1; --i) {
			System.out.println("Hello world");
		}
		
		/* while문
		 * - 특징
		 *	 - for 문과 다르게 초기화, 증감식 위치가 보장 되지 않음
		 *	 - 보통 while문 시작전에 초기화.
		 *	 - 실행문에 증감식이 포함되거나 조건식에 증감식이 포함
		 * - while문 보통 일정한 반복 횟수가 없을 때 사용
		 * - 문법
		 * while(조건식){
		 * 		실행문;
		 * }
		 * */
		//1부터 5까지 1씩 증가
		System.out.println("==============");
		i = 1;
		while(i <= 5) {
			System.out.println("Hello world");
			++i;
		}
			
		//5부터 1까지 1씩 감소(잘 모르겠으면 위 방법을 활용)
		System.out.println("==============");
		i = 5;
		while(i-- >= 1) {
			System.out.println("Hello world");
		}
		
		
		/* do while문
		 * - 특징
		 * 	 - 최소 한번은 실행
		 * - 문법
		 * do{
		 * 	  실행문;
		 * }while(조건식); //여기서 ;는 필수
		 * */
		
		i = 1;
		System.out.println("==============");
		do {
			System.out.println("출력");
			i++;
		}while(i >= 5); //잘못된 조건식
		
		/* 중첩 반복문
		 * - 반복문 안에 반복문이 들어가 있는 형태
		 */
		/* break문
		 * - switch나 반복문을 빠져 나가는 역할
		 * - switch에서는 case 마지막에 break를 주어서 빠져 나감 
		 * - 반복문에서는 if문과 함께 사용되어 반복문을 빠져 나감
		 * 
		 * continue문
		 * - 스킵
		 * - 반복문에서 continue를 만나면 특정 위치로 이동
		 * - 이동하는 특정 위치
		 * 	 - for문 : 증감식
		 * 	 - while문, do while문 : 조건식
		 * 
		 */
		//Hello wolrd 5번 출력 예제 break문
		i = 1;
		System.out.println("=================");
		while(true) {
			System.out.println("Hello world");
			if(i == 5) {
				break;
			}
			i++;
		}
		
		//10이하의 짝수를 출력하는 예제 continue문
		for(i = 1; i <= 10; i++) {
			//i가 홀수이면 건너뜀
			if(i % 2 != 0) {
				continue;
			}
			System.out.println(i + " ");
		}
		
		/*무한 루프
		 * - 조건식이 항상 참이여서 반복문을 빠져 나오지 못하는 상황
		 * - 보통 무한루프로 만든 후 if문과 break를 이용하여 특정 상황에서 종료 되게 함
		 * for문에서 무한 루프
		 * - 조건식을 생략 (true해도 되긴 함)
		 * while문에서 무한 루프
		 * - 조건식에 true
		 * */
		
		
	}

}
