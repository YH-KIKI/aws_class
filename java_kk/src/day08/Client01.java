package day08;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Client01 {
	
	private Socket socket;
	
	private final static String EXIT = "EXIT";
	
	public void send() {
		Thread t2 = new Thread(()->{
			System.out.println("[전송 기능 실행]");
			
			Scanner scan = new Scanner(System.in);
			try {
				ObjectOutputStream oos
					= new ObjectOutputStream(socket.getOutputStream());
				//메세지를 여러번 전송하기 위한 무한루프
				while(true) {
					//메세지 콘솔에서 입력
					System.out.print("입력 : ");
					String msg = scan.nextLine();
					//서버로 전송
					oos.writeUTF(msg);
					oos.flush();
					if(msg.equals(EXIT)) {
						break;
					}
				}			
			} catch (IOException e) {
				System.err.println("예외 발생");
			}
		});
		t2.start();
	}
	public void receive() {
		//클라이언트가 전송하면 받아서 콘솔에 출력 하는 쓰레드
		Thread t1 = new Thread(()->{
			System.out.println("[수신 기능 실행]");
			try {
				//클라이언트가 보낸 값을 받기 위한 입력 스트림객체 생성
				ObjectInputStream ois 
					= new ObjectInputStream(socket.getInputStream());
				//메세지를 여러번 입력할수 있게 무한 루프
				while(true) {
					String msg = ois.readUTF();
					System.out.println("메세지 : " + msg);
					if(msg.equals("EXIT")) {
						System.out.println("[상대방이 송신 기능을 종료했습니다.]");
						break;
					}
				}
			} catch (IOException e) {
				System.err.println("예외가 발생했습니다.");
			}
			System.out.println("[수신 스레드 종료]");
		});
		t1.start();
	}
}