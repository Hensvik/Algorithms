package base.class08;

//汉诺塔问题
//
//down：减少的
public class Code01_Hanoi {

	public static void hanoi(int n) {
		if (n > 0) {
			func(n, n, "left", "mid", "right");
		}
	}

	// 1~i 圆盘 目标是from -> to,other是另外一个
	public static void func(int rest, int down, String from, String help, String to) {
		//如果剩余只有1块底，就把这一块移动到to
		if (rest == 1) {
			System.out.println("move " + down + " from " + from + " to " + to);
		} else {
			//将rest-1从from移动到help
			func(rest - 1, down - 1, from, to, help);
			//将最底下一块从from移动到to
			func(1, down, from, help, to);
			//将rest-1从help移动到to
			func(rest - 1, down - 1, help, from, to);
		}
	}

	public static void main(String[] args) {
		int n = 4;
		hanoi(n);
	}

}
