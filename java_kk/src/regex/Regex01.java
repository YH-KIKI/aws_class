package regex;

import java.util.regex.Pattern;

public class Regex01 {
	
	public static void main(String[] args) {
		/*Regex
		 * - 정규표현식 (Regular Expression)
		 * - 문자열에 패턴을 확인할 때 사용
		 * 	- 아이디는 6~13자이고, 영문, 숫자만 가능하며
		 * 	  특수문자는 !@#$만 가능하다
		 *  
		 * - 메타 문자
		 * 	- 정규표현식에 사용하는 문자
		 *  - 특정한 규칙을 표현할 때 사용
		 *  
		 * - 메타 문자 종류
		 * 	- \d 한개의 숫자
		 *    - \d\d\d : 3자리 숫자
		 *  - \s : 한개의 공백 (\t, ' ', '\n'
		 *  - \w : 한개의 영문 (대소문자) 또는 숫자
		 *  - [문자들] : 한개의 문자로 문자들에 있는 문자만 가능 
		 *    - [abc] : 한개의 문자가 a이거나 b이거나 c
		 *    - [a-z] : 한개의 문자가 소문자
		 *    - [0-9] : 한개의 문자가 숫자(\d와 같음)
		 *    - [a-zA-Z0-9] : 한개의 문자가 영문이거나 숫자(\w와 같음)
		 *    
		 * - 패턴A+
		 * 	 - 패턴A가 1개이상
		 * 	 - \w+ : 영문이나 숫자가 1개 이상
		 * 	 - [abc]+ : a나 b나 c가 1개 이상
		 * - 패턴A?
		 * 	 - 패턴이 0개 또는 1개
		 *   - https? : http이거나 https
		 * - 패턴A*
		 * 	 - 패턴이 0개 이상
		 * 	 - [abc]* : ''이거나 a,b,c,aa,ab,ac, ...
		 * - ^패턴A
		 * 	 - ^가 제일 앞인 경우
		 * 	 - 문자열이 패턴A로 시작
		 * 	 - ^010 : 010으로 시작
		 * - 중간에 [^문자들]
		 * 	 - 문자들이 아닌 문자
		 * 	 - [^abc] : a도 아니고 b도 아니고 c도 아닌 문자
		 * - 패턴A$
		 * 	 - $가 제일 끝인 경우
		 * 	 - 문자열이 패턴A로 끝남
		 * - 패턴A{min, max}
		 * 	 - 패턴A가 최소 min번, 최대 max번 반복
		 * 	 - 패턴A{min} : 패턴A가 min번 반복
		 * 	 - 패턴A{,max} : 패턴A가 0번이상 max번 이하
		 * 	 - 패턴A{min, } : 패턴A가 min번 이상
		 * - (패턴A)
		 * 	 - 하나의 패턴안에 서브 패턴(패턴A)를 넣을 때 사용
		 * 	 - (패턴A | 패턴B) : 패턴이 패턴A이거나 패턴B
		 * - . / [.]
		 * 	 - 한글자 / 문자 . 
		 */
		/* 자바에서 정규표현식 테스트하기 
		 * Pattern.matches(정규표현식문자열, 문자열A)
		 *  - 문자열A가 정규표현식에 맞는지 확인해서 true/false를 반환
		 * */
		
		// 아이디가 영문자 6~13자이고, 영문, 숫자만 가능할 때
		// 주어진 아이디가 아이디 패턴에 맞는지 확인하세요.
		String id = "a123456789123";
		String idRegex = "^\\w{6,13}$";  //영문숫자 6~13자 필터링
		
		System.out.println(id + "는 아이디 형식에 맞습니까?"
			+ Pattern.matches(idRegex, id));
		
		// 아이디가 영문자 6~13자이고, 영문, 숫자만 가능하고,
		// 아이디는 영문으로 시작할 때
		// 주어진 아이디가 아이디 패턴에 맞는지 확인하세요.
		
		String id2 = "a123456789123";
		String idRegex2 = "^[a-zA-Z]\\w{5,12}$";  // 앞글자 포함을 빼고 숫자 계산을 해줘야 함 그래서 6,13이 아닌 5,12
		
		System.out.println(id2 + "는 아이디 형식에 맞습니까?"
				+ Pattern.matches(idRegex2, id2));
		
		//전화번호 패턴이 맞는지 확인
		// 000-0000-0000, 00-0000-0000 형태이며, 전부 숫자
		
		String phone = "010-1234-5678";
		String phoneRegex = "^\\d{2,3}-\\d{4}-\\d{4}$";  // 앞글자 포함을 빼고 숫자 계산을 해줘야 함 그래서 6,13이 아닌 5,12
		
		System.out.println(phone + "는 전화번호 형식에 맞습니까?"
				+ Pattern.matches(phoneRegex, phone));
		
		
		
		
		String phone2 = "0212345678";
		String phoneRegex2 = "^\\d{2,3}((-\\d{4}){2}|\\d{8})$";  
		
		System.out.println(phone2 + "는 전화번호 형식에 맞습니까?"
				+ Pattern.matches(phoneRegex2, phone2));
		
		//주어진 url주소가 올바른지 확인
		//url은 https:// 또는 http://로 시작하며
		//naver.com 또는 xxx.co.kr처럼 되어야 함
		//확장자(.com이나 .co.kr)은 1글자 이상으로 구성
		//도메인 부분은 최소 1글자 이상이어야 함
		//도메인과 확장자는 영문 또는 숫자이어야 함
		//http://a.c (o)
		//http://a.b.c (o)
		//http://abc123 (x)
		//abc123.com (x)
		
		String url = "http://ab.b.c";
		String urlRegex = "^https?://\\w+([.]\\w+)+$"; 
		
		System.out.println(url + "는 URL 형식에 맞습니까?"
				+ Pattern.matches(urlRegex, url));
	}

}
