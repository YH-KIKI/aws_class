package day06;

import java.util.ArrayList;
import java.util.function.Predicate;

public class EX08_FunctionalInterface {

	public static void main(String[] args) {
		/* Consumer : 매개변수 O, 리턴 X, 주로 출력문 담당 
		 * Supplier : 매개변수 X, 리턴 O
		 * Function : 매개변수 O, 리턴 O, 매개변수타입 != 리턴타입
		 * Operator : 매개변수 O, 리턴 O, 매개변수타입 == 리턴타입
		 * Predicate : 매개변수O, 리턴 boolean
		 * */
		
		
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(1, 1, 1, "홍길동"));
		list.add(new Student(1, 1, 2, "가길동"));
		list.add(new Student(1, 2, 1, "고길동"));
		list.add(new Student(2, 1, 1, "임꺽정"));
		list.add(new Student(3, 1, 1, "홍가네"));
		
		//모든 학생 조회
		Predicate<Student> p = (student)->{
			return true;
		};
		printStudent(list, p);
		printStudent(list, s->true);
				
		//1학년 1반 학생을 조회
		System.out.println("---------------------------------------");
		//Predicate 인터페이스의 test 메서드를 구현하여 객체를 생성하여 매개변수로 넘겨줌
		printStudent(list, s->s.getGrade() == 1 && s.getClassNum() == 1);
				
		//1학년 학생을 조회
		System.out.println("---------------------------------------");
		printStudent(list, s->s.getGrade() == 1);
		
		//2학년 학생을 조회
		System.out.println("---------------------------------------");
		printStudent(list, s->s.getGrade() == 2);
		
		//1번 학생을 조회
		System.out.println("---------------------------------------");
		printStudent(list, s->s.getNum() == 1);
		

	}
	
	public static void printStudent (ArrayList<Student> list, Predicate<Student> p) {
		for(Student tmp : list) {
			if(p.test(tmp)) {
				System.out.println(tmp);
			}
		}
	}

}
