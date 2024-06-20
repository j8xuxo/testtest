package tw.com.firstbank;

public class MyLoop {

	int defaultEnd;

	public MyLoop() {
		this(100);
	}
	
	MyLoop(int defaultEnd) {
		super();
//		if (defaultEnd > 100) {
//			defaultEnd = 100;
//		}
		this.defaultEnd = defaultEnd;
	}

	void loop(int start, int end) {
		for (int a = start; a < end && a < defaultEnd; a++) {
			System.out.println(a);
		}
	}

}
