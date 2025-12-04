package day04;

public class Ex02_결_Operator {

	public static void main(String[] args) {
		/* 연산자
		 * - 연산자가 포함된 식을 보면 결과를 예측할 수 있다
		 * 대입 연산자
		 * - =
		 * - a = b;
		 *   - b를 a에 저장(덮어쓰기)
		 *   - 오른쪽에 있는 b에 저장된 값을 왼쪽에 있는 a에 덮어쓰기
		 * - a = b + c;
		 *   - 오른쪽에는 연산자가 올 수 있다
		 *   - 왼쪽에는 연산자가 오면 안됨
		 * 
		 * 산술 연산자
		 * - 종류
		 *   - +, -, *, /, %
		 *   - % : 나머지 연산자
		 *     - A % B : A를 B로 나누었을 때 나머지를 가져옴
		 *   - 정수 / 정수 => 정수
		 *     - 1 / 2 => 0
		 *     - /할 때 자료형변환을 해야 함
		 *     - 1 / (double)2 => 0.5
		 * 비교 연산자
		 * - 종류
		 *   - >, <, >=, <=, ==, !=
		 *   - 크기를 비교하는 연산자
		 *   - 정수나 실수, 문자만 비교 가능
		 *   - 결과가 true, false
		 * 논리 연산자
		 * - 종류
		 *   - &&, ||, !
		 *   - && : ~이고. 둘다 참이면 참
		 *   - || : ~이거나. 하나라도 참이면 참
		 *   - ! : 반대. 참=>거짓, 거짓=>참
		 *   - 결과가 true, false
		 * 증감 연산자
		 * - 종류
		 *   - ++, --
		 *   - 최종적으로 1증가/1감소
		 *   - ++변수명(전위형), 변수명++(후위형)
		 * */

		System.out.println("1 / 2 = " + 1 / 2);
		System.out.println("1 / 2 = " + 1 / (double)2);
		System.out.println("123 % 4 = " + 123 % 4);
		
		System.out.println("1 > 2 : " + (1 > 2));
		System.out.println("'1' > 2 : " + ('1' > 2));//비교는 가능.
		System.out.println("'a' > 'b' : " + ('a' > 'b'));
		
		System.out.println(" 1 >= 2 : " + (1 >= 2));
		System.out.println(" 1 > 2 || 1 == 2 : " + (1 > 2 || 1 == 2));
		
		int num1 = 1, num2 = 1;
		++num1;
		num2++;
		System.out.println("num1 = " + num1);
		System.out.println("num2 = " + num2);
		
		System.out.println("1 + 3 > 4 - 2 || 12 / 4 < 3 : " 
				+ (1 + 3 > 4 - 2 || 12 / 4 < 3));
		
	}

}

