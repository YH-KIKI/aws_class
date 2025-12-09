package day07;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EX09_Lombok {

	public static void main(String[] args) {
		Car c1 = new Car("아반떼", "검은색");
		System.out.println(c1);

	}

}

@Data	//getter, setter추가, toString, equals, hashCode 오버라이
@AllArgsConstructor //모든 필드를 매개변수로 갖는 생성자 추가
@NoArgsConstructor  //기본 생성자 추가
class Car{
	String name;
	String color;
	
}