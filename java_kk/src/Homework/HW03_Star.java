package Homework;

public class HW03_Star {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*샘플 코드를 참고하여 아래와 같이 출력 되도록 코드를 작성하세요.*/
		
		int row = 5, col = 5;
	
		/* *****
		 * *****
		 * *****
		 * *****
		 * *****
		 */
		
		// 별 5개 5줄 출력하는 코드 
		for(int i = 1; i <= row; i++) {
			for(int j = 1; j <= col; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		
		/* *		i=1, *=1
		 * **		i=2, *=2
		 * ***		i=3, *=3
		 * ****		i=4, *=4
		 * *****	i=5, *=5
		 * 				* = i개 
		 */				
		
		for(int i = 1; i <= row; i++) {
			for(int j = 1; j <= i; j++) {	//* = i라 위에 col을 i로
				System.out.print("*");
			}
			System.out.println("");
		}
		
		
		
		/*     *	i=1, 공백=4, *=1
		 *    **	i=2, 공백=3, *=2
		 *   ***	i=3, 공백=2, *=3
		 *  ****	i=4, 공백=1, *=4
		 * *****	i=5, 공백=0, *=5
		 * 			공백의 개수 + *의 개수 = 5
		 * 			공백 = 5-i, * = i
		 */
		 
		for(int i = 1; i <= row-i; i++) {
			for(int j = 1; j <= col; j++) {
				System.out.print(" ");
			}
			System.out.println("*");
		}
		
		 
		 
		 
		
		/*     *
		 *    ***
		 *   *****
		 *  *******
		 * *********
		 */
		
		

	}

}
