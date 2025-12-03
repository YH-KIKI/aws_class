package day03;

public class EX04_ARRAYCopy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/* 배열 복사하기 
		 * 1. 반복문을 이용하여 직접 구현
		 * 2. System.arraycopy(src, scrPos, dest, destPos, length)를 이용 p.218
		 * 	 - src : 복사할 배열
		 * 	 - srcPos : 복사할 배열의 시작 위치, 배열에서 몇번지에서 복사할건지
		 * 	 - dest : 복사해서 붙여넣을 배열
		 *   - destPos : 붙여넣을 시작 위치
		 *   - length : 복사할 개수
		 * 
		 * */
		
		int arr[] = {1, 2, 3};
		//기본 자료형 변수 복사
		int num1 = 10;
		int num2 = num1;
		
		
		//기본 자료형은 =을 통해 복사를 한 후 원본을 바꿔도 복사본에 영향이 없다.
		System.out.println("num1 : " + num1);
		System.out.println("num2 : " + num2);
		num1 = 20;
		System.out.println("num1을 20으로 수정");
		System.out.println("num1 : " + num1);
		System.out.println("num2 : " + num2);
		
		
		int arr1[] = {1, 2, 3};
		int arr2[] = arr1;
		
		//참조형 변수는 =를 통해 복사를 하면 원본이 바뀌면 복사본도 함께 변경(공유한 값이 변경)
		//참조형 변수는 =을 통해 주소를 복사해오기 때문에 같은 배열을 공유
		System.out.println("arr1[0] : " + arr1[0]);
		System.out.println("arr2[1] : " + arr2[0]);
		arr1[0] = 20;
		System.out.println("arr1[0]를 20으로 수정");
		System.out.println("arr1[0] : " + arr1[0]);
		System.out.println("arr2[1] : " + arr2[0]);
		
		arr1 = new int[] {1,2,3};
		//반복문으로 직접 복사
		int arr3[] = new int[arr1.length];
		for(int i = 0; i < arr1.length; i++) {
			arr3[i] = arr1[i];
		}
		System.out.println("================================");
		System.out.println("arr1[0] : " + arr1[0]);
		System.out.println("arr3[1] : " + arr3[0]);
		arr1[0] = 20;
		System.out.println("arr1[0]를 20으로 수정");
		System.out.println("arr1[0] : " + arr1[0]);
		System.out.println("arr3[1] : " + arr3[0]);
		
		
		int arr4[] = new int[arr1.length];
		System.arraycopy(arr1, 0, arr4, 0, arr1.length);
		
		System.out.println("================================");
		System.out.println("arr1[0] : " + arr1[0]);
		System.out.println("arr4[1] : " + arr4[0]);
		arr1[0] = 20;
		System.out.println("arr1[0]를 20으로 수정");
		System.out.println("arr1[0] : " + arr1[0]);
		System.out.println("arr4[1] : " + arr4[0]);
		
		
	}

}
