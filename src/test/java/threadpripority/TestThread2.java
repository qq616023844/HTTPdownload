package threadpripority;

public class TestThread2 extends Thread{

	boolean state = true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(state) {
			System.out.print("1");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void stop2() {
		state = false;
		System.out.println("stop2 has run");
	}

}
