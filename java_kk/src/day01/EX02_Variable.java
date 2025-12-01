package day01;

public class EX02_Variable {
	
	public static void main(String[] args) {

	/* 변수 : 정보를 저장하는 공간
	 * 분류 : 
	 * - 기본형 : 공간에 실제값이 저장 
	 * 	- char, byte, short, int, long, float, double, boolean
	 *  - 대표 : char, int, double, boolean
	 * 참조형 : 공간에 주소가 저장 => 실제 값은 다른 공간에 저장
	 * 	- 배열, 객체 
	 * 
	 * char
	 * - 문자 1개를 저장
	 * - ''에 저장 
	 * 
	 * int 
	 * - 정수를 저장
	 * - 대충 -2,000,000,000 ~ 2,000,000,000를 넘으면 long
	 * 
	 * double
	 * 소수점 15자리 유효
	 * 실수를 저장
	 * 
	 * bloolean
	 * - 논리값 저장, true 또는 flase
	 * 
	 * 변수 선언 방법
	 * - 변수타입 변수명; 
	 * - 변수타입 변수명 = 초기값;
	 * - 변수타입 변수명1, 변수명2;
	 * - 변수타입 변수명1 = 값1, 변수명2 = 값2;
	 * 
	 * 변수명 작성 규칙
	 * - 숫자로 시작할 수 없음
	 * - 대소문자 구별
	 * - 특수문자 _,$만 가능
	 * - 중복선언 불가(범위가 겹치는 경우)
	 * - 키워드 사용 불가
	 * 
	 * 변수명 작성 관례
	 * - 카멜 표기법 : 두번째 단어부터 첫글자를 대문자로
	 */
	
char ch1 = 'a';
System.out.println(ch1);
char ch2 = '\"'; // \\, \", \'
System.out.println(ch2);
char ch3 = '\u0041';
System.out.println(ch3); //유니코드 16진 0041 => 65에 해당하는 문자를출력

int num1= 1234567890; // 12345678901을 하면 범위를 벗어나서 에러 발생
System.out.println(num1);

long num2 = 12345678901L; // 접미사 L, l을 붙여야 함
System.out.println(num2);

System.out.println("오버플로우 예제");
byte num3 = 127; //byte가 포현할 수 있는 가장 큰 값
++num3;
System.out.println("바이트 127 + 1 : " + num3); 

double num4 = 1.234;
System.out.println(num4);

float num5 = 1.234F; //접미사 F, f를 붙여야 함
System.out.println(num5);


boolean isEven = true;
System.out.println(isEven);


	}
}

