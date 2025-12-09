package day06;

import java.util.Objects;

public class EX01_Object {

	public static void main(String[] args) {
		/* Object p.360
		 * - 모든 클래스의 최상위 조상이 되는 클래스
		 * - Object에 있는 메서드를 사용 또는 오버라이딩 하여 사용할 수 있다.
		 * 
		 * equals(클래스명 객체)
		 * - 객체를 비교할 때 사용하는 메서드 
		 * - Object.equals는 주소를 비교
		 * 	 => 메서드 오버라이딩(재정의)해서 필드를 비교하여 같다와 같지 않다를 판별
		 * 
		 * to
		 * 
		 * */
		
		Car c1 = new Car("현대", "아반떼", 2022);
		Car c2 = new Car("현대", "아반떼", 2022);
		System.out.println("c1 == c2 : " + (c1 == c2));
		System.out.println("c1.euals(c2) : " + c1.equals(c2));
		System.out.println("c1 : " + c1);
		System.out.println("c2 : " + c2);		
	}
}

class Car{
	String company;
	String carName;
	int year;
	
	//기본 생성자
	public Car () {
		company = "회사명";
		carName = "차명";
		year = "2025";
	}

	public Car(String company, String carName, int year) {
		super();
		this.company = company;
		this.carName = carName;
		this.year = year;
	}

	@Override
	public boolean equals(Object obj) {
		//주소가 같은지 확인 => 같은 객체를 공유하는지 확인
		if (this == obj)
			return true;
		//비교 대상이 만들어지지 않으면
		if (obj == null)
			return false;
		//두 객체의 클래스가 다르면 (다른클래스면 비교하지 않는다, 만약 클래스가 달라도 비교하기 원하면 빼면 됨)
		if (getClass() != obj.getClass())
			return false;
		//두 객체의 클래스가 달라도 비교 가능한 경우 : 상속 관계인 두 클래스 형변환이 가능해야 함
		
		//비교 대상이 같은 크래스이기 때문에 클래스 형변환을 함		
		Car other = (Car) obj;
		//내가 원하는 값을 이용하여 같다 다르다를 판별
		return Objects.equals(carName, other.carName) && year == other.year;
		//차명이 있고, 차명과 비교대상 차명이 같고, 연식이 같으면참 아니면 거짓 
		// return carName != null && carName.equals(other.carName) && year == other.year;
	}

	@Override
	public String toString() {
		return " [회사 : " + company + " , 차명 : " + carName + " + carName + ", year=" + year + "]";
	}
	
	
}
