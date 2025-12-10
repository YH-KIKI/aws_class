package day08;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EX04_Client {

	public static void main(String[] args) {
		
		//ip, port 설정
		final int PORT = EX04_Server.PORT;
		final String IP = "192.168.0.191"; 	//접속할 IP설정
		
		try {
			//아이디를 입력
			Scanner scan = new Scanner(System.in);
			System.out.println("아이디 : ");
			String id = scan.nextLine();
			
			//연결 요청 후 객체 생성
			Socket socket = new Socket(IP, PORT);
			
			//아이디 전송
			ObjectOutputStream oos 
				= new ObjectOutputStream(socket.getOutputStream());
			oos.writeUTF(id);
			oos.flush();
			
			//클라이언트 객체 생성(아이디 추가)
			Client02 c = new Client02(socket, id);
			//클라이언트의 전송 기능 실행
			c.send();
			//클라이언트의 수신 기능 실행			
			c.receive();
		}catch(Exception e) {
			e.printStackTrace();			
		}

	}

}
