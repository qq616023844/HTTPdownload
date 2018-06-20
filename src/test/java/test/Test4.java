package test;

public class Test4 {

	public void f(long size,int threadsize) {
		long blocksize = size/threadsize;
		long s = -1;
		for(int i=0;i<threadsize;i++) {
			if (i==(threadsize-1)) {
				System.out.println("start="+(s+1));
				System.out.println("end="+size);
			}else {
				System.out.println("start="+(s+1));
				System.out.println("end="+blocksize*(i+1));
				s=blocksize*(i+1);
			}

		}
	}
	public static void main(String[] args) {
		Test4 t = new Test4();
		t.f(512312342, 3);

	}

}
