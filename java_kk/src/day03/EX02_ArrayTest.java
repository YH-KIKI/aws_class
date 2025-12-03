package day03;

import java.util.Scanner;

public class EX02_ArrayTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [] scores = new int[3];
		
		//scores에 0번지에 1, 1번지에 2, 2번지에 3을 저장하세요
				
		scores[0] = 1;
		scores[1] = 2;
		scores[2] = 3;
		
		for(int i = 0; i < 3; i++) {
			scores[i] = i + 1;
			System.out.println(scores[i]);
		}
		
		//Scanner를 이용하여 0~100사이의 정수 3개를 입력받아 배열에 저장하세요.
		Scanner scan = new Scanner(System.in);
		System.out.print("정수 3개를 입력하세요 : ");
		
		//scores[0] = scan.nextInt();
		//scores[1] = scan.nextInt();
		//scores[2] = scan.nextInt();
		
		// 위 3줄을 한번에 저장하는 식
		for(int i = 0; i < scores.length; i++) { //3대신 scores.length 활용 가능
			scores[i] = scan.nextInt();
		}
		
		// 저장한거 확인 
		for(int i = 0; i < scores.length; i++) { 
			System.out.println(scores[i]);
		}
		
		

	}
}
