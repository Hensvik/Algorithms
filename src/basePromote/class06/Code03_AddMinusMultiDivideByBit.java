package basePromote.class06;

//给定两个有符号32位整数a和b，不能使用算术运算符，分别实现a和b的加、减、乘、除运算
//【要求】
//如果给定a、b执行加减乘除的运算结果就会导致数据的溢出，那么你实现的函数不必对此
//负责，除此之外请保证计算过程不发生溢出
public class Code03_AddMinusMultiDivideByBit {

	//加法思路：1.取异或得各位数相加信息；2.取与得各位数进位信息；
	//二者再重复上述1和2，发现不产生进位信息时，异或结果即为答案

	//如果溢出，用户活该
	public static int add(int a, int b) {
		int sum = a;
		while (b != 0) {
			sum = a ^ b;
			b = (a & b) << 1;
			a = sum;
		}
		return sum;
	}

	//相反数
	public static int negNum(int n) {
		return add(~n, 1);
	}

	//减法思路：
	//a加上b的相反数，即a+( ~b+1)
	public static int minus(int a, int b) {
		return add(a, negNum(b));
	}

	//乘法思路：
	//类比乘法计算思路：根据其中一个数的每一位1的位置对另一个数字进行左移位并累加
	public static int multi(int a, int b) {
		int res = 0;
		while (b != 0) {
			if ((b & 1) != 0) {
				res = add(res, a);
			}
			a <<= 1;
			b >>>= 1;
		}
		return res;
	}

	public static boolean isNeg(int n) {
		return n < 0;
	}

	//除法思路：
	//1.被除数左移至极限小于除数的位置
	//2.然后除数减去这个数
	//被除数重置
	//将得到的数重复上述流程至得到的数小于被除数为止

	//以下方法考虑了负数相除的情况
	public static int div(int a, int b) {
		int x = isNeg(a) ? negNum(a) : a;
		int y = isNeg(b) ? negNum(b) : b;
		int res = 0;
		for (int i = 31; i > -1; i = minus(i, 1)) {
			if ((x >> i) >= y) {
				res |= (1 << i);
				x = minus(x, y << i);
			}
		}
		return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
	}

	public static int divide(int a, int b) {
		if (b == 0) {
			throw new RuntimeException("divisor is 0");
		}
		if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
			return 1;
		} else if (b == Integer.MIN_VALUE) {
			return 0;
		} else if (a == Integer.MIN_VALUE) {
			int res = div(add(a, 1), b);
			return add(res, div(minus(a, multi(res, b)), b));
		} else {
			return div(a, b);
		}
	}

	public static void main(String[] args) {
		int a = (int) (Math.random() * 100000) - 50000;
		int b = (int) (Math.random() * 100000) - 50000;
		System.out.println("a = " + a + ", b = " + b);
		System.out.println(add(a, b));
		System.out.println(a + b);
		System.out.println("=========");
		System.out.println(minus(a, b));
		System.out.println(a - b);
		System.out.println("=========");
		System.out.println(multi(a, b));
		System.out.println(a * b);
		System.out.println("=========");
		System.out.println(divide(a, b));
		System.out.println(a / b);
		System.out.println("=========");

		a = Integer.MIN_VALUE;
		b = 32;
		System.out.println(divide(a, b));
		System.out.println(a / b);

	}

}
