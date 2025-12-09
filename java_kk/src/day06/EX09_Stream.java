package day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class EX09_Stream {

	public static void main(String[] args) {
		/* 스트림 p.493
		 * - 왜 사용?
		 * 	 - 배열이나 컬렉션 인터페이스의 정보들을 일관된 방법으로 처리할 수 있어서
		 * 	 - 중간 처리메서드와 최종 처리 메서드를 제공해서 여러 연산을 한번에 할 수 있음
		 * - filter
		 * 	 - 중간 처리 메서드
		 * 	 - 객체들 중 원하는 객체들만 고를 때 사용
		 * - map
		 * 	 - 중간 처리 메서드
		 *   - 객체를 이용하여 다른 형태의 결과를 얻을 때 사용
		 *   - mapToInt : 객체를 이용하여 정수를 반환할 때 사용
		 * - count, average
		 * 	 - 최종 처리 메서드
		 * 	 - count : 개수
		 * 	 - average : 평균. 정수로 변환할 경우 
		 * - forEach
		 * 	 - 최종 처리 메서드
		 * 	 - 스트림에서 하나씩 꺼내서 반복문 실행
		 */
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(1, 1, 1, "홍길동"));
		list.add(new Student(1, 1, 2, "가길동"));
		list.add(new Student(1, 2, 1, "고길동"));
		list.add(new Student(2, 1, 1, "임꺽정"));
		list.add(new Student(3, 1, 1, "홍가네"));
			
		//List를 stream으로 변환
		Stream<Student> stream = list.stream();
		//1학년 1반 1번 학생을 출력
		stream.filter(s->s.getGrade() == 1)		//1학년 학생을 찾음
			  .filter(s->s.getClassNum() == 1)  //1반 학생을 찾음
			  .filter(s->s.getNum() == 1) 		//1번 학생을 찾음
			  .forEach(s->System.out.println(s));
		//등록된 학생 수
		stream = list.stream();
		System.out.println("1학년 학생 수 : " + stream.filter(s->s.getGrade() == 1).count());
		
		//등록된 학생들의 평균 학년
		stream = list.stream();
		double avg = stream.mapToInt(Student::getGrade)
				.average()
				.orElse(0); //평균을 낼 수 없으면 0을 반환
		System.out.println("평균 학년 : " + avg);
		
		//배열을 스트림으로
		String [] list2 = {"딸기", "바나나", "오렌지"};
		Stream<String> stream2 = Arrays.stream(list2);
		stream2.forEach(f->System.out.println(f));
	}
}
