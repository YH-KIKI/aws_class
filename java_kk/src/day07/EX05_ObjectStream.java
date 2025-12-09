package day07;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class EX05_ObjectStream {

	public static void main(String[] args) {
		/* ObjectInputStream/ ObjectOutputStream
		 * - 보조 스트림
		 * - 스트림을 통해 객체단위로 읽어옴/씀
		 * - 클래스가 직렬화가 된 경우에만 ObjectStream을 이용할 수 있음
		 * 
		 * 직렬화/역직렬화하는 방법
		 * - 클래스에 Serializable 인터페이스를 구현
		 * - ObjectStream을 이용하여 읽어오거나(ReadObject()) 쓰면 됨(writeObject())
		 * */
		
		String fileName = "src/day07/data2.txt";
		
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point(0,0));
		list.add(new Point(10,10));
		
		
		try (ObjectOutputStream oos 
				= new ObjectOutputStream(new FileOutputStream(fileName))){
			oos.writeObject(list);
			
		}catch(Exception e) {
			System.err.println("예외 발생");
		}
		
		//저장된 리스트를 가져오기
		try (ObjectInputStream ois 
				= new ObjectInputStream(new FileInputStream(fileName))){
			list = (ArrayList<Point>)ois.readObject();
			System.out.println(list);
		}catch(Exception e) {
			System.err.println("예외 발생");
		}
	}
}

class Point implements Serializable{

	private static final long serialVersionUID = 123L;
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
