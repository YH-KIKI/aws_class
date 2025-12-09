package day05;

public class EX07_ClassCasting {

	public static void main(String[] args) {
		/* 클래스 형변환
		 * - 조상 클래스와 자손 클래스 관계일 때 클래스 형변환이 가능
		 * - 업캐스팅 
		 *   - 자동으로 변환이 가능
		 *   - 자식 클래스의 객체를 부모 클래스로 변환하는 경우
		 * - 다운 캐스팅
		 * 	 - 강제로 변환
		 * 	 - 부모 클래스의 객체를 자식 클래스로 변환하는 경우  
		 *   - 조건부로 가능
		 * */
		
		//업캐스팅(자식 객채 => 부모 객체가 사용
		Rect r = new Rect(0, 0, 10, 5);
		r.draw();
		Shape p = r; //업 캐스팅
		p.draw();
		
		//다운캐스팅(부모 객체 => 자식 객체 사용)
		//1. 문제가 되는 상황
		Shape p1 = new Shape("도형");
		p1.draw();
		//Rect r1 = (Rect)p1;  //다운캐스팅이 정상적으로 일어나지 않아 예외 발생
		//r1.draw();
		
		//2. 정상적으로 돌아가는 상황
		Rect r2 = new Rect(5, 5, 10, 10);
		Shape p2 = r2;		 //업캐스팅을 먼저 함
		Rect r3 = (Rect)p2;; //이후 다운 캐스팅을 함
		r3.draw();

	}

}
