package day08;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Server02 {
	
	//접속이 연결된 클라이언트 소켓
	private Socket socket;
	
	//클라이언트에게 메세지를 보내기 위한 출력스트림 리스트
	private List<ObjectOutputStream> list;
	
	
	private final String EXIT = "EXIT";
	
	//소켓이 전송한 메세지를 받아 다른 클라이언트들에게 전송하는 메서드
	public void receive() {
		Thread t = new Thread(()->{
			String id = "";
			try {
				//list에 지금 연결된 소켓의 출력 스트림을 추가
				ObjectOutputStream oos 
					= new ObjectOutputStream(socket.getOutputStream());
				list.add(oos);
				//클라이언트가 전송한 정보를 가져오기 위해 입력 스트림 생성
				ObjectInputStream ois 
					= new ObjectInputStream(socket.getInputStream());
				//무한루프로 EXIT가 입력될 때 까지 반복
				while(true) {
					//클라이언트가 입력한 아이디 가져오기
					id = ois.readUTF();
					//클라이언트가 입력한 메세지 가져오기
					String msg = ois.readUTF();
					
					
					//list에 있는 클라이언트들에게 메세지를 전송
					//A가 메세지를 보내서 모든 클라이언트들에게 메세지를 전송하는 중에는
					//뒤늦게 온 B의 메세지를 보내지 않고 기다리게 하기 위해 동기화를 시킴
					synchronized (list) {
						for(ObjectOutputStream client : list) {
							send(client, id, msg);
						}
						//내가 EXIT를 입력했으면 난 연결이 끊겨야 하기 때문에
						//리스트에서 나를 제거						
						if(msg.equals(EXIT)) {
							list.remove(oos);
						}
					}
					
				}
			
			} catch(Exception e){
				System.out.println(id + "님이 접속을 종료했습니다.");
			}
			
		});
		t.start();
	}
	private void send(ObjectOutputStream client, String id, String msg) {
		if(client == null || id == null || msg == null) {
			return;
		}
		
		try {
			client.writeUTF(id);
			client.writeUTF(msg);
			client.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
