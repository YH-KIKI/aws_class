package Homework.hw10;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HW10_StudentProgram {

	private static Scanner scan = new Scanner(System.in);
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		/* 학생의 성적을 관리하는 프로그램을 구현하세요.
		 * - 파일에 저장. 파일에서 불러오기 
		 * 메뉴
		 * 1. 학생 등록
		 *  - 학년, 반, 번호, 이름을 입력받아 등록
		 *  - 학년, 반, 번호가 같은 학생은 등록 못함
		 * 2. 학생 삭제
		 *  - 학년, 반, 번호를 입력받아 삭제
		 * 3. 학생 조회
		 *  - 학년, 반, 번호를 입력받아 조회
		 * 4. 과목 등록
		 *  - 학년, 학기, 과목명을 입력받아 등록
		 *  - 같은 학년, 학기, 과목명을 가진 과목은 등록 못함
		 * 5. 과목 삭제
		 *  - 학년, 학기, 과목명을 입력받아 삭제
		 * 6. 과목 전체 조회
		 * 7. 학생 성적 추가
		 *  - 학생의 학년, 반, 번호를 입력받아 있으면 과목 학년, 학기, 과목명, 성적을 입력받아 추가
		 * 8. 학생 성적 삭제
		 *  - 학생의 학년, 반, 번호를 입력받아 있으면 과목 학년, 학기, 과목명을 입력받아 삭제 
		 * 9. 프로그램 종료 
		 * */
		int menu = 0;
		final int EXIT = 9;
		//학생 정보를 관리하는 리스트 
		List<Student> students = new ArrayList<Student>();
		//과목 리스트 
		List<Subject> subjects = new ArrayList<Subject>();
		String studentsFileName = "student.txt";
		String subjectsFileName = "subject.txt";
		
		try {
			students = (ArrayList<Student>)load(studentsFileName); 
		}catch(Exception e) {
			println("학생 정보 불러오기 실패");
			students = new ArrayList<Student>();
		}
	
		try {
			subjects = (ArrayList<Subject>)load(subjectsFileName);
		}catch(Exception e) {
			println("과목 정보 불러오기 실패");
			subjects = new ArrayList<Subject>();
		}
		
		// students = load(studentsFileName);
		// subjects = load(subjectsFileName);
		
		do {
			//메뉴 출력
			printMenu();
			menu = scan.nextInt();
			switch(menu) {
			case 1:	
				addStudent2(students);
				break;
			case 2: 
				removeStudent(students);
				break;
			case 3: 
				//searchStudent(students);
				searchStudent2(students);
				break;
			case 4:
				addSubject2(subjects);
				break;
			case 5: 
				removeSubject(subjects);
				break;
			case 6: 
				printSubjects(subjects);
				break;
			case 7: 
				addSubjectScore(students, subjects);
				break;
			case 8:
				removeSubjectScore(students, subjects);
				break;
			case EXIT: 
				exit();
				save(studentsFileName, students);
				save(subjectsFileName, subjects);
				break;
			default:
				
			}
		} while(menu != EXIT);
		
	}
		private static Object load(String fileName) throws Exception{
			try(ObjectInputStream ois =
					new ObjectInputStream(new FileInputStream(fileName))){
				return ois.readObject();
			}catch(Exception e) {
				throw e;
			}
		}
	
	private static void save(String fileName, Object obj) {
		try(ObjectOutputStream oos = 
				new ObjectOutputStream(new FileOutputStream(fileName))){
			oos.writeObject(obj);
			oos.flush();
		}catch(Exception e) {
			println("저장에 실패했습니다.");
		}
	}
	
	private static void removeSubjectScore(List<Student> students, List<Subject> subjects) {
		// 성적을 삭제할 학생의 학년, 반, 번호를 입력
		Student student = inputStudent(false);
		// 일치하는 학생이 없으면 안내문 출력후 종료
		int index = students.indexOf(student);
		
		if(index < 0) {
			println("일치하는 학생이 없습니다.");
			return;
		}
		
		// 있으면 
		// 과목 정보를 입력
		Subject subject = inputSubject();
		
		// 과목 목록에 과목 정보가 없으면 안내문 출력 후 종료
		if(!subjects.contains(subject)) {
			println("등록되지 않은 과목입니다.");
			return;
		}
		
		// 학생 정보에서 과목 정보를 주면서 성적 목록에서 제거하고,
		// 제거 여부를 알려달라고 함
		Student selectedStudent = students.get(index);
		
		// 제거 했으면 제거 안내문 출력
		if(selectedStudent.removeSubjectScore(subject)) {
			println("성적을 삭제했습니다.");
		}
		
		// 실패했으면 제거 실패 안내문 출력
		else {
			println("등록되지 않은 성적입니다.");
		}
		
	}
	
	private static void addSubjectScore(List<Student> students, List<Subject> subjects) {
		
		//성적을 추가할 학생의 학년, 반, 번호를 입력 
		Student student = inputStudent(false);
		
		//학생이 없으면 일치하는 학생이 없습니다 출력 후 종료
		//List의 indexOf 활용 
		int index = students.indexOf(student);
		if(index < 0) {
			println("일치하는 학생이 없습니다.");
			return;
		}
		
		//과목 성적 정보를 입력(학년, 학기, 과목명, 성적)
		Subject subject = inputSubject();
		System.out.print("성적 : ");
		int score = scan.nextInt();
		
		//기존 코드에 추가작업 없이 과목 성적을 추가
		//0학년 0학기로 한 이유는 subject.getGrade로 학년을 넣어주면 되는데
		//그러면 코드가 길어져서 간단히 하기위해 0학년으로 대체 
		SubjectScore subjectScore = 
				new SubjectScore(0, 0, "", score);
		subjectScore.setSubject(subject);
		
		//아래 코드는 생성자를 추가해서 위 코드 대신 사용할 수 있음 
		//SubjectScore subjectScore = new SubjectScore(subject, score);
		
		//없는 과목이면 등록되지 않은 과목입니다를 출력후 종료 
		if(!subjects.contains(subject)) {
			println("등록되지 않은 과목입니다.");
			return;
		}
		
		//학생 정보에 성적을 추가해서 성공하면
		//성적을 추가했습니다.
		//입력한 학생 정보를 학생 목록에서 가져옴 
		//student; => 입력한 학년, 반, 번호는 있지만 이름과 성적들이 없음 
		Student selectedStudent = students.get(index);
		
		//강사가 할 방법
		//학생정보에게 입력한 성적 정보를 주고 추가하라고 요청 후 성공 여부를 알려 달라고 함
		boolean isAdd = selectedStudent.addSubjectScore(subjectScore);
		
		if(isAdd) {
			println("성적을 추가했습니다.");
		}
		
		//실패하면 성적을 수정했습니다를 출력
		//실패 => 새로운 추가 실패 => 기존 성적을 수정 
		else {
			println("성적을 수정했습니다.");
		}
		
		
	}

	//문자열 앞뒤로 절취선 추가하는 메서드
	private static void println(String str) {
		System.out.println("====================");
		System.out.println(str);
		System.out.println("====================");
	}
	
	private static void printSubjects(List<Subject> subjects) {
		
		//등록된 과목이 없으면 알림 출력(등록된 과목이 없습니다.)
		//if(subjects.size() == 0)
		if(subjects.isEmpty()) {
			println("등록된 과목이 없습니다.");
			return;
		}
		System.out.println("====================");
		//있으면 과목들을 한줄에 하나씩 출력 
		for( Subject subject : subjects) {
			System.out.println(subject);
		}
		System.out.println("====================");
	}

	private static void removeSubject(List<Subject> subjects) {
		
		//학년, 학기, 과목명을 입력
		Subject subject = inputSubject();
		//일치하는 정보가 있으면 삭제후 알림(과목이 삭제되었습니다.)
		if(subjects.remove(subject)) {
			println("과목을 삭제했습니다.");
			return;
		}
		//없으면 알림(일치하는 과목이 없습니다.)
		println("일치하는 과목이 없습니다.");
	}

	//학년, 학기, 과목명을 입력받아 객체를 반환하는 메서드
	private static Subject inputSubject() {
		//학년, 학기, 과목명을 입력
		System.out.println("====================");
		System.out.println("과목 정보를 입력하세요.");
		System.out.print("학년 : ");
		int grade = scan.nextInt();
		System.out.print("학기 : ");
		int semester = scan.nextInt();
		System.out.print("과목명 : ");
		String name = scan.next();
		System.out.println("====================");
		return new Subject(grade, semester, name);
	}
	
	private static Subject getSubjectBySubjects(List<Subject> subjects
			, Subject subject) {
		int index = subjects.indexOf(subject);
		if(index < 0) {
			return null;
		}
		return subjects.get(index);
	}
	
	//중복 과목 체크 함. Subject클래스에 equals를 오버라이딩 해서. 
	private static void addSubject2(List<Subject> subjects) {
		
		Subject subject = inputSubject();
		
		//subject 중복체크
		if(getSubjectBySubjects(subjects, subject) != null) {
			println("이미 등록된 과목입니다.");
			return;
		}
		
		//중복이 아니면 과목을 과목 목록에 추가
		subjects.add(subject);
		
		// 알림 문구 출력 
		println("과목을 추가했습니다.");
	}
	
	//중복 과목 체크 안함. Subject클래스에 equals를 오버라이딩 하기 전 
	private static void addSubject(List<Subject> subjects) {
		
		Subject subject = inputSubject();
		
		//과목을 과목 목록에 추가
		subjects.add(subject);
		
		// 알림 문구 출력 
		println("과목을 추가했습니다.");

	}
	//Student클래스의 equals를 활용
	private static void removeStudent(List<Student> students) {
		//학생 정보를 입력받아 학생 객체를 생성
		Student student = inputStudent(false);
		
		//학생 정보를 정보를 이용하여 삭제하는데 학생 정보가 가 없으면 
		//일치하는 학생이 없습니다. 안내문구 출력후 종료
		if(!students.remove(student)) {
			println("일치하는 학생이 없습니다.");
			return;
		}
		//있으면 학생을 삭제했습니다를 출력 
		println("학생 정보를 삭제했습니다.");
	}

	//학생을 조회하는데 equals를 이용 안함
	private static void searchStudent(List<Student> list) {
		//학년, 반, 번호를 입력받아 학생 객체를 생성. inputStudent 활용
		Student student = inputStudent(false);
		
		boolean isFind = false;
		//반복문을 통해서
		for(Student tmp : list) {
			//tmp의 학년, 반, 번호가 student의 학년,반,번호와 같으면 
			if( tmp.getGrade() == student.getGrade() 
				&& tmp.getClassNum() == student.getClassNum() 
				&& tmp.getNum() == student.getNum()) {
				//학생 정보 tmp를 출력
				tmp.printScore();
				isFind = true;
			}
		}
		//일치하는 학생이 없는 경우
		if(!isFind) {
			println("일치하는 학생이 없습니다.");
		}
		
	}
	
	//searchStudent는 equals를 이용하지 않음.
	//searchStudent2는 Student 클래스의 equals를 오버라이딩하여 이용
	private static void searchStudent2(List<Student> list) {
		//학년, 반, 번호를 입력받아 학생 객체를 생성. inputStudent 활용
		Student student = inputStudent(false);
		
		int index = list.indexOf(student);
		//일치하는 학생이 없는 경우
		if(index < 0) {
			println("일치하는 학생이 없습니다.");
			return;
		}
		//있으면 학생 정보(성적을 포함한)를 출력
		//list.get(index).printScore();
		Student tmp = list.get(index);
		tmp.printScore();
		
	}

	private static Student inputStudent(boolean isName) {
		//학년, 반, 번호, 이름을 scan를 이용하여 입력 받음 
		System.out.println("학생 정보를 입력하세요.");
		System.out.print("학년 : ");
		int grade = scan.nextInt();
		System.out.print("반  : ");
		int classNum = scan.nextInt();
		System.out.print("번호 : ");
		int num = scan.nextInt();

		String name = null;
		//이름까지 입력을 받아야 하면 
		if(isName) {
			System.out.print("이름 : ");
			name = scan.next();
			
		}
		return new Student(grade, classNum, num, name);
	}
	
	//학생 정보 입력받은 후 학생 추가 
	private static void addStudent(List<Student> list) {
		//학년, 반, 번호, 이름을 scan를 이용하여 입력 받음 
//		System.out.println("학생 정보를 입력하세요.");
//		System.out.print("학년 : ");
//		int grade = scan.nextInt();
//		System.out.print("반  : ");
//		int classNum = scan.nextInt();
//		System.out.print("번호 : ");
//		int num = scan.nextInt();
//		System.out.print("이름 : ");
//		String name = scan.next();
		
		//학년, 반, 번호, 이름을 이용하여 학생 객체를 생성
		//new를 통해 인스턴스를 생성한 후 객체에 저장 
		//A a = new ~~~
		Student stduent = inputStudent(true);
		
		//리스트에 학생 객체를 추가 
		//list.add를 이용
		list.add(stduent);
		
		//학생을 추가했습니다라고 콘솔에 출력
		println("학생을 추가했습니다.");
	}
	//Student 클래스의 equals를 이용, 중복 학생 처리
	private static void addStudent2(List<Student> list) {
		//학년, 반, 번호, 이름을 scan를 이용하여 입력 받음 
		Student stduent = inputStudent(true);
		
		//등록된 학생인지 확인
		//학생 정보가 학생 리스트에서 몇번지에 있는지 확인
		int index = list.indexOf(stduent);
		//있는 학생이면 이미 등록된 학생입니다라고 출력 후 종료
		if(index >= 0) {
			println("이미 등록된 학생입니다.");
			return;
		}
		
		//리스트에 학생 객체를 추가 
		list.add(stduent);
		
		//학생을 추가했습니다라고 콘솔에 출력
		println("학생을 추가했습니다.");
	}
	
	//프로그램 종료
	private static void exit() {
		//프로그램을 종료합니다라고 출력
		println("프로그램을 종료합니다.");
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
		System.out.print("메뉴 선택 : ");
		
	}

}