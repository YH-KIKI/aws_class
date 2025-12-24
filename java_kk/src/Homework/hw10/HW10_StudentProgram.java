package Homework.hw10;

import java.util.Scanner;

public class HW10_StudentProgram {

	public static void main(String[] args) {
		/* 기본 게임 방식은 HW08과 동일한데 기록 관리를 추가
		 * 관리할 기록은 횟수와 입력한 이니셜
		 *  메뉴
		 *  1. 플레이
		 *  2. 기록 확인
		 *  3. 종료
		 *  메뉴 선택 : 
		 *  //HW08에 했던 야구 게임이 진행
		 *  //정답을 맞추면 맞춘 횟수를 출력
		 *  정답입니다.
		 *  시도횟수는 4회
		 *  5등안에 들었습니다. 
		 *  이니셜을 남겨주세요 : JIK
		 *  
		 *  메뉴
		 *  1. 플레이
		 *  2. 기록 확인
		 *  3. 종료
		 *  메뉴 선택 : 2
		 *  1. JIK - 4회
		 *  메뉴
		 *  1. 플레이
		 *  2. 기록 확인
		 *  3. 종료
		 *  메뉴 선택 : 3
		 * */
		Scanner scan = new Scanner(System.in); // 안써도된다고? 나중에 코드 확인해보기
		
		int menu = 0;
		final int EXIT = 9;
		// 학생 정보를 관리하는 리스트 만들기
		List<Student> students = new ArrayList<Student>();
		
		
		do {
			//메뉴 출력
			printMenu();
			menu = scan.nextInt();
			switch(menu) {
			case 1:	break;
				addStudent(students);
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			case 6: break;
			case 7: break;
			case 8: break;
			case 9: break;
				
			}
		}while(menu != EXIT);
	}
	
	//학생 정보 입력받은 후 학생 추가
	private static void addStudent(List<Student> list) {
		//학년, 반, 번호를 scan을 이용하여 입력 받음
		
		//학년, 반, 번호를 이용하여 학생 객체를 생성
		
		//리스트에 학생 객체를 추가
		
		//학생을 추가했습니다. 라고 콘솔에 출력
		
	}
	// 프로그램 종료
	private static void exit(){
		System.out.println("===============");
		System.out.println("프로그램을 종료합니다");
		System.out.println("===============");
	}
	
	private static void printMenu() {
		System.out.println("메뉴");
		System.out.println("1. 학생 등록");
		System.out.println("2. 학생 삭제");
		System.out.println("3. 학생 조회");
		System.out.println("4. 과목 등록");
		System.out.println("5. 과목 삭제");
		System.out.println("6. 과목 전체 조회");
		System.out.println("7. 학생 성적 추가");
		System.out.println("8. 학생 성적 삭제");
		System.out.println("9. 종료");
		System.out.println("메뉴 선택 : ");
		
	}

}	