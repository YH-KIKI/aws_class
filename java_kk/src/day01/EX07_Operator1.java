package day01;

public class EX07_Operator1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 연산자
		 * 목표
		 * - 연산자 종류를 알고 결과를 예측할 수 있다.
		 * 
		 * 1. 대입 연산자(p.70)
		 * - = 
		 * - 오른쪽 값을 왼쪽에 저장
		 * - 오른쪽에는 식(연산자가 포함된)이 올 수 있지만 왼쪽은 안됨
		 * 
		 * 2. 산술 연산자(p.72)
		 * - +(덧셈), -(뺼셈), *(곱셈), /(나눗셈), %(나머지)
		 * - 점수 연산자 정수 => 정수
		 * 	- /을 조심. 1/2는 => 0.5가 아닌 0
		 * */
		
		int num = 10;
		num = num + 10;
		
		int num1 = 1, num2 = 2;
		// 괄호가 없으면 문자열 취급되어 1 + 2 = 12로 프린트
		System.out.println(num1 + " + " + num2 + " = " + num1 + num2);

		System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
		System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
		System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
		// 정수라 나누기하면 .뒤에 숫자가 생략되어 결과가 0
		System.out.println(num1 + " / " + num2 + " = " + (num1 / num2));
		// 나누기 전에 double(실수형)으로 변환해야 값이 제대로 나옴
		System.out.println(num1 + " / " + num2 + " = " + (num1 / (double) num2));
		System.out.println(num1 + " % " + num2 + " = " + (num1 % num2));
		
		
		/* 증감연산자(p.75)
		 * - 최종적으로 1증가/1감소
		 * - ++ : 1증가, -- : 1감소
		 * - 전위형
		 *   - ++변수, --변수
		 *   - 변수를 증가시키고 일은 함
		 *   
		 * - 후위형
		 * 	 - 변수++, 변수--
		 *   - 일을 하고 변수를 증가 시킴
		 * 
		 * */
		int num3 = 3, num4 = 3;
		
		//num3이 증가를 한 후, 증가한 num3의 값을 num5에 저장
		int num5 = ++num3;
		//num4의 값을 num6에 저장한 후, num4의 값을 증가
		int num6 = num4++;
		
		System.out.println("num3 : " + num3); //4
		System.out.println("num4 : " + num4); //4
		System.out.println("num5 : " + num5); //4
		System.out.println("num6 : " + num6); //3
		
		//num5,6처럼 결과가 되도록 작업 
		num3 = 3;
		num4 = 3;
		int num7 = num3, num8 = num4;
		
		++num3; //증가 시키고
		num7 = num3; //일을 함
		
		System.out.println("num7 : " + num7); //3
		System.out.println("num8 : " + num8); //3
		
		
	}

}
