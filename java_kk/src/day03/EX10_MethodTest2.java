package day03;

public class EX10_MethodTest2 {

	public static void main(String[] args) {
		/* 두 정수의 최대 공약수를 구하는 메서들르 만들고 테스트 해보세요
		 * */
		
		int num1 = 8, num2 =12;
		int myGcd = myText(num1, num2);
		System.out.println(myGcd);
		
		
		System.out.println(num1 + "과 " + num2 + "의 최대 공약수 : " + gcd(num1, num2));
		
		/* 최대 공약수를 이용하여 최소 공배수를 구하세요.
		 * A, B의 최대 공약수를 g라 하면
		 * A = ga, B = gb로 표현할 수 있고,
		 * 최대 공약수를 이용하여 최소 공배수 1를 다음과 같이 표현
		 * l = gab
		 * l = AB/g
		 * 
		 * */
		int lcm = num1 * num2 / gcd(num1, num2);
		System.out.println(num1 + "과 " + num2 + "의 최소 공배수 : " + lcm);
		
		
	}
	/* 기능 : 두 정수의 최대 공약수를 구하는 메서드
	 * 매개변수 : 두 정수 => int num1, int num2
	 * 리턴타입 : 두 정수의 산술 연산 결과 => 정수 => int
	 * 메서드명 : gcd
	 * */
	public static int myText(int num1, int num2){ //내풀이
		int myGcd = 1;
		for (int i = 1; i <= num1; i++) {
			if(num1 % i == 0 && num2 % i == 0) {
				myGcd = i;
			}
		}
		 return myGcd;
	}
	
	public static int gcd(int num1, int num2) { //강사님 풀이(큰수부터 거꾸로 체크)
		for(int i = num1; i >= 1; i--) {
			//i가 num1의 약수가 아니면 건너뜀)
			if(num1 % i != 0) {
				continue;
			}
			//i가 num2의 약수 % i ! = 0) {가 아니면 건너뜀
			if(num2 % i != 0) {
				continue;
			}
			return i;	
		}
		return 1;
	
	}
	
}
