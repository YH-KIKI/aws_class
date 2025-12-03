package day03;

public class EX06_MultiArray {

	public static void main(String[] args) {
		/* 2차원 배열 p.219
		 * - 행렬을 생각하면 됨
		 * - 행과 열이 있는 배열
		 * - 자료형 배열명 [][] = new 자료형 [행크기][열크기]
		 * */
		
		int arr[][] = new int [8][9];
		//구구단 각 단의 결과를 배열에 저장
		//arr.length : 행의 길이
		for(int i = 0; i < arr.length; i++) { 
			//i단의 각 결과를 0번지부터 순서대로 저장
			//arr[i].length는 한 행의 열의 개수
			for(int j = 0; j < arr[i].length; j++) {
				arr[i][j] = (i + 2) * (j + 1);
			}
		}
		
		//결과 확인
		for(int []row : arr) {
			for(int col : row) {
				System.out.print(col + " ");
			}
			System.out.println();
		}
		
		//행마다 열의 개수가 다른 예제
		int arr2[][] = new int [3][];
		arr2[0] = new int[3]; //첫 행은 열이 3개
		arr2[1] = new int[5]; //두번째 행은 열이 5개
		arr2[2] = new int[2]; //세번째 행은 열이 2개
		
		for(int row[] : arr2) {
			for(int col : row) {
				System.out.print(col + " ");
			}
			System.out.println();
		}

	}

}
