package day03;

public class EX12_MethodOverloading {

	public static void main(String[] args) {
		/* 두 정수의 최대 공약수를 구하는 메서드를 만들고 테스트해보세요.
		 * */
		int num1 = 8, num2 = 12;
		System.out.println(num1 + "과 " + num2 + "의 최대 공약수 : " 
				+ gcd(num1, num2));
		
		/* 최대 공약수를 이용하여 최소 공배수를 구하세요.
		 * A, B의 최대 공약수를 g라 하면
		 * A = ga, B = gb로 표현할 수 있고,
		 * 최대 공약수를 이용하여 최소 공배수 l를 다음과 같이 표현
		 * l = gab
		 * l = AB/g
		 * 
		 * */
		int lcm = num1 * num2 / gcd(num1, num2);
		System.out.println(num1 + "과 " + num2 + "의 최소 공배수 : " 
				+ lcm);
		
	}
	/**기능 : 두 정수의 최대 공약수를 구하는 메서드
	 * 매개변수 : 두 정수 => int num1, int num2
	 * 리턴타입 : 두 정수의 최대 공약수 => 정수 => int 
	 * 메서드명 : gcd
	 */
	public static int gcd(int num1, int num2) {
		for(int i = num1; i >= 1; i--) {
			//i가 num1의 약수가 아니면 건너뜀
			if(num1 % i != 0) {
				continue;
			}
			//i가 num2의 약수가 아니면 건너뜀
			if(num2 % i != 0) {
				continue;
			}
			return i;//처음구한 공약수가 최대 공약수. 왜? 큰수부터 내려오기 때문에
		}
		return 1;
	}
}