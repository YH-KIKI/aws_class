package day08;

import java.net.ServerSocket;
import java.net.Socket;

public class EX01_Server  {

	//1. port 지정
	public final static int PORT = 7000;
	
	public static void main(String[] args) {
		
		
		//2. port를 이용하여 서버소켓 객체 생성
		try{
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			System.out.println("[연결 대기 중...]");
			
			//3. 연결 대기 후, 연결 요청이 오면 수락 후 소켓 객체 생성
			Socket socket = serverSocket.accept();
			
			System.out.println("[연결 성공!]");
			
			Client01 client = new Client01(socket);
			client.send();
			client.receive();
		}catch(Exception e) {
			System.err.println("예외가 발생했습니다.");
		}
		System.out.println("[서버 종료(메인 스레드 종료)]");
	}

}