package Homework.hw10;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectScore implements Serializable{
	
	private static final long serialVersionUID = 123L;
	
	//다음 필드를 선언하세요.
	//과목 정보, 성적
	//Subject 객체를 사용하기 위해
	private Subject subject;
	private double score;
	
	
	//toString을 오버라이딩 하세요
	//1학년 1학기 국어 90점 형태로 
	
	@Override
	public String toString() {
		return subject.toString() + " " + score + "점"; 
		//Subject의 toSring을 불러오고 그뒤에 필요한걸 추가
		//혹은 subject.get.grade + "학년" 형식도 OK
	}


	
	//학년, 학기, 과목명, 성적을 이용한 생성자를 추가하세요
	public SubjectScore(int grade, int semester, String name, double score) {
		/*
		 subject = new Subject();
		 subject.setGrade(grade);
		 subject.setSemester(semester);
		 subject.setName(name);
		 을 한줄로 하면 this.subject = new Subject(grade, semester, name);
		 */
		subject = new Subject(grade, semester, name); //Subject의 객체를 호출해서 새 Subject생성자를 만듦
		this.score = score;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubjectScore other = (SubjectScore) obj;
		return Objects.equals(subject, other.subject);
	}

}
