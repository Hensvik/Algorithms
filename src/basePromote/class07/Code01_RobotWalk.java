package basePromote.class07;

public class Code01_RobotWalk {

	public static int ways1(int N, int M, int K, int P) {
		// 参数无效直接返回0
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		// 总共N个位置，从M点出发，还剩K步，返回最终能达到P的方法数
		return walk(N, M, K, P);
	}

	// N : 位置为1 ~ N，固定参数
	// cur : 当前在cur位置，可变参数
	// rest : 还剩res步没有走，可变参数
	// P : 最终目标位置是P，固定参数
	// 该函数的含义：只能在1~N这些位置上移动，当前在cur位置，走完rest步之后，停在P位置的方法数作为返回值返回
	public static int walk(int N, int cur, int rest, int P) {
		// 如果没有剩余步数了，当前的cur位置就是最后的位置
		// 如果最后的位置停在P上，那么之前做的移动是有效的
		// 如果最后的位置没在P上，那么之前做的移动是无效的
		if (rest == 0) {
			return cur == P ? 1 : 0;
		}
		// 如果还有rest步要走，而当前的cur位置在1位置上，那么当前这步只能从1走向2
		// 后续的过程就是，来到2位置上，还剩rest-1步要走
		if (cur == 1) {
			return walk(N, 2, rest - 1, P);
		}
		// 如果还有rest步要走，而当前的cur位置在N位置上，那么当前这步只能从N走向N-1
		// 后续的过程就是，来到N-1位置上，还剩rest-1步要走
		if (cur == N) {
			return walk(N, N - 1, rest - 1, P);
		}
		// 如果还有rest步要走，而当前的cur位置在中间位置上，那么当前这步可以走向左，也可以走向右
		// 走向左之后，后续的过程就是，来到cur-1位置上，还剩rest-1步要走
		// 走向右之后，后续的过程就是，来到cur+1位置上，还剩rest-1步要走
		// 走向左、走向右是截然不同的方法，所以总方法数要都算上
		return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
	}

	public static int ways2(int N, int M, int K, int P) {
		// 参数无效直接返回0
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		int[][] dp = new int[K + 1][N + 1];
		dp[0][P] = 1;
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				if (j == 1) {
					dp[i][j] = dp[i - 1][2];
				} else if (j == N) {
					dp[i][j] = dp[i - 1][N - 1];
				} else {
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
				}
			}
		}
		return dp[K][M];
	}

	public static int ways3(int N, int M, int K, int P) {
		// 参数无效直接返回0
		if (N < 2 || K < 1 || M < 1 || M > N || P < 1 || P > N) {
			return 0;
		}
		int[] dp = new int[N + 1];
		dp[P] = 1;
		for (int i = 1; i <= K; i++) {
			int leftUp = dp[1];// 左上角的值
			for (int j = 1; j <= N; j++) {
				int tmp = dp[j];
				if (j == 1) {
					dp[j] = dp[j + 1];
				} else if (j == N) {
					dp[j] = leftUp;
				} else {
					dp[j] = leftUp + dp[j + 1];
				}
				leftUp = tmp;
			}
		}
		return dp[M];
	}

	public static void main(String[] args) {
		System.out.println(ways1(7, 4, 9, 5));
		System.out.println(ways2(7, 4, 9, 5));
		System.out.println(ways3(7, 4, 9, 5));
	}

}
