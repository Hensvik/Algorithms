package base.class08;

//给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表
//i号物品的重量和价值。给定一个正数bag，表示一个载重bag的袋子，你装的物
//品不能超过这个重量。返回你能装下最多的价值是多少？
public class Code07_Knapsack {

	public static int maxValue1(int[] weights, int[] values, int bag) {
		return process1(weights, values, 0, 0, bag);
	}

	//i...的货物自由选择，形成最大的价值返回
	public static int process1(int[] weights, int[] values, int i, int alreadyweight, int bag) {
		//当已装的重量超过bag，即超重了，则返回0
		if (alreadyweight > bag) {
			return 0;
		}
		//当i已经超过重量表的长度，意味着都装不下
		if (i == weights.length) {
			return 0;
		}
		return Math.max(
				//递归判断i+1的情况
				process1(weights, values, i + 1, alreadyweight, bag),
				//递归判断i商品的价值放入后，i+1商品的递归的情况
				values[i] + process1(weights, values, i + 1, alreadyweight + weights[i], bag));
	}

	public static int maxValue2(int[] c, int[] p, int bag) {
		int[][] dp = new int[c.length + 1][bag + 1];
		for (int i = c.length - 1; i >= 0; i--) {
			for (int j = bag; j >= 0; j--) {
				dp[i][j] = dp[i + 1][j];
				if (j + c[i] <= bag) {
					dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]]);
				}
			}
		}
		return dp[0][0];
	}

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7 };
		int[] values = { 5, 6, 3, 19 };
		int bag = 11;
		System.out.println(maxValue1(weights, values, bag));
		System.out.println(maxValue2(weights, values, bag));
	}

}
