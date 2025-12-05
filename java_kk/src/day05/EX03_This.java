package day05;

public class EX03_This {

	public static void main(String[] args) {
		/* This 교재 p.176
		 *  - 나 자신을 가리키는 객체
		 *  - 클래스내에서 메서드나 생성자에서 사용
		 *  - 왜 사용?
		 * 	  - 메서드, 생성자의 매개변수와 필드의 이름이 같은 경우 구분을 해야하기 때문에
		 * 		필드 앞에 this를 붙임
		 * this() 생성자 p.182
		 * - 생성자에서 다른 생성자를 이용하여 초기화 할 때 사용
		 * - 생성자에서 첫번째 줄에 this()를 이용
		 * */
	}
}
class Point2{
	private int x, y;
	
	public void print() {
		System.out.println(x + "," + y);
	}
	
	/* 
	 * 생성자 만드는 방법
	 * - 클래스를 클릭하여 커서 활성화
	 * - 우클릭 > Source > Generate Constructor using Fields를 클릭 후 
	 * 	 생성자의 매개변수로 들어갈 필드를 선택하여 생성
	 */
	public Point2(int x, int y) {
		this.x = x;
		this.y = y;			
	}
	
	public Point2() {
		//x = 1 //이 코드가 여기에 있으면 this()에 에러 발생
		this(1, 1); //Point2(int x, int y)를 이용하여 초기화
	}
	/* getter / setter 만드는 방법
	 * - 클래스를 클릭하여 커서 활성화
	 * - 우클릭 > Source > Generate Getters/Setters 를 클릭 후 
	 * 	 생성자의 매개변수로 들어갈 필드를 선택하여 생성
	 */

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
