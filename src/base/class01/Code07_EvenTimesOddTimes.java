package base.class01;

public class Code07_EvenTimesOddTimes {

	//查找数组中唯一出现奇数次的数
	public static void printOddTimesNum1(int[] arr) {
		int eO = 0;
		for (int cur : arr) {
			eO ^= cur;
		}
		System.out.println(eO);
	}

	//查找数组中唯二出现奇数次的数
	public static void printOddTimesNum2(int[] arr) {
		int eO = 0, eOhasOne = 0;
		//遍历异或的结果是该唯二数的异或a^b
		for (int curNum : arr) {
			eO ^= curNum;
		}
		//获取最右边的一个不为0的数与a^b进行异或
		int rightOne = eO & (~eO + 1);
		for (int cur : arr) {
			if ((cur & rightOne) != 0) {
				eOhasOne ^= cur;
			}
		}
		System.out.println(eOhasOne + " " + (eO ^ eOhasOne));
	}

	public static void main(String[] args) {
		int a = 5;
		int b = 7;

		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println(a);
		System.out.println(b);

		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOddTimesNum1(arr1);

		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);

	}

}
