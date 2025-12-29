package kr.hi.boot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor	//기본 생성자
@AllArgsConstructor	//모든 필드를 필요로 하는 생성자
public class Human {
	String name;
	int age;
}
