package day07;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

interface A{
	void test();
	default void test1() {}
}

class AImp implements A{
	
	@Override
	public void test() {
		
	}
}
