package day02;

import java.util.Scanner;

public class HW01_LCM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* 두 정수의 최소 공배수를 구하는 코드를 작성하세요.
		 * A의 배수 : 어떤 수를 A로 나누었을 때 나머지가 0인 수
		 * A와 B의 공배수 : A와 B의 배수들 중 공통으로 있는 수 
		 * A와 B의 최소 공배수 : A와 B의 공배수 중 가장 작은 수
		 * 6의 배수 : 6, 12, 18, 24, ...
		 * 8의 배수 : 8, 16, 24, 32, 40, ...
		 * 6과 8의 배수 : 24, 48, 72, ....
		 * 6과 8의 최소 공배수 : 24
		 */
		
		System.out.print("두 정수 입력 : ");
		Scanner scan = new Scanner(System.in);
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		
		int min = 0;
		for(int i = 1; i <= num1 * num2; i++){
			if(i % num1 == 0 && i % num2 == 0){
				min = i;
				break;
			}
		}
		
		System.out.println(num1 + "과 " + num2 + "의 최소 공배수 : " + min);
		
		
		
		/* 반복횟수 : i는 1부터 1씩 증가
		 * 규칙성 : 1가 num1과 num2의 공배수면 i를 출력 후 반복은 종료
		 * =>i가 num1의 배수이고, i가 num2의 배수이면 i를 출력 후 반복문 종료
		 * =>i를 num1로 나누었을 때 나머지가 0과 같고 i를 num2로 나누었을 때 나머지가 0과 같다면 ~
		 * */

	}

}
