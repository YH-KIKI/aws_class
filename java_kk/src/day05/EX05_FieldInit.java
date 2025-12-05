package day05;

public class EX05_FieldInit {
	
	public static void main(String[] args) {
		/* 필드 초기화 순서
		 * 1. 각 자료형의 기본값으로 초기화
		 * 2. 명시적 초기화
		 * 	 - 변수 선언 후 바로 초기값을 넣은 경우
		 * 3. 초기화 블록
		 * 	 - 클래스 안 {}로 되어 있는 블록에서 초기화가 진행
		 * 4. 생성자
		 * */
		Test t = new Test();
		t.print();
		
	}
	
}

class Test{
	//1. 기본값으로 초기화 0으로
	int num1;
	//2. 기본값으로 초기화 뒀다가 명시적 초기화로 10으로 덮어씀
	int num2 = 10;
	int num3 = 10;
	int num4 = 10;
	
	{		//3. 기본값 => 명시적초기화 => 초기화 블록에서 20으로 덮어씀
		num3 = 20;
		num4 = 20;		
	}
	public Test() {
		//4. 기본값 => 명시적초기화 => 초기화 블록 => 생성자에서 30으로 덮어씀
		num4 =30;
	}
	public void print() {
		System.out.println("num1 : " + num1);
		System.out.println("num2 : " + num2);
		System.out.println("num3 : " + num3);
		System.out.println("num4 : " + num4);
		
	}

}
