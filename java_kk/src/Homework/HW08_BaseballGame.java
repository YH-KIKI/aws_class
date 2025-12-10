package Homework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class HW08_BaseballGame {

	public static void main(String[] args) {
		/* 1~9사이의 중복되지 않은 3개의 수를 생성해서 맞추는 게임
		 * 규칙
		 * S : 숫자가 있고 위치가 같음
		 * B : 숫자가 있고 위치가 다름
		 * O : 일치하는 숫자가 하나도 업음
		 * 
		 * 예시 
		 * 랜덤수 : 1 5 4
		 * 입력 : 1 2 3
		 * 결과 : 1S
		 * 
		 * 입력 : 4 5 6
		 * 결과 : 1S 1B
		 * 
		 * 입력 : 7 8 9 
		 * 결과 : O (아웃)
		 * 
		 * 입력 : 1 4 5 
		 * 결과 : 1S 2B
		 * 
		 * 입력 : 1 5 4
		 * 결과 : 정답입니다. 
		 * 
		 * */
		
		ArrayList<Integer> com = new ArrayList<Integer>();
		ArrayList<Integer> user = new ArrayList<Integer>();
		
		int min = 1, max = 9, size = 3;
		int strike = 0; int ball = 0;
		
		// 랜덤수 생성
		try {
			generateRandomList(com, min, max, size);
			// System.out.println(com);	//답이 출력되니 주석
		}catch (Exception e) {
			System.err.println(e.getMessage());
			System.out.println("[프로그램 종료]");
			return;
		}
		
		
		// 반복문
		do {
			System.out.println("입력 : ");
			// 숫자 입력
			if(!inputInteger(user, min, max, 3)) {
				System.out.println("중복된 수를 입력했습니다.");
				continue;
			}
			// 스트라이크 개수 확인
			strike = getStrike(com, user);
			// 볼 개수 확인
			ball = getball(com, user);
			// 결과 출력
			printResult(strike, ball);	
		} while(strike < 3);
		System.out.println("정답입니다.");
				
	}
	
	public static int play() {
		return 0;
	}
	
	
	private static int getball(ArrayList<Integer> com, ArrayList<Integer> user) {
		int total = 0;
		for(Integer tmp : com) {
			if(user.contains(tmp)) {
				total++;
			}
		}
		return total - getStrike(com, user);
	}
	
	private static int getStrike(ArrayList<Integer> com, ArrayList<Integer> user) {
		int strike = 0;
		for(int i = 0; i < com.size(); i++) {
			if(com.get(i) == user.get(i)) {
				strike++;
			}
		}
		return strike;
	}
	
		
	public static boolean inputInteger(ArrayList<Integer> list, int min, int max, int size) {
		if(list == null) {
			throw new RuntimeException("리스트가 비었습니다.");
		}
		if(min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		if(max - min + 1 < size) {
			throw new RuntimeException("중복되지 않게 만들 수 없습니다.");
		}
		list.clear();
		Scanner scan = new Scanner(System.in);
		
		//중복 포함해서 3개 입력
		while(list.size() < size ) {
			list.add(scan.nextInt());
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		//입력받은 수를 set에 저장
		set.addAll(list);
		//중복이 안되면 ture, 중복이 되면 false
		return list.size() == set.size();
		
	}
	//list에 min ~ max사이의 중복되지 않은 수 size개를 저장하는 메서드
	public static void generateRandomList(ArrayList<Integer>list,
			int min, int max, int size) {
		if(list == null) {
			throw new RuntimeException("리스트가 비었습니다.");
		}
		if(min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		if(max - min + 1 < size) {
			throw new RuntimeException("중복되지 않게 만들 수 없습니다.");
		}
		while(list.size() < size) {
			int r = random(min, max);
			//중복되지 않으면 추가
			if(!list.contains(r)) {
				list.add(r);
			}
		}
		
	}
	
	
	public static int random(int min, int max) {
		if(min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		Random r = new Random();
		return r.nextInt(min, max + 1);
	}
	
	//결과 출력
	public static void printResult(int strike, int ball) {
		if(strike != 0) {
			System.out.println(strike + "S");
		}
		if(ball != 0) {
			System.out.println(ball + "B");
		}
		if(strike == 0 && ball == 0) {
			System.out.println("O");
		}
		System.out.println();
	}

}
