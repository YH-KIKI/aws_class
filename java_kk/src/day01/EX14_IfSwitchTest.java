package day01;

import java.util.Scanner;

public class EX14_IfSwitchTest {
	
	public static void main(String[] args) {
		/* 두 정수와 산술연산자를 입력받아 산술 연산 결과를 출력하는 코드를 작성하세요.
		 * 예시
		 * 두 정수와 연산자 입력 (예 : 1 + 2) : 1 / 2
		 * 1 / 2 = 0.5
		 * 
		 * 예시2
		 * 두 정수와 연산자 입력 (예 : 1 + 2) : 1 ? 2
		 * ?는 산술 연산자가 아닙니다.
		 * */
		
		Scanner scan = new Scanner(System.in);
		System.out.print("두 정수를 입력 하세요 : ");
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		System.out.print("문자를 입력하세요 : ");
		char op = scan.next().charAt(0);
		
		switch(op) {
			case '-':
				System.out.println(num1 + " " + op +  " " + num2 + " = " + (num1 - num2));
				break;
			case '+':
				System.out.println(num1 + " " + op +  " " + num2 + " = " + (num1 + num2));
				break;
			case '*':
				System.out.println(num1 + " " + op +  " " + num2 + " = " + (num1 * num2));
				break;
			case '/':
				System.out.println(num1 + " " + op +  " " + num2 + " = " + (num1 / (double)num2));
				break;
			case '%':
				System.out.println(num1 + " " + op +  " " + num2 + " = " + (num1 % num2));
				break;
			default:
				System.out.println(op + "는 산술 연산자가 아닙니다.");
		}
		
		if(op == '+'){
			System.out.print(num1 + " " + op +  " " + num2 + " = " + (num1 + num2));
		}else if(op == '-'){
			System.out.print(num1 + " " + op +  " " + num2 + " = " + (num1 - num2));
		}else if(op == '*'){
			System.out.print(num1 + " " + op +  " " + num2 + " = " + (num1 * num2));
		}else if(op == '/'){
			System.out.print(num1 + " " + op +  " " + num2 + " = " + (num1 / (double)num2));
		}else if(op == '%'){
			System.out.print(num1 + " " + op +  " " + num2 + " = " + (num1 % num2));
		}else {
			System.out.print(op + "는 산술 연산자가 아닙니다.");
		}
	}

}
