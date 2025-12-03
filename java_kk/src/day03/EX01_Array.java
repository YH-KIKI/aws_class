package day03;

public class EX01_Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* 배열
		 * - 같은 형태의 자료를 여러개 관리할 때 사용
		 * - 예 : 학생들의 국어 성적을 관리, 학생 정보를 관리
		 * - 연속된 공간에 배열을 생성 => 탐색이 빠름
		 * - 왜?
		 * 	 - 반복문을 사용할 수 있기 때문
		 * - 선언 방법
		 * 	1. 자료형 [] 배열명 = new 자료형 [크기];
		 * 	2. 자료형 배열명 [] = new 자료형 [크기];
		 * 	3. 자료형 [] 배열명 = {값1, 값2, 값3};
		 *  4. 자료형 [] 배열명 = new int[]{값1, 값2, 값3};
		 *  1과 2는 약간의 차이가 있음
		 *  
		 * - 배열 사용
		 * 	 - 배열명[번지]를 이용하여 값을 가져오거나 수정
		 * 	 - 번지는 0번지부터 크기-1번지까지 가능
		 * 	 - 잘못된 번지를 입력하면 예외가 발생
		 * 
		 * - 배열의 길이
		 * 	 - 배열명.length
		 *   
		 * */
		
		// []과 배열명 사이에는 공백이 있어도 되고 없어도 OK
		
		//nums1 : 배열, nums2 : 변수
		int [] arr1 = new int[3], /*int []*/nums1; 
		int arr2 [] = new int[3], /*int*/nums2;
		// nums1 = 10; // 배열 nums1에 10(주소)로 저장. 에러 발생O
		nums2 = 10; // 정수 변수 nums2에 10을 저장. 에러 발생 X
		
		//3,4번 
		int [] arr3 = {1,2,3};
		int [] arr4 = new int[] {1,2,3};
		
		// arr3 = {4,5,6}; //배열 선언 이후에는 이 방법으로 재할당이 안됨
		arr4 = new int[] {4,5,6}; //배열 선언 이후에도 이 방법으로 재할당이 가능
		System.out.println(arr4[0]);
		// System.out.println(arr4[3]); // 실행시 예외가 발생
			
		}
		
		
		
	}
