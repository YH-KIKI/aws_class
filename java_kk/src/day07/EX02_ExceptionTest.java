package day07;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EX02_ExceptionTest {

	public static void main(String[] args) {
		/* 
		 * 메뉴를 출력하고 메뉴를 입력받는 예제
		 */ 
		int menu = 0;
		Scanner scan = new Scanner(System.in);
			
		do {
			System.out.println("====메뉴====");
			System.out.println("1. 메뉴1");
			System.out.println("2. 메뉴2");
			System.out.println("3. 메뉴3");
			System.out.println("메뉴 선택 : ");
			//정수가 아닌 값을 입력했을 때 예외를 발생하는 예외를 처리해서 반복문이 이어서 동작되도록 해보세요
			try {
				menu = scan.nextInt();
			}catch(InputMismatchException e) {
				System.err.println("예외 발생");
				scan.nextLine(); //입력 버퍼에 남아있는 문자열과 엔터를 제거
			}
		}while(menu != 3);
	}

}
