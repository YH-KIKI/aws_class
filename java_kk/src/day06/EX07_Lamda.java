package day06;

public class EX07_Lamda {

	public static void main(String[] args) {
		/* 내부 클래스
		 * - 클래스 안에 있는 클래스
		 * - 클래스를 구현하는 데 필요한 클래스이지만 다른 곳에서는 사용할 필요가 없을 때
		 * 	 내부 클래스를 만듬
		 * 
		 * 람다식 p.479
		 * - 언제 사용?
		 * 	 - 함수형 인터페이스의 객체를 만들 때 사용(편리하게 만들 때)
		 * 	 - 인터페이스의 객체를 만들려면
		 * 	   => 구현 클래스를 이용하여 객체를 생성해야 함
		 * 	   => 익명 클래스를 이용하여 객체를 생성할 때 람다식을 이용 
		 * 
		 * - 함수형 인터페이스
		 * 	 - 추상 메서드가 1개로 구성된 인터페이스
		 * - 어떻게 적용?
		 * 
		 * 	함수형인터페이스 객체명 = (매개변수명1, 매개변수명2) => {
		 * 		구현
		 * };
		 * - 매개변수가 1개이고 구현 코드가 한줄일 때
		 *  함수형인터페이스 객체명 = 매개변수명1 -> 구현;
		 * */
		MyInterface mi = (a, b)->{
			return a + b;
		};
		System.out.println(mi.sum(1, 2));
	}
}

@FunctionalInterface //필수는 아니지만 함수형 인터페이스인지 체크
interface MyInterface{
	int sum(int a, int b);
}