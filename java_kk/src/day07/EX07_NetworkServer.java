package day07;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EX07_NetworkServer {

	public static void main(String[] args) {
		/* TCP
		 * - 데이터 전송 속도가 느림
		 * - 안정적으로 통신
		 * - 연결 지향적
		 * UDP
		 * - 데이터 전송 속도가 빠름
		 * - 신뢰성이 없음
		 * - 비연결 지향적
		 * 
		 * 소켓 프로그래밍
		 * - 소켓을 이용한 통신 프로그램
		 * - 들어올 수 있는 구멍을 만들고, 그 구멍을 통해 통신
		 */
		
		/* 소켓 프로그래밍
		 * - 서버
		 * 1. 서버의 포트를 지정
		 * 2. 서버용 소켓 객체를 생성
		 * 3. 접속 대기
		 * 4. 접속 요청이 오면 수락 후 클라이언트 소켓 객체를 생성
		 * 5. 연결된 소켓 객체에서 IO스트림을 이용하여 읽고 쓰기를 함
		 * 6. 통신을 종료
		 * 
		 * - 클라이언트
		 * 1. 서버의 IP주소와 포트를 이용하여 클라이언트 객체를 생성
		 * 2. 연결 요청
		 * 3. 연결이 되면 소켓 객체에서 IO스트림을 이용하여 읽고 쓰기를 함
		 * 4. 통신을 종료
		 */
		//1. 서버 포트 지정
		int port = 5000;
		
		//2. 서버용 소켓 객체 생성
		try (ServerSocket serverSocket = new ServerSocket(port)){
			
			//3.4. 접속대기 및 연결 요청이 오면 연결 승인 후 소켓 객체 생성(클라이언트)
			Socket socket = serverSocket.accept();
			
			//5. 연결된 후 IO스트림을 이용하여 작업
			//클라이언트에게 문자열을 받음
			ObjectInputStream ois 
				= new ObjectInputStream(socket.getInputStream());
			String str = ois.readUTF();
			System.out.println("클라이언트 : " + str);
				
			//문자열을 입력
			Scanner scan = new Scanner(System.in);
			System.out.print("서버 : ");
			String msg = scan.nextLine();
			
			//클라이언트에게 입력받은 문자열을 전송
			ObjectOutputStream oos 
				= new ObjectOutputStream(socket.getOutputStream());
			oos.writeUTF(msg);
			oos.flush(); //버퍼가 다 안차도 밀어내는 역할
			
		}catch (Exception e) {
			System.err.println("예외 발생. 서버 종료.");
		}
	}
}
