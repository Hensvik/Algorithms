package basePromote.class07;

//换钱的最少货币数
//【题目】
//给定数组 arr，arr 中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值
//的货币可以使用任意张，再给定一个整数 aim，代表要找的钱数，求组成 aim 的最少货币
//数。
//【举例】
//arr=[5,2,3]，aim=20。
//4 张 5 元可以组成 20 元，其他的找钱方案都要使用更多张的货币，所以返回 4。
//arr=[5,2,3]，aim=0。
//不用任何货币就可以组成 0 元，返回 0。
//arr=[3,5]，aim=2。
//根本无法组成
public class Code02_CoinsMin {

	//aim表示最终要达成的目标
	public static int minCoins1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return -1;
		}
		return process(arr, 0, aim);
	}

	// 当前考虑的面值是arr[i]，还剩rest的钱需要找零
	// 如果返回-1说明自由使用arr[i..N-1]面值的情况下，无论如何也无法找零rest
	// 如果返回不是-1，代表自由使用arr[i..N-1]面值的情况下，找零rest需要的最少张数
	public static int process(int[] arr, int i, int rest) {
		// base case：
		// 已经没有面值能够考虑了
		// 如果此时剩余的钱为0，返回0张
		// 如果此时剩余的钱不是0，返回-1
		if (i == arr.length) {
			return rest == 0 ? 0 : -1;
		}
		// 最少张数，初始时为-1，因为还没找到有效解
		int res = -1;
		// 依次尝试使用当前面值(arr[i])0张、1张、k张，但不能超过rest
		for (int k = 0; k * arr[i] <= rest; k++) {
			// 使用了k张arr[i]，剩下的钱为rest - k * arr[i]
			// 交给剩下的面值去搞定(arr[i+1..N-1])
			int next = process(arr, i + 1, rest - k * arr[i]);
			if (next != -1) { // 说明这个后续过程有效
				res = res == -1 ? next + k : Math.min(res, next + k);
			}
		}
		return res;
	}

	public static int minCoins2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return -1;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		// 设置最后一排的值，除了dp[N][0]为0之外，其他都是-1
		for (int col = 1; col <= aim; col++) {
			dp[N][col] = -1;
		}
		for (int i = N - 1; i >= 0; i--) { // 从底往上计算每一行
			for (int rest = 0; rest <= aim; rest++) { // 每一行都从左往右
				dp[i][rest] = -1; // 初始时先设置dp[i][rest]的值无效
				if (dp[i + 1][rest] != -1) { // 下面的值如果有效
					dp[i][rest] = dp[i + 1][rest]; // dp[i][rest]的值先设置成下面的值
				}
				// 左边的位置不越界并且有效
				if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1) {
					if (dp[i][rest] == -1) { // 如果之前下面的值无效
						dp[i][rest] = dp[i][rest - arr[i]] + 1;
					} else { // 说明下面和左边的值都有效，取最小的
						dp[i][rest] = Math.min(dp[i][rest],
								dp[i][rest - arr[i]] + 1);
					}
				}
			}
		}
		return dp[0][aim];
	}

	// for test
	public static int[] generateRandomArray(int len, int max) {
		int[] arr = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		return arr;
	}

	public static void main(String[] args) {
		int len = 10;
		int max = 10;
		int testTime = 10000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(len, max);
			int aim = (int) (Math.random() * 3 * max) + max;
			if (minCoins1(arr, aim) != minCoins2(arr, aim)) {
				System.out.println("ooops!");
				break;
			}
		}
	}
}
