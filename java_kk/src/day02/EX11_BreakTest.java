package day02;

import java.util.Scanner;

public class EX11_BreakTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* 문자를 입력받아 입력받은 문자열을 출력하는 코드를 작성하세요. */
		
//		Scanner scan = new Scanner(System.in);
//		System.out.print("문자를 입력하세요 : ");
//		
//		char ch = scan.next().charAt(0); 
//		System.out.println("입력된 문자 : "+ ch);
//		
		/* 문자를 입력 받아 입력받은 문자가 q 이면 종료. 아니면 계속 입력을 받는 코드를 작성하세요. 
		 * */
		
		Scanner scan = new Scanner(System.in);
		char ch;
		System.out.print("문자를 입력하세요 : ");
		ch = scan.next().charAt(0); 
		System.out.println("입력된 문자 : "+ ch);
		
		System.out.println("=====================================");
		
		for(ch = 'a'; ch !='q';) {
			System.out.print("문자 입력 : ");
			ch = scan.next().charAt(0);
			System.out.println("입력 문자 : " + ch);
			}
		System.out.println("프로그램 종료.");
		
		System.out.println("=====================================");
		
		for( ; ; ) {
			System.out.print("문자 입력 : ");
			ch = scan.next().charAt(0);
			System.out.println("입력 문자 : " + ch);
			if(ch == 'q') {
				break;
			}
			
		}
		
		System.out.println("=====================================");
		do {System.out.print("do while문 문자 입력 : ");
		ch = scan.next().charAt(0);
		System.out.println("입력 문자 : " + ch);
		}while(ch != 'q');
		System.out.println("프로그램 종료.");
			
		}

	}

