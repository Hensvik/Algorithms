package base.class08;

//规定1和A对应、2和B对应、3和C对应... 那么一个数字字符串比如"111"，就可以转化为"AAA"、"KA"和"AK"。
//给定一个只有数字字符组成的字符串str，返回有多少种转化结果。

//特殊情况1：如果存在0的位数，0是不能单独转换的，只有10和20的情况
//特殊情况2：如果存在某位3-9的范围中，那么也单独转换，因为不存在大于26的组合位数
public class Code06_ConvertToLetterString {

	public static int number(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process(str.toCharArray(), 0);
	}

	public static int process(char[] chs, int i) {
		//如果i已经抵达最后位置，那么仅返回一种有效结果
		if (i == chs.length) {
			return 1;
		}
		//如果存在0，由于之前的分配方法存在错误，所以返回0
		if (chs[i] == '0') {
			return 0;
		}
		//如果某位上为1，res从i+1上持续递归
		if (chs[i] == '1') {
			int res = process(chs, i + 1);
			//如果i+1没有越界，res尝试并累加i+2上的数据
			if (i + 1 < chs.length) {
				res += process(chs, i + 2);
			}
			return res;
		}
		//如果某位上为2，res从i+1上持续递归
		if (chs[i] == '2') {
			int res = process(chs, i + 1);
			//但仅有在i+1没有越界时，
			if (i + 1 < chs.length && (chs[i + 1] >= '0' && chs[i + 1] <= '6')) {
				res += process(chs, i + 2);
			}
			return res;
		}
		return process(chs, i + 1);
	}

	public static void main(String[] args) {
		System.out.println(number("11111"));
	}

}
