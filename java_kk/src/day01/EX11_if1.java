package day01;

import java.util.Scanner;

public class EX11_if1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* 조건문
		 * - 상황에 따라 결과가 달라질 때 사용
		 * - 예 : 자판기에서 선택한 메뉴에 따라 나오는 음료가 다름
		 * - ~면 ...해라 표현되면 조건문
		 * 	 - ~ : 조건식
		 * 	 - ... : 실행문
		 * 
		 * else if문 문법
		 * if(조건식1){  // 조건식 1이 참이면 실행문 1을 실행
		 * 		실행문1;
		 * }else if(조건식2){  //조건식1이 거짓이고 조건식2가 참이면 실행문2를 실행
		 * 		실행문2;
		 * }else{  //조건식1과 조건식2가 모두 거짓이면 실행문3을 실행
		 * 		실행문3;
		 * }
		 *  
		 * */
		
		/*정수를 입력받아 입력받은 정수가 짝수인지 판별하는 코드를 작성하세요.
		 * 예시
		 * 정수 입력 : 5
		 * 5는 홀수
		 * 
		 */
		
		Scanner scan = new Scanner(System.in);
		System.out.print("정수 입력 : ");
		int num = scan.nextInt();
		
		if(num % 2 == 0) {
			System.out.println(num + "는 짝수");
		}else { 
			System.out.println(num + "는 홀수");
		}		
			
		String result = num % 2 == 0 ? "짝수" : "홀수";
		System.out.println(num + "는 " + result);

		
	}

}
