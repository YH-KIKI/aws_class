package day03;

import java.util.Scanner;

public class EX09_MethodTest {

	public static void main(String[] args) {
		/* 두 정수와 산술 연산자가 주어지면 연산 결과를 알려주는 메서드를 만들고,
		 * 만들어진 메서드를 호출해서 테스트해보세요.
		 * */
		
//		Scanner scan = new Scanner(System.in);
//		System.out.print("두 정수를 입력하세요 : ");
//		int num1 = scan.nextInt();
//		int num2 = scan.nextInt();
//		
//		
//		System.out.print("산술 연산자를 입력하세요 : ");		
//		char op = scan.next().charAt(0);
//		
//		
		int num1 = 1, num2 =2;
		char op = '/';
		double res = calculate(num1, num2, op);
		System.out.println(num1 + " " + op + " " + num2 + " = " + res);
	
		

	}
	
	/* 기능 : 두 정수와 산술 연산자가 주어지면 산술 연산 결과를 알려주는 메서드
	 * 매개변수 : 두 정수와 산술 연산자 => int num1, int num2, char op
	 * 리턴타입 : 두 정수의 산술 연산 결과 => 실수(나누기) => double
	 * 메서드명 : calculate(계산하다)
	 * @param num1 정수1
	 * @param num2 정수2
	 * @param op 산술 연산자
	 * @return 두 정수의 산술 연산 결과
	 * @author 강사
	 * */
	
	public static double calculate (int num1, int num2, char op) {
		double res = 0.0; //결과를 저장할 변수
		switch(op) {
		case '+': res = num1 + num2; break;
		case '-': res = num1 - num2; break;
		case '*': res = num1 * num2; break;
		case '/': res = num1 / (double)num2; break;
		case '%': res = num1 % num2; break;
		}
		return res;
			
	}	
		 

}

