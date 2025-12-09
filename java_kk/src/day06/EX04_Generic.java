package day06;

public class EX04_Generic {

	public static void main(String[] args) {
		/* 제네릭
		 * - 필드나 메서드의 타입을 고정으로 하지 않고 객체를 생성할 때 타입을 지정하는 방식
		 * - 객체 생성시 타입은 기본형 자료형을 쓸 수 없다.
		 * 	 => 기본 자료형과 비슷한 Wrapper클래스를 제공
		 * 	 => int => Integer, char => Charater, double => Double
		 * 제네릭 클래스 선언
		 * class 클래스명<T>{
		 * 		T 필드명;
		 * 
		 * 		void set필드명(T 변수명){
		 * 			this.필드명 = 필드명;
		 * 		}
		 * 		T get필드명(){
		 * 			return 필드명;
		 * 		}
		 * }
		 * - 제네릭 클래스 객체 생성
		 * 클래스명<타입> 객체명 = new 생성자<타입>();
		 * 
		 * 
		 * */ 
		Array1<Integer> arr = new Array1<Integer>(5);
		arr.set(0, 1);
		arr.set(1, 2);
		arr.set(2, 10);
		
		for(int i = 0; i < 3; i++) {
			System.out.println(arr.get(i));
		}
		Array1<String> arr2 = new Array1<String>(5);
		arr2.set(0, "안녕");
		arr2.set(1, "hi");
		arr2.set(2, "사과");
		for(int i = 0; i < 3; i++) {
			System.out.println(arr2.get(i));
		}

	}

}

//배열을 관리하는 클래스
class Array1<T>{
	private T [] arr;
	
	public Array1(int size) {
		arr = (T[]) new Object[size];
		// arr = new T[size]; // new T[size]를 허용하지 않음
	}
	public T get(int index) {
		return arr[index];
	}
	
	public void set(int index, T data) {
		arr[index] = data;
	}
	
}