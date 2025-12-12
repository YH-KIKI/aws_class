package exam;

//클래스 선언하기
class Student implements Comparable<Student>{

	int grade, classNum, num, score;
	String name;
	
	public int compareTo(Student o) {
		if(this.getGrade() != o.getGrade()) {
			return this.getGrade() - o.getGrade();
		}
		if(this.getClassNum() != o.getClassNum()) {
			return this.getClassNum() - o.getClassNum();
		}
		return this.getNum() - o.getNum();
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Student(int grade, int classNum, int num, int score, String name) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.score = score;	
		this.name = name;
	}
	


	public Student(int grade, int classNum, int num, int score) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
		this.score = score;
		
	}
	
	
	@Override
	public String toString() {
		return grade + "학년 " + classNum + "반 " + num + "번 " + name + " - 점수: " + score;
	}


	//학년, 반, 번호 비교
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return this.grade == other.grade &&
		           this.classNum == other.classNum &&
		           this.num == other.num;
		}


	
	
}