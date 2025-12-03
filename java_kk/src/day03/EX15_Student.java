package day03;

import java.util.Scanner;

public class EX15_Student {

	final static int MAX_COUNT = 3;
	public static void main(String[] args) {
		/* 프로그램 기능
		 * 1. 학생 추가
		 * 2. 학생 성적 수정
		 * 3. 학생 삭제
		 * 4. 종료
		 * */
		Scanner scan = new Scanner(System.in);
		char menu;
		MyStudent stds[] = new MyStudent[MAX_COUNT];
		int count = 0; //현재 저장된 학생 수 => 학생을 배열에 추가할 때 사용
		//
		int grade, classNum, num;
		String name;
		
		do { printMenu();
			//메뉴 출력
			//메뉴 입력
			menu = scan.next().charAt(0);
			
			//선택한 메뉴에 맞는 기능 실행
			switch(menu) {
			case '1':
				//학생 배열과 학생수를 알려주면 학생정보를 추가하여
				//추가된 학생수를 새로 저장
				count = addStudent(stds, count, scan);
				break;				
				//학생의 학년, 반, 번호, 이름을 입력 => 메서드로 이동
//				System.out.println("학년 반 번호 이름순으로 입력하세요.: ");
//				grade = scan.nextInt();
//				classNum = scan.nextInt();
//				num = scan.nextInt();
//				name = scan.next();		
//				//객체를 생성해서 배열에 저장
//				MyStudent std = new MyStudent(grade, classNum, num, name);
//				stds[count] = std;
//				count++; //추가된 학생수 증가
//				break;
			case '2':
				System.out.println("학생 성적 수정 기능 구현 예정");
				break;
			case '3':
				System.out.println("학생 정보 삭제 기능 구현 예정");
				break;
			case '4':
				System.out.println("프로그램을 종료합니다.");
				break;
			default:
				System.out.println("잘못된 메뉴입니다.");
				
			}
			
		}while(menu != '4');
		
	}

	public static void printMenu() {

		System.out.println("메뉴");
		System.out.println("1. 학생 추가");
		System.out.println("2. 학생 성적 수정");
		System.out.println("3. 학생 삭제");
		System.out.println("4. 종료");
		System.out.println("메뉴 입력 : ");
		
	}
	/* 기능 : 학생 정보를 입력 받아 학생 배열에 추가하는 메서드
	 * 매개변수 : 학생 배열, 현재 저장된 학생 수, 스캐너
	 * 		=> MyStudent []stds, int count, Scanner scan
	 * 리턴타입 : 저장된 학생 수 => int
	 * 메서드 : addStudent
	 * 
	 * */
	public static int addStudent(MyStudent []std, int count, Scanner scan) {
		System.out.println("학년 반 번호 이름순으로 입력하세요.: ");
		int grade = scan.nextInt();
		int classNum = scan.nextInt();
		int num = scan.nextInt();
		String name = scan.next();		
		//객체를 생성해서 배열에 저장
		MyStudent std = new MyStudent(grade, classNum, num, name);
		stds[count] = std;
		count++; //추가된 학생수 증가
		return 0;
		
	}

}

class MyStudent{
	int korScore, engScore, mathScore; //필드
	int grade, classNum, num;		   //필드
	String name;					   //필드
	
	//학생 정보를 콘솔에 출력하는 기능
	void print() {		//메서드
		System.out.println(grade + "학년 " + classNum + "반 " + num + "번 " + name);
		
	}
	//기본 생성자
	MyStudent(){}			//생성자
	//생성자 오버로딩
	MyStudent(int grade1, int classNum1, int num1, String name1){
		grade = grade1;
		classNum = classNum1;
		num = num1;
		name = name1;
	}
	
}