package day04;

public class Ex03_결_ConditionalStatement {

	public static void main(String[] args) {
		/* 조건문
		 * - 언제 사용?
		 *   - 상황에 따라 결과가 달라질 때 사용
		 *   - 성적에 따라 학점이 달라질 때 
		 *   - A이면 B이다로 표현 가능하면 조건문을 사용
		 *   - A : 조건식, B : 실행문
		 * if문
		 * - 문법
		 *   if(조건식){
		 *     실행문;
		 *   }
		 * - 적용
		 *   - A이면 B하세요
		 *   if(A){
		 *     B;
		 *   }
		 * - 문법2
		 *   if(조건식){
		 *     실행문1;
		 *   }
		 *   else{
		 *     실행문2;
		 *   }
		 * - 적용
		 *   - A이면 B하고 아니면 C하세요.
		 *   if(A){
		 *     B;
		 *   }
		 *   else{
		 *     C;
		 *   }
		 * 문법3
		 *   if(조건식1){
		 *     실행문1;
		 *   }
		 *   else if(조건식2){
		 *     실행문2;
		 *   }
		 *   else{
		 *     실행문3;
		 *   }
		 * 적용
		 *   - A이면 B하고, A가 아니고 C이면 D하고, 아니면(A아니고 C도 아니면) F하세요.
		 *   if(A){
		 *     B;
		 *   }
		 *   else if(C){
		 *     D;
		 *   }
		 *   else{
		 *     F;
		 *   }
		 * */
		
		int 이수학점 = 151;
		final int 졸업학점 = 150;
		boolean 졸업작품여부 = false;
		
		/* 이수학점이 졸업학점 미만이면 졸업 실패
		 * 아니면(졸업학점 이상이면) 졸업작품을 했으면 졸업
		 * 아니면 졸업 실패라고 출력하도록 조건문을 작성
		 * */
		if(이수학점 < 졸업학점) {
			System.out.println("졸업 실패");
		}
		else if(졸업작품여부) {
			System.out.println("졸업");
		}
		else {
			System.out.println("졸업 실패");
		}
		
		/* switch문
		 * - 언제 사용?
		 *   - if문에서 각 조건문이 변수 == 값형태로 되어 있는데 변수가 고정인 경우 사용
		 * - 왜 사용? 
		 *   - if문보다 구조가 간결  
		 * - 문법
		 * 	switch(변수){
		 * 	case 값1:	if(변수 == 값1)
		 * 		실행문1;
		 * 		break;
		 * 	case 값2:	else if(변수 == 값2)
		 * 		실행문2;
		 * 		break;
		 * 	default:	else
		 * 		실행문3;
		 * 	}
		 * - 주의사항
		 *   - 값에는 상수(final로 선언한 값)와 리터럴(1,2,3,'a')만 올 수 있음
		 *   - 값은 문자(''), 정수, 문자열("")만 가능
		 *   - case에 break가 없으면 실행문 실행 후 다음 case에 실행문으로 이동
		 *     => case에서는 break를 만나야 switch문을 빠져 나감
		 *     => 실행문이 같은 경우 break를 생략해서 하나의 실행문으로 여러 case를 관리할 수있다
		 *     => 실행문이 같아서 case를 함께 관리할 때 case 값1, 값2, ...로 줄여쓸 수 있음 
		 *     
		 *        
		 * */
		
		//문자가 산술연산자(+, -, *, /, %)이면 산술연산자입니다라고 출력하는 예제
		char ch = '+';
		//변수 == 값
		switch(ch) {
		
		case '+', '-', '*', '/', '%':
		/*
		case '+':
		case '-':
		case '*':
		case '/':
		case '%':
		*/
			System.out.println(ch + "는 산술 연산자입니다.");
			break;
		default:
			System.out.println(ch + "는 산술 연산자가 아닙니다.");
		}
	}

}






