package day05;

import java.util.Scanner;

public class Ex11_Interface {

	public static void main(String[] args) {
		/* 인터페이스 p.324
		 * - 추상 메서드와 클래스 상수로 구성되어 있음
		 * - 기능 목록만 나열
		 * - 인터페이스에서 변수를 선언하면 앞에 자동으로 public final static이 추가 됨
		 *   => 반드시 초기화를 해야 함
		 * - 인터페이스에서 메서드를 선언하면 앞에 자동으로 public abstract가 추가 됨 
		 * - 인터페이스 선언하기
		 * 	interface 인터페이스명{
		 * 		추상메서드;
		 * 		클래스 상수;
		 * 	}
		 * - 인터페이스의 객체를 생성하려면 구현 클래스를 만들어서 생성
		 * - 구현 클래스 선언하기 p.325
		 * 	class 클래스명 implements 인터페이스명1, 인터페이스명2, ...{
		 * 		//추상 메서드 오버라이딩
		 * 	}
		 * - 왜 사용?
		 *   - 제품 표준을 지정하면 표준에 맞추면 제조사들이 어떻게 만들든 사용자들은 표준에 맞춰
		 *     쓰면 되기 때문에
		 *   - 예를 들면 핸드폰 C타입
		 */
		System.out.println(RemoteCotroller.v);
		TVRemoteCon rc = new TVRemoteCon();
		rc.turnOn();
		rc.turnOff();
	}
}
class TVRemoteCon implements RemoteCotroller{

	boolean power;
	
	@Override
	public void turnOn() {
		power = true;
	}

	@Override
	public void turnOff() {
		power = false;
	}
	
}

interface RemoteCotroller{
	/*public final static*/int v = 220;//클래스 상수. 
	/*public abtract */void turnOn();
	void turnOff();
	
}

interface A{
	//작업1이 끝난 후 몇개 끝냈는지 정수로 알려주기로 함
	int 작업1();
	
	//작업1에서 끝낸 개수를 이용하여 작업2를 진행하여 작업2가 진행 됐는지 알려주기로 함
	boolean 작업2(int num);
	
	//작업2 진행 여부에 따라 작업3이 진행되고 결과를 마무리 함
	void 작업3(boolean process);
}
/* 숫자 야구 게임을 3명이 구현하기 했을 때
 * 1번이 랜덤수를 생성하기로 하고,
 * 2번이 정수를 입력받기로 하고, 
 * 3번이 랜덤수와 입력수를 이용하여 마무리 하기로 했다.
 * 이 때 인터페이스가 있으면 3번은 1번과 2번이 끝낼때까지 기다릴 필요 없이 구현할 수 있음
 * */
interface Baseball{
	int [] random(int min, int max, int size);
	
	int [] input(Scanner scan, int min, int max, int size);
	
	int strike(int []arr1, int []arr2);
	int ball(int []arr1, int []arr2);
	
	boolean judge(int strike, int ball);
}







