package day01;

import java.util.Scanner;

public class EX10_OperatorText {
	
	public static void main(String[] args) {
		
	/* 두 정수를 입력받아 합을 구하는 코드를 작성하세요. */
		//안내 문구
		System.out.print("두 정수 입력 : ");
		//입력 받고
		Scanner scan = new Scanner(System.in);
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		//결과 출력
		System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
	}

}
