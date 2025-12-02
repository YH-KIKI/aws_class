package day02;

public class Ex02_ForTest {

	public static void main(String[] args) {

		//구구단 2단을 출력하는 코드를 작성하세요.
		int i = 1;
		for( ; i <= 9 ; ++i) {
			System.out.println("2 X " + i + " = " + 2 * i);
			
		}
		System.out.println("------------------");
		//a에서 z까지 출력하는 코드를 작성하세요.
		//문자 a는 유니코드표에서 십진수 97이다. z는 122이다
		System.out.println((int)'a');
		System.out.println((char)97);
		
		for(int num = 97; num <= 122; num++ ) {
			System.out.print((char)num);
		}
		System.out.println();
		
		for(int num = 0; num < 26; num++) {
			System.out.print( (char)('a' + num));
		}
		System.out.println();
		
		for(char ch = 'a' ; ch <= 'z'; ch++) {
			System.out.print(ch);
		}
		System.out.println();
		
		/* 1부터 10까지 합을 구하는 코드를 작성하세요.
		 * 
		 * sum = 0
		 * i = 1, sum = 0 + 1
		 * i = 2, sum = 0 + 1 + 2
		 * i = 3, sum = 0 + 1 + 2 + 3
		 * ...
		 * i = 9, sum = 0 + 1 + 2 + 3 + 4 + ... + 9
		 * i =10, sum = 0 + 1 + 2 + 3 + 4 + ... + 9 + 10
		 * 
		 * 원래 sum인데 초기 sum을 이해를 위해 sum0로 표시
		 * sum0 = 0
		 * i = 1, sum1 = sum0 + 1
		 * i = 2, sum2 = sum1 + 2
		 * i = 3, sum3 = sum2 + 3
		 * ...
		 * i = 9, sum9 = sum8 + 9
		 * i =10, sum10= sum9 + 10
		 *        sum = sum + i
		 * */
		int sum;
		for(i = 1, sum = 0; i<=10; i++) {
			sum = sum + i; //sum += i;
		}
		System.out.println("1부터 10까지 합 : " + sum);
	}

}
