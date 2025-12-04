package day04;

public class EX06_ArraySort {

	public static void main(String[] args) {
		/* 배열 정렬
		 * - 정렬 방법은 매우 많음 => 알고리즘에서 배움
		 * - 설명이 쉬운 버블 정렬
		 * 	 - 옆에 수와 크기를 비교하여 위치를 바꿈
		 *   - 효율이 가장 안좋음
		 * 
		 * */
		
		//두 정수의 값을 바꾸는 코드를 작성하세요.
		int num1 = 10, num2 = 20;
		int tmp = 0;
		tmp = num1;
		num1 = num2;
		num2 = tmp;
		
		
		System.out.println("num1 = " + num1);
		System.out.println("num2 = " + num2);
		
		/* 1 3 5 7 9 2 4 6 8 10(원본) 내림차순으로 정렬
		 * 3 5 7 9 2 4 6 8 10 1 : 한 사이클 후 결과. 1이 정렬 (1이 3과 비교하여 오른쪽 이동..  1이 5랑 비교하여 오른쪽 이동... 쭉 계속해서 최종 결과)
		 * 5 7 9 3 4 6 2 10 1 2 : 두 사이클 후 결과, 1, 2가 정렬
		 * */
		
		int arr [] = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
		
		for(int i = 0; i <arr.length - 1; i++) {  //마지막은 정렬 안해도 돼서 -1
			for(int j = 0; j < arr.length -1; j++) {
				if(arr[j] < arr[j+1]) {
					int tmp1 = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp1;		
				}
			}
			//정렬된 배열 확인
			for(int num : arr) {
				System.out.println(num + " ");
			}
			System.out.println();
			
		}
		
	}

}
