package day08;

public class EX02_Exception {

	public static void main(String[] args) {
		/* 예외 객체가 제공하는 메서드
		 * - printStackTrace()
		 * 	 - 예외가 발생한 경로를 출력하는 메서드
		 * 	 - 예외가 발생한 메서드부터 해당 메서드를 호출한 메서드들이 출력
		 * - getMessage()
		 * 	 - 예외 메세지를 문자열로 반환	
		 * */ 
		try {
			test2(null);
			test();
		}catch(Exception e) {
			e.printStackTrace();	//예외 경로를 출력하기 때문에 찾기가 편함 
			System.out.println(e.getMessage());
		}
	}
	public static void test() {
		System.out.println(1/0);
	}
	public static void test2(Object object) {
		if(object == null) {
			throw new RuntimeException("객체가 null입니다.");
		}
	}

}
