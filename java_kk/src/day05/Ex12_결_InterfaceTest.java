package day05;

import java.util.Arrays;

public class Ex12_InterfaceTest {

	public static void main(String[] args) {
		
		MyStudent [] stds = new MyStudent[5];
		
		/* Arrays.sort는 클래스에 compareTo라는 메서드를 이용하여 정렬하는데
		 * MyStudent에는 int compareTo(MyStudent my)라는 메서드가 없음
		 * => 그렇다고 직접 compareTo를 만들어도 안됨
		 * => Comparable 인터페이스에 있는 compareTo를 원함(매개변수의 다형성)
		 * */
		stds[0] = new MyStudent(1, 2, 1);
		stds[1] = new MyStudent(1, 1, 3);
		stds[2] = new MyStudent(1, 1, 2);
		stds[3] = new MyStudent(1, 1, 1);
		stds[4] = new MyStudent(2, 1, 1);
		Arrays.sort(stds);
		for(MyStudent std : stds) {
			std.print();
		}
	}
}
class MyStudent implements Comparable<MyStudent>{
	int grade, classNum, num;
	
	void print() {
		System.out.println(grade + "학년 " + classNum + "반 " + num + "번");
	}

	public MyStudent(int grade, int classNum, int num) {
		this.grade = grade;
		this.classNum = classNum;
		this.num = num;
	}
	public int compareTo(MyStudent my) {
		//학년이 다르면 학년을 기준으로 정렬
		if(this.grade != my.grade) {
			return my.grade - this.grade;
		}
		//학년이 같고 반이 다르면 반을 기준으로 정렬
		if(this.classNum != my.classNum) {
			return this.classNum - my.classNum;
		}
		//학년, 반이 같으면 번호를 기준으로 정렬
		return this.num - my.num;
	}
}



