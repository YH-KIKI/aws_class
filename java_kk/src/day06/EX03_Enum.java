package day06;

public class EX03_Enum {

	public static void main(String[] args) {
		/* enum p.285
		 * - 열거형
		 * - 왜 사용?
		 * 	 - 비슷한 유형의 리터럴을 모아서 사용하기 위해
		 * 	  => 상수 여러개를 따로 선언해서 사용하기 보다 하나로 묶어서 사용하기 위해
		 * - 사용 방법
		 *  enum {이름1, 이름2, 이름3, ...}
		 *  - 이름1은 0, 이름2는 1
		 * */
		
		SEASON season = SEASON.SPRING;
		switch(season) {
		case SPRING: System.out.println("봄입니다."); break;
		case SUMMER: System.out.println("여름입니다."); break;
		case FALL: System.out.println("가을입니다."); break;
		case WINTER: System.out.println("겨울입니다."); break;	
		}
	}

}

enum SEASON{
	SPRING,SUMMER, FALL, WINTER
}
