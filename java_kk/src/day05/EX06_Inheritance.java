package day05;

public class EX06_Inheritance {

	public static void main(String[] args) {
		/* 클래스 상속 (교재 p.242)
		 * - 클래스를 물려받는 것
		 * - 부모 클래스의 필드, 메서드를 물려 받는 것
		 * - 왜 사용? 
		 * 	 - 상속을 통해 코드의 중복을 제거
		 *   - 다형성을 통해 여러 객체를 관리할 수 있음
		 * - 상속은 is a 관계
		 * 	 - A는 B이다가 성립하면 A:자식 클래스, B:부모 클래스
		 * 	 - 스마트폰은 폰이다(O)
		 *     - 스마트폰 : 자식, 폰 : 부모
		 *   - 스마트폰은 카메라이다(X)
		 *     - 상속이 불가능
		 * - 포함은 has a 관계
		 * 	 - 클래스의 필드를 다른 클래스의 객체가 오는 경우
		 * 	 - A는 B를 가지고 있다가 성립하면 A클래스의 필드로 B를 선언
		 * 	 - 스마트폰은 카메라를 가지고 있다(O)
		 * 	   - 스마트폰 : 클래스, 카메라 : 필드
		 * - 상속하는 방법
		 * 	 - 부모클래스가 선언되어 있음
		 * class 자식클래스명 extends 부모클래스명{
		 * 		//추가할 필드
		 * 		//추가할 메서드
		 *		//생성자 추가 
		 * }
		 * - 상속을 받으면 부모 클래스의 모든 필드와 메서드를 사용할 수 있다 ? (X)
		 *    => private로 된 필드/메서드는 사용할 수 없다.
		 *    
		 * - super 객체 (교재 p.257)
		 * 	 - 부모를 가리키는 객체
		 *   - super.메서드명() : 부모 클래스의 메서드를 호출
		 * - super() 생성자
		 * 	 - 부모 클래스의 생성자를 호출
		 * 	 - 생성자 첫번째에 있어야 함
		 * - 메서드 오버라이딩(Overriding)
		 * 	 - 부모 클래스의 메서드를 재정의 하는 것
		 *   - 부모 클래스의 매서드와 리턴타입, 메서드명, 매개변수가 동일해야 함 
		 *   - 접근제어자 범위가 같거나 넓어져야 함
		 * - 부모 클래스는 2개이상 올 수 있다 (X)
		 * 	 => 여러 클래스에게 한번에 상속 받을 수 없다.
		 * */
		
		Rect r = new Rect(0, 0, 10, 10);
		r.draw();

	}

}

//도형 클래스
class Shape{
	
	private String name; //도형 이름
	
	public void draw() {
		System.out.println(name + "을 그립니다.");	
	}
	
	public Shape() {}
	public Shape(String name) {
		this.name = name;
	}

}

//직사각형
class Rect extends Shape{
	int left;	//좌상점의 x좌표
	int top;	//좌상점의 y좌표
	int right;	//우하점의 x좌표
	int bottom;	//우하점의 y좌표
	
	public Rect(int left, int top, int right, int bottom) {
		super("사각형");
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	@Override //Override 어노테이션은 메서드가 오버라이딩인지 문법 체크해줌
	public void draw() {
		//사각형을 그립니다를 출력 후 왼쪽 위의 점과 오른쪽 아래점을 출력
		super.draw();
		System.out.println("좌상점 : (" + left + "," + top + ")");
		System.out.println("우하점 : (" + right + "," + bottom + ")");
	}
	
}
