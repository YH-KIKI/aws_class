package day03;

public class EX13_Constructor {

	public static void main(String[] args) {
		/* 생성자 p.150
		 * - new 연산자를 통해 생성한 객체의 필드를 초기화 할 때 사용
		 * - 특징
		 *   - 리턴타입이 없음
		 *   - 이름이 클래스명과 같음
		 *   - 생성자 오버로딩을 통해 여러 생성자를 만들 수 있음
		 *   
		 * 생성자 오버로딩 p.153
		 * - 생성자의 매개변수를 다르게 하여 새로운 생성자를 추가
		 * */
		MyCar2 car = new MyCar2();
		car.print();
		MyCar2 car2 = new MyCar2("현대", "아반떼", "검정");
		car2.print();
	}

}
class MyCar2{
	String company, name, color;
	
	//자동차 정보를 출력하는 메서드
	void print() {
		System.out.println("==================");
		System.out.println("제조사 : " + company);
		System.out.println("차명 : " + name);
		System.out.println("색상 : " + color);
		System.out.println("==================");
	}
	
	MyCar2() {
		company = "제조사 모름";
		name = "차";
		color = "검정";
	}
	MyCar2(String company1, String name1, String color1){
		company = company1;
		name = name1;
		color = color1;
	}
	
}