package day02;

public class EX08_NestedForTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* num가 소수이면 num을 출력하고 아니면 출력하지 않는 코드를 작성하세요.
		 * 소수는 약수가 2개
		 * 약수는 나누어 떨어지는 수
		 * 8의 약수 : 1, 2, 4, 8
		 * */
		
		int num = 2;
		int count = 0;
		for(int i = 1; i <= num; i++) {
			if (num % i == 0) {
				count++;
				//System.out.println(count);
			}
		}
		
		
		//예전 예제 : 2는 소수, 지금 예제 : 2
		//예전 예제 : 4는 소수, 지금 예제 : 4
		if(count == 2) {
			System.out.println(num);
			
			
		}
	
		//100이하의 소수를 출력하는 예제
		//num을 2부터 100까지 1씩 증가 
		
			//num가 소수이면 num을 출력
		
		for(num = 2; num <= 100; num++) {
			count = 0;		
			for(int i = 1; i <= num; i++) {
				if (num % i == 0) {
					count++;
			}
		}
		if(count == 2) {System.out.print(num + " ");
		}

	}
    }
}
