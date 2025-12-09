package day06;

import java.util.Objects;

public class Student implements Comparable<Student>{
	
	private int grade, classNum, num;
	private String name;
	
	public Student(int grade, int classNum, int num, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.name = name;
	}

	@Override
	public String toString() {
		return grade + " 학년 " + classNum + " 반 " + num  + " 번 " + name;
	}

	
	/*- 컬렉션 프레임워크에서 객체를 추가, 삭제할 때
	 * 해당 객체가 있는지를 Objects.equals(o1,o2)를 이용하여 확인*/ 
	
	
	@Override
	public int hashCode() {
		return Objects.hash(classNum, grade, num);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return classNum == other.classNum && grade == other.grade && num == other.num;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getClassNum() {
		return classNum;
	}

	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(Student s2) {
		if(this.getGrade() != s2.getGrade()) {
			return this.getGrade() - s2.getGrade();
		}
		if(this.getClassNum() != s2.getClassNum()) {
			return this.getClassNum() - s2.getClassNum();
		}
		return this.getNum() - s2.getNum();
	}

}


