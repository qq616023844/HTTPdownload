package threadpripority;

public class TestThread implements Runnable{

	boolean state = true;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(state) {
		}
	}
	public void stop2() {
		state = true;
	}

}
