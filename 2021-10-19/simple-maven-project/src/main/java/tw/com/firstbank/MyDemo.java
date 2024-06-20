package tw.com.firstbank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyDemo {

	static int HELLO = 0;

	int i = 0;

	public static void main(String[] args) {
		System.out.println("Hello World!");

		// byte, short, int, long, float, double, char, boolean
		int i = 0;

		System.out.println(i);

		// java lambda

		// flow control
		if (i > 0) {

		} else if (i <= 0) {

		} else {

		}
		switch (i) {
		case 0:
		case 1:
		case 2:
		default:
		}
//		// for (宣告初始變數; 繼續執行的條件; 每跑一圈要做的事)
//		for (int a = -1; a < i; a++) {
//			System.out.println(a);
//		}

		// constructor
		MyLoop loop = new MyLoop(1);
		loop.loop(-1, i);

		// operator
		// ++a a++
		// --a a--
		// + - * / %
		// < <= == >= > !=
		// & && | ||

		// public
		// protected
		// default(package)
		// private

		Employee employee = new Matt();
		employee.work();

		// Collection <I> size()
		// List <I> get()
		// ArrayList
		// Set <I>
		// HashSet

		List<String> collection = new ArrayList<>();
		collection.add("matt");
		collection.add("jason");

		// collection.size();
		// collection.get(index);

		// for (宣告初始變數; 繼續執行的條件; 每跑一圈要做的事)
//			for (int a = -1; a < i; a++) {
//			System.out.println(a);
//		}
		for (int a = 0; a < collection.size(); a++) {
			System.out.println(collection.get(a));
		}

		for (String each : collection) {
			System.out.println(each);
		}
	}

}
