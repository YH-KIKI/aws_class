package exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class exam_01 {

	public static void main(String[] args) {
		
		//리스트
		StudentScoreManager ss = new StudentScoreManagerImp();
		ss.add(new Student(2, 1, 5, 95, "김수현"));
		ss.add(new Student(2, 1, 12, 88, "최민수"));
		ss.add(new Student(1, 3, 2, 77, "이영희"));
		
		
		Scanner scan = new Scanner(System.in);
		

		//전체출력
		ss.printAll();
		
		try {
			//특정학생 정보 입력 후 검색 출력
			System.out.println();
			System.out.println("==============특정 학생 정보 입력 후 출력 ==================");
			System.out.print("학년 입력: ");
			int num1 = scan.nextInt();
			System.out.print("반 입력: ");
			int num2 = scan.nextInt();
			System.out.print("번호 입력: ");
			int num3 = scan.nextInt();
			System.out.println(ss.find(num1, num2, num3));

			//특정학생 정보 입력 후 삭제
			System.out.println();
			System.out.println("==============특정 학생 정보 삭제 후 출력 ==================");
			System.out.print("학년 입력: ");
			int num4 = scan.nextInt();
			System.out.print("반 입력: ");
			int num5 = scan.nextInt();
			System.out.print("번호 입력: ");
			int num6 = scan.nextInt();
			
			ss.remove(num4, num5, num6);
			System.out.print("삭제완료");
			
			//기준 정렬 후 다시 전체 출력
			System.out.println();
			System.out.println("================점수 오름차순 정렬후 다시 출력==================");
			ss.sortByScore();
			ss.printAll();
		
			}catch(InputMismatchException e) {
				System.err.println("정수를 입력하세요.");
				scan.nextLine();
			}
		}
}


interface StudentScoreManager{
	//있는 기록이면 업데이트 후 false, 없는 기록이면 추가후 true
	boolean add(Student student);
	Student remove(int grade, int classNum, int num);
	Student find(int grade, int classNum, int num);	
	void printAll();    //전체조회
	void sortByScore(); //정렬
}




//성적 기록 관리
class StudentScoreManagerImp implements StudentScoreManager {

	ArrayList<Student> list = new ArrayList<Student>();

	
	@Override
	public boolean add(Student student) {
		if(student == null) {
			return false;
		}
		if(!list.contains(student)) {
			return list.add(student);
		}
		int index = list.indexOf(student);
		list.set(index, student);
		
		return false;
	}


	@Override
	public Student remove(int grade, int classNum, int num) {
	    int index = list.indexOf(new Student(grade, classNum, num, 0));
	    if(index < 0) return null; // 없는 이름이면 null 반환

	    return list.remove(index);
	}

	@Override
	public Student find(int grade, int classNum, int num) {
		int index = list.indexOf(new Student(grade, classNum, num, 0));
		if(index < 0) {
		return null;
		}
		return list.get(index);
	}

	@Override
	public void printAll() {
		for (Student stu : list) {
			System.out.println(stu);
		}
	}


	@Override
	public void sortByScore() {
		Collections.sort(list);
	}
	
}




	
