package day05;

public class EX08_Polymorphism {

	public static void main(String[] args) {
		/* 다형성 (교재 p.268)
		 * - 하나의 코드가 여러 자료형으로 구현되어 실행되는 것
		 * 
		 * 매개변수의 다형성
		 * - 메서드의 매개 변수로 자식 클래스들이 들어가야 하고, 메서드 안 내용이 같은 경우
		 *   부모 클래스를 매개변수로 저장하는 것
		 * 
		 * 객체의 다형성
		 * - 부모 클래스의 객체를 이용하여 다양한 자식 클래스의 객체를 관리할 수 있음
		 * 
		 * instanceof
		 * - 객체가 지정된 클래스로 형변환이 가능한지를 true 또는 false로 알려줌
		 * - 사용법
		 * 	 - 객체 instanceof ????????????
		 * 
		 * */
		
		//여러 도형을 그리고 그린 도형의 순서를 관리하기 위해 다음과 같이 했다면???
		Circle[] circles = new Circle[10];
		Rect[] rects = new Rect[10];
		
		//원1
		Circle c1 = new Circle (10, 10, 3);
		circles[0] = c1;
		
		//원2
		Circle c2 = new Circle (5, 5, 2);
		circles[1] = c2;
		//사각형1
		Rect r1 = new Rect(0, 0, 10, 10);
		rects[0] = r1;
		
		//원3
		Circle c3 = new Circle (20, 20, 5);
		circles[2] = c3;
		
		//r[] : r1
		//c[] : c1 c2 c3
		//위 배열들ㅇ르 이용해서 순서를 알려면 복잡(추가적인 작업이 필요)
		
		
		Shape[] s = new Shape[10];
		s[0] = c1;
		s[1] = c2;
		s[2] = r1;
		s[3] = c3;
		//s[] : c1 c2 c3
		//위 배열은 순서를 알기 쉬움
		
		for(Shape tmp : s) {
			if(tmp != null) {
				tmp.draw();
			}
		}
		System.out.println("====================");
		printShape(c1);
		printShape(r1);
		
		printInstanceOf(c1);
		printInstanceOf(r1);
		Shape s1 = new Shape();
		printInstanceOf(s1);
		printInstanceOf("abc");
	}
		
		public static void printInstanceOf(Object obj) {
			if(obj instanceof Circle) {
				System.out.println("객체는 Circle로 변환이 가능합니다.");
			}else if(obj instanceof Rect){
				System.out.println("객체는 Rect로 변환이 가능합니다.");				
			}else if(obj instanceof Shape){
				System.out.println("객체는 Shape로 변환이 가능합니다.");				
			}else {
				System.out.println("객체는 도형으로 변환이 불가능합니다.");
			}
		}
		
	
	//사각형이 있으면 사각형을 그리는 메서드
	/*public static void printShape(Rect r) {
		r.draw();
	}
	//원이 있으면 원을 그리는 메서드
	public static void printShape(Circle c) {
		c.draw();
	}
	*/
	//위에 주석 처리한 메서드 2개를 합쳐서 아래 메서드로 만들 수 있음 => 매개변수의 다형성
	public static void printShape(Shape s) {
		s.draw();
	}

}

/* 왜 Shape을 상속 받을 수 있을까요?
 * 이 파일엔 Shape 클래스가 없는데
 * 같은 패키지에 다른 파일안에 Shape 클래스가 선언되어 있고
 * Shape 클래스의 접근제어자가 디폴트이어서 같은 패키지인 이 파일에서 사용할 수 있음*/

class Circle extends Shape{
	int cx, cy;
	int r; //반지름
	
	public Circle(int cx, int cy, int r) {
		super("원");
		this.cx = cx;
		this.cy = cy;
		this.r = r;
	}
	
	@Override
	public void draw() {
		super.draw();
		System.out.println("중심점 : (" + cx + "," + cy + ")");
		System.out.println("반지름 : " + r);
;	}
	
	
}
