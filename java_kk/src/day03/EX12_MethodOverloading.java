package day03;

public class EX12_MethodOverloading {

	public static void main(String[] args) {
		/* 메서드 오버로딩
		 * - 동일한 이름의 메서드가 여러개 있는 경우
		 * - 조건 : 매개변수가 다름. 둘 중 하나를 만족하면 됨
		 *   1. 매개변수의 종류가 다름
		 *   2. 매개변수의 개수가 다름
		 * */
		int result = sum(1,2);
		System.out.println(result);
		double result2 = sum(1.2, 2.3);
		System.out.println(result2);
		
		//println은 대표적인 메서드 오버로딩
		System.out.println(1);
		System.out.println('1');
		System.out.println("1");
		System.out.println(1.1);
	}
	public static int sum(int num1, int num2) {
		return num1 + num2;
	}
	public static double sum(double num1, double num2) {
		return num1 + num2;
	}
	//에러 발생. 리턴타입만 다르면 메서드 오버로딩을 적용할 수 없음
	/*
	public static double sum(int num1, int num2) {
		return num1 + num2;
	}
	*/
}