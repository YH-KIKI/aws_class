package day01;

import java.util.Scanner;

public class EX15_HomeWork {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* 성적을 입력받아 성적에 맞는 학점을 출력하는 코드를 작성하세요. switch
		 * A : 90 ~ 109
		 * B : 80 ~ 89
		 * C : 70 ~ 79
		 * D : 60 ~ 69
		 * F : -9 ~ 59
		 * 잘못된 성적 : -9미만, 110이상
		 * */
		
		Scanner scan = new Scanner(System.in);
		
		//입력 안내문구
		System.out.print("성적 입력 : ");
		//성적을 입력
		int score = scan.nextInt();
		
		//성적에 따라 학점을 출력
		// 1~9를 10으로 나누면 몫 : 0,
		// 10~19를 10으로 나누면 몫 : 1
		switch(score / 10) {
		case 9, 10:
			System.out.println(score + "점은 A");
			break;
		case 8:
			System.out.println(score + "점은 B");
			break;
		case 7:
			System.out.println(score + "점은 C");
			break;
		case 6:
			System.out.println(score + "점은 D");
			break;
		case 0,1,2,3,4,5:
			System.out.println(score + "점은 F");
			break;
		default:
			System.out.println(score+"점은 잘못된 점수");
			break;
		}

	}

}
