package threadpripority;

public class Maain2 {

	public static void main(String[] args) {
		
		TestThread2 t = new TestThread2();
		t.start();
		try {
			Thread.sleep(5000);
			t.stop2();
			System.out.println("stop2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
