package day08;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EX01_Client {

	public static void main(String[] args) {
		
		//1. 서버 IP주소와 port를 지정
		String ip = "127.0.0.1";
		final int PORT = EX01_Server.PORT;
		
		//2. IP주소와 port를 이용하여 연결 요청후 성공하면 소켓 객체를 생성
		try{
			Socket socket = new Socket(ip, PORT);
			System.out.println("[연결 성공!]");
			
			Client01 client = new Client01(socket);
			client.send();
			client.receive();
			
		}catch(Exception e) {
			System.err.println("예외 발생");
		}
		System.out.println("[클라이언트 종료(메인스레드 종료)]");
	}

}