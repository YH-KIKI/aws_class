package day03;

import java.util.Scanner;

public class EX03_ArrayTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* 3명의 국어 성적을 입력 받아 저장한 후, 3명의 국어 성적 평균을 구하는 코드를 작성하세요*/
		
		Scanner scan = new Scanner(System.in);
		System.out.print("각 3명의 국어 성적을 입력하세요 : ");
		
		int [] scores = new int[3];
		int sum = 0;
		double avg = 0.0;
		
		/*for(int i = 0; i < scores.length; i++) {
			scores[i] = scan.nextInt();
			}
		
		for(int i = 0; i < scores.length; i++) {
			sum += scores[i];
			}*/
		
		// 위 코드를 줄여서 표현
		for(int i = 0; i < scores.length; i++) {
			scores[i] = scan.nextInt();
			sum += scores[i];
			}
		
		
		avg = (double)sum / scores.length;
		System.out.println("3명의 국어 평균 : " + avg);
		
		

	}

}
