package day06;

import java.util.ArrayList;
import java.util.Collections;

public class EX10_Sort {

	public static void main(String[] args) {
		/* 리스트에서 정렬하기
		 * - Collections.sort(리스트)
		 * 	 - 리스트는 Comparator 인터페이스를 구현한 구현 클래스이어야 함
		 * - 리스트.sort(객체)
		 * 	 - 객체는 Comparator 인터페이스를 구현한 구현 클래스이어야 함.
		 */
		
		
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(2, 1, 1, "임꺽정"));
		list.add(new Student(1, 1, 1, "홍길동"));
		list.add(new Student(1, 1, 2, "가길동"));	
		list.add(new Student(3, 1, 1, "홍가네"));
		list.add(new Student(1, 2, 1, "고길동"));
		
	
		/*
		list.sort((s1, s2) -> {
			if(s1.getGrade() != s2.getGrade()) {
				return s1.getGrade() - s2.getGrade();
			}
			if(s1.getClassNum() != s2.getClassNum()) {
				return s1.getClassNum() - s2.getClassNum();		
			}
			return s1.getNum() - s2.getNum();
		});
		*/
		
		
		Collections.sort(list);
		System.out.println(list);
		
	

	}

}
