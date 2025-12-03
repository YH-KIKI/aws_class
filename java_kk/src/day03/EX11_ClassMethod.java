package day03;

public class EX11_ClassMethod {

	public static void main(String[] args) {
		/* 필드와 메서드
		 * - 필드는 같은 클래스에 있는 메서드에서 사용될 수 있음
		 * - 메서드에서 필드 값을 수정하면 수정이 된다.
		 * - 메서드가 필드값만 수정하는 경우 리턴타입이 필요하지 않음
		 * */
		MyCar car = new MyCar();
		car.turnOn();
		car.turnOff();
		car.turnOn();
		car.changeGear('D');
		//엑셀을 밟음
		for(int i = 1; i <= 5; i++){
			car.speedUp();
		}
		System.out.println("현재 속력은 " + car.speed + "km/h 입니다.");
		
		car.turnOff();
				
		for(int i = 1; i<=)
	}

}

class MyCar{
	String company, name, color;
	int speed;
	char gear;
	boolean power;
	
	// 시동을 켜는 기능
	void turnOn() {
		power = true;
	}
		
	// 시동을 끄는 기능
	void turnOff() {
		//주행 중에는 시동이 꺼짖 ㅣ않게
		if(speed == 0) {
			power = false;
			System.out.println("조용~");
		}
	}
	
	// 기어 변경
	void changeGear(char nextGear) {
		switch(nextGear) {
		case 'P':
			gear = nextGear;
			System.out.println("기어가 주차로 변경됐습니다.");
			break;
		case 'D':
			gear = nextGear;
			System.out.println("기어가 주행으로 변경됐습니다.");
			break;
		case 'R':
			gear = nextGear;
			System.out.println("기어가 후진으로 변경됐습니다.");
			break;
		case 'N':
			gear = nextGear;
			System.out.println("기어가 중립로 변경됐습니다.");
			break;
		default:
			System.out.println("기어가 고장났습니다.");
		}
	}
	
	// 속력 UP (엑셀레이터 밟음) : 속력이 1씩 증가
	void speedUp() {
		speed++;		
	}
	// 속력 DOWN(브레이크 밟음) : 속력이 1씩 감소
	void speedDown() {
		speed--;
	}
	
}
