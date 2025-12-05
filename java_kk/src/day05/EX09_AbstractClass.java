package day05;

public class EX09_AbstractClass {

	public static void main(String[] args) {
		/* 추상 클래스 (교재 p.290)
		 * - 클래스 앞에 abstract 키워드가 붙은 클래스
		 * - 보통 추상 클래스는 추상 메서드를 가지고 있음
		 * - 추상 클래스는 객체를 생성할 수 없음 (교재 p.298)
		 * 	 => 기능 구현이 안된 메서드가 있기 때문에
		 * 	 => 추상 클래스의 객체를 생성하려면 추상 클래스를 상속받은 클래스를 만든 후 
		 *  	추상 메서드를 오버라이딩하여 구현 후 객체를 생성
		 * 
		 * 추상 메서드 (교재 p.290)
		 * - 메서드의 정의하는 부분이 있고, 구현하는 부분이 없는 메서드
		 * - 이런 기능이 있을 예정인데, 아직 구현 안했음
		 * - 일반 메서드 형태
		 * 	 리턴타입 메서드명(매개변수){ 구현 }
		 * - 추상 메서드 형태
		 * 	 리턴타입 메서드명(매개변수);
		 * */
		//추상 클래스는 객체를 생성할 수 없음
		//Computer com = new Computer();
		Computer com = new MyComputer(); //업캐스팅
		com.trunOn();
		com.printState();
		com.trunOff();
		com.printState();

	}

}
//추상 클래스 Computer의 객체를 만들기 위해 Computer 클래슬르 상속받아
//MyComputer라는 클래스를 생성

class MyComputer extends Computer{

	@Override
	public void printState() {
		if(power) {
			System.out.println("전원이 켜져있습니다.");
		}else {
			System.out.println("전원이 꺼져있습니다.");
		}
	}	
}

abstract class Computer{
	protected boolean power;
	
	public void trunOn() {
		power = true;
	}
	public void trunOff() {
		power = false;
	}
	public abstract void printState();
	
}
