package day08;

import lombok.AllArgsConstructor;

public class EX03_Synchronized {

	public static void main(String[] args) {
		/* 동기화
		 * - 메서드나 블럭에 스레드가 실행중이면 뒤늦게 온 스레드는 앞 스레드가 다 끝날 때 까지 대기하는 것
		 * - 
		 * */
		
		BankAccount bankAccount = new BankAccount("홍길동", 100000);
		Customer c1 = new Customer("홍길동", bankAccount);
		Customer c2 = new Customer("홍길동 아빠", bankAccount);
		
		c1.start();
		c2.start();

	}

}

class Customer extends Thread{
	private String name;
	private BankAccount bankAccount;
	
	@Override
	public void run() {
		//계좌에 돈을 입금
		int money = 100000;
		System.out.println(name + "님이 모바일로" + money + "원을 입금 중입니다.");
		BankAccount.deposit(name, money);
	}
}


@AllArgsConstructor
class BankAccount{
	private String name;
	private int balance;
	

	/**
	 * 기능 : 입금
	 * @param name 입금자명
	 * @param money 금액
	 */
	/* synchronized(동기화)를 하지 않으면 두사람이 동시에 입금했을 때
	 * 한사람이 입금이 다 되고 두번째가 입금하는 것이 아니라
	 * 잔액이 같이 바뀌기 때문에 잔액 출력이 맞지 않음 
	 * 먼저 10만을 입금하면 잔액이 20만이여야 하는데 30만으로 출력되는 문제가 발생
	 * 	=> synchronized를 추가하면 앞 사람 입금이 끝나야 뒷 사람 입금이 진행 
	 * */
	public synchronized void deposit (String name, int money) {
		System.out.println("입금 전 금액 : " + balance);
		
		if(money < 0) {
			System.out.println("금액은 0이상이어야 합니다.");
			return;
		}
		balance += money; //balance = balance + money;
		
		try {
			Thread.sleep(2000);  //2초동안 스레드를 멈춤
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(name + " : " + money + "원 입금. 잔액 : " + balance);

	}
	
}