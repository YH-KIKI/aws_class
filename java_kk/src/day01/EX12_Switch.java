package day01;

public class EX12_Switch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* switch문(p.98)
		 * - 조건문 중 하나
		 * - 모든 조건문을 처리할 수 없음
		 * - 특정 값과 비교하는 경우 사용
		 * 
		 * 문법
		 * switch(식 또는 변수){
		 * case 값1:    //변수(식) == 값1면 실행문1을 실행
		 * 		실행문1;
		 * 		break; //switch문을 빠져 나옴
		 * case 값2:    //변수가 값1과 다르고 변수 == 값2이면 실행문2를 실행
		 * 		실행문2;
		 * 		break; //switch문을 빠져 나옴
		 * 		...
		 * default:    //else : 변수가 값1, 값2가 아니면 실행문3을 실행
		 * 		실행문3;
		 * }
		 */
		
		//홀짝 예제를 switch문으로
		int num = 10;
		switch(num % 2) {
		//짝수
		case 0:
			System.out.println(num + "는 짝수");
			break;
		//홀수
		default:
		}

	}

}
