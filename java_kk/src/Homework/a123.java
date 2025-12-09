package Homework;

import java.util.Scanner;

public class a123 {

	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		char ch = scan.next().charAt(0);
		
		/*
		System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
		System.out.println(num1 + " - " + num2 + " = " + (num1 - num2));
		System.out.println(num1 + " * " + num2 + " = " + (num1 * num2));
		System.out.println(num1 + " / " + num2 + " = " + (num1 / (double)num2));
		*/
		switch(ch) {
		case '+': 
			System.out.println(num1 + " " + ch + " " + num2 + " = " + (num1 + ch + num2));
			break;
			
			default:
				System.out.println(ch + "은 + 연산자가 아니무니다");
		
		}
		

	}

}
