package day06;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EX06_List {

	public static void main(String[] args) {
		/* List를 이용하여 1~5사이의 정수를 입력받아 중복이 안되면 저장하고 
		 * 중복이 되면 저장하지 않는 예제를 작성하세요
		 * 과정1. 앞 예제를 활용하여 정수를 5번 입력받는 코드 구현
		 * 과정2. 과정1 코드를 고민하여 중보이 안될때만 추가되도록 구현 
		 * 
		 * */
		ArrayList<Integer> list = new ArrayList<Integer>();
		int num;		
		Scanner scan = new Scanner(System.in);
		
		/*
		num = scan.nextInt();
		list.add(num);
		
		num = scan.nextInt();
		list.add(num);
		
		num = scan.nextInt();
		list.add(num);
		
		num = scan.nextInt();
		list.add(num);
		
		num = scan.nextInt();
		list.add(num);
		*/
		/*
		for(int i = 1; i <= 5; i++) {
			System.out.print("1~5사이의 정수 입력 : ");
			num = scan.nextInt();
			//추가하기 전에 종복 검사
			//리스트에 num가 없으면
			if(num > 5 || num < 1) {
				System.out.println("범위를 벗어난 수를 입력했습니다.");
				continue;
			}
			if(!list.contains(num)) {
				list.add(num);//추가			
				System.out.println(num + "가 추가 됐습니다.");
			}else {
				System.out.println(num + "는 중복된 수입니다.");
			}
		}
		*/
		
		int min = 1, max = 5, size = 5;
		list = EX06_List.createRandomList(min, max, size);
		System.out.println(list);
	}
	
	/* 기능 : min~max 사이의 중복되지 않은 size개의 랜덤한 정수를 리스트에 담아 반환하는 메서드 
	 * 매개변수 : min~max, size => int min, int max, int size
	 * 리턴타입 : 랜덤한 정수가 담긴 리스트 => ArrayList<Integer>
	 * 메서드명 : createRandomList
	 * */
	public static ArrayList<Integer> createRandomList(int min, int max, int size){
		
		//최소가 최대보다 크면 바꿈
		if(min > max){
			int tmp = min;
			min = max;
			max = tmp;
		}
		//랜덤으로 생성할 수 있는 수보다 크기가 더 큰 경우 => 무한루프
		if(max - min + 1 < size) {
			return null;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		while(list.size() < size) {	
			Random random = new Random();
			int num = random.nextInt(min, max+1);
			//추가하기 전에 중복검사
			//리스트에 num이 없으면
			if(num > max || num < min) {
				continue;
			}
			if(!list.contains(num)) {
				list.add(num);
			}
		}
		return list;
		
	}
	void test() {}
}
