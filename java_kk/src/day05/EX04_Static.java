package day05;

public class EX04_Static {

	public static void main(String[] args) {
		/* static 교재 p.192
		 * - static이 없는 필드와 메서드는 객체명을 통해 호출 
		 * 	 - static 없는 필드/메서드
		 * 	   - 객체마다 가지고 있는 필드/메서드
		 * 		 => 각 객체가 공유하지 않고 자기만 사용
		 * - static이 있는 필드와 메서드는 클래스명을 통해 호출 
		 *   - static 필드/메서드
		 * 	   - 클래스가 가지고 있는 필드/메서드
		 * 	     => 모든 객체가 공통으로 사용 가능 (공유)
		 * 
		 * - static이 있는 변수 : 클래스 변수 
		 * - static이 없는 변수 : 인스턴스 필드
		 * 
		 * - 클래스 필드는 언제 생성?
		 * 	 - 클래스가 메모리에 올라가면 생성
		 * - 인스턴스 필드는 언제 생성?
		 * 	 - new 연산자를 이용하여 객체를 만들 때 생성 
		 * 
		 * (교재 p.200)
		 * - 클래스 메서드에서 클래스 필드가 사용 가능? (O)
		 * - 클래스 메서드에서 인스턴스 필드가 사용 가능? (X)
		 * 	 => 클래스 메서드는 객체가 생성되기 전에 호출할 수 있음
		 *   => 객체가 생성되기 전이라면 인스턴스 필드가 없음
		 *   => 그래서 인스턴스 필드를 호출 할 수 있음
		 * - 인스턴스 메서드에서 인스턴스 필드가 사용 가능? (O)
		 * - 인스턴스 메서드에서 클래스 필드가 사용 가능? (O)
		 * 	 => 클래스 필드는 객체 생성없이도 사용 가능하기 때문에
		 * 	 => 객체를 생성한 후에는 사용이 가능
		 *
		 * */
		//HYCar를 이용하여 객체를 만들기전에 company를 호출할 수 있음
		System.out.println(HYCar.company);
		HYCar car1 = new HYCar("아반떼");
		car1.print();
		HYCar car2 = new HYCar("아이오닉5");
		car2.print();
		//제조사명(static 필드)이 현대에서 HY로 변경
		HYCar.company = "HY";
		car1.name = "아반떼 N";
		car1.print();
		car2.print();
		

	}

}
class HYCar{
	public static String company = "현대";
	public String name;
	
	public HYCar(String name) {
		this.name = name;
	}
	
	public void print() {
		System.out.println("제조사 : " + company);
		System.out.println("차명 : " + name);
		
	}
}