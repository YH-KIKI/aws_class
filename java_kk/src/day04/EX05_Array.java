package day04;

public class EX05_Array {

	public static void main(String[] args) {
		/* 배열
		 * - 왜 사용?
		 * 	 - 여러 정보를 관리하는데 반복문을 이용할 수 있기 때문에
		 * - 언제 사용?
		 * 	 - 학생수, 나이, 태어난해 => 배열을 안씀
		 * 	 - 학생1의 국어성적, 학생2의 국어성적, 학생3의 국어성적 => 배열을 씀 
		 * 	 - 학생1의 수학성적, 학생1의 국어성적, 학생1의 영어성적 => 배열을 안씀
		 * 	 - 같은 자료형이고, 같은 의미를 가지는 정보를 관리할 때 사용
		 * 
		 * - 배열 선언 방법
		 * 	 - 자료형 [] 배열명;
		 *   - 자료형 배열명 [];
		 * - 배열 생성 방법 => 실제 배열을 만들어서 연결
		 * 	 - 배열명 = new 자료형[크기];
		 * 	 - 크기는 정수만 올 수 있고, 변수, 상수, 리터럴 모두 올 수 있음
		 *   - 생성된 배열의 각 값은 기본값으로 초기화
		 *   - 정수 : 0, 실수 : 0.0, 논리 : false, 문자 : '\u0000'
		 * - 배열 사용 방법
		 * 	 - 배열명[번지]를 이용하여 변수처럼 쓸 수 있음
		 * 	 - 배열값 변경
		 * 	 - 배열명[번지] = 값;
		 * 	 - 배열값 가져오기
		 *   - 배열명[번지]
		 *   - 번지는 0 ~ 크기 -1 까지 사용 가능
		 *   - 범위를 벗어난 번지를 사용하면 예외가 발생
		 * - 배열의 크기
		 * 	 - 배열명.length
		 * - 배열의 복사
		 * 	 1. 반복문을 이용하여 직접 구현
		 * 	 2. System.arraycopy(src, srcPos, dest, destPos, length)
		 * 		- src : 복사할 배열
		 * 		- srcPos : 복사할 시작 번지
		 * 		- dest : 붙여넣을 배열
		 * 		- destPos : 붙여넣을 시작 번지
		 * 		- length : 복사할 개수
		 * 	 - 배열 복사 시 = 을 이용하면 안된다
		 * 	   배열명2 = 배열명1; //복사가 아니라 공유 
		 * */
		
		int [] arr1 = new int[3];
		//0번지값 가져와서 콘솔에 출력
		System.out.println(arr1[0]);
		//0번지에 1을 저장
		arr1[0] = 1;
		//확인용
		System.out.println(arr1[0]);
		
		//length를 이용한 배열 전체 출력(한줄로)
		// i < arr1. length는 배열 크기보다 작은 가장 큰 정수는 크기 -1
		for(int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
		
		//배열 복사1 : 직접 복사
		int [] arr2 = new int[arr1.length]; //arr1 크기만큼 배열생성
		for(int i = 0; i< arr1.length; i++) {
			arr2[i] = arr1[i];
		}
		
		//복사 확인
		for(int i = 0; i < arr2.length; i++) {
			System.out.print(arr2[i] + " ");
		}
		System.out.println();
		
		//배열 복사2 : System.arraycopy
		int [] arr3 = new int[arr1.length];
		//arr1 배열 전체를 복사
		System.arraycopy(arr1, 0, arr3, 0, arr1.length);
		
		//복사 확인
		for(int i = 0; i < arr3.length; i++) {
			System.out.print(arr3[i] + " ");
		}
		System.out.println();
		
		//배열 복사2 : System.arraycopy
		//arr의 0번지와 1번지를 복사해서 arr4의 1번지와 2번지에 붙여넣음
		int [] arr4 = new int[arr1.length];
		System.arraycopy(arr1, 0, arr4, 1, 2);
		

		//복사 확인
		for(int i = 0; i < arr3.length; i++) {
			System.out.print(arr3[i] + " ");
		}
		System.out.println();
		
		/* 향상된 for문
		 * - 배열 전체를 탐색할 때 사용
		 * - 중간에 몇개를 건너뛸 수 없음
		 * - 언제 사용?
		 * 	 - 배열이나 컬렉션프레임워크에서 전체 탐색을 할 때 사용
		 * - 왜 사용?
		 * 	 - 전체 탐색할 때 코드가 간결
		 * - 배열 전체를 탐색할 때 사용
		 * - 중간에 몇개를 건너뛸 수 없음
		 * - 문법 
		 * 	for(자료형 변수명 : 배열명){
		 * 		실행문;
		 *  }
		 * */
		
		int [] arr5 = { 1, 2, 3, 4, 5};
		for(int num :arr5) {  //자료형 변수명 : 배열명
			System.out.println(num + " ");
		}
		System.out.println();
		
		
		

	}

}
