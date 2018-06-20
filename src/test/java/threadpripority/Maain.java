package threadpripority;

public class Maain {

	public static void main(String[] args) {
		
		TestThread t = new TestThread();
		Thread thread = new Thread(t);
		thread.start();
		try {
			Thread.sleep(1000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
