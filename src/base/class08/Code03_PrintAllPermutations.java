package base.class08;

import java.util.ArrayList;

//打印一个字符串的全部排列，要求不要出现重复的排列
public class Code03_PrintAllPermutations {

	public static ArrayList<String> Permutation(String str) {
		ArrayList<String> res = new ArrayList<>();
		if (str == null || str.length() == 0) {
			return res;
		}
		//将字符串转换为数组
		char[] chs = str.toCharArray();
		process(chs, 0, res);
		res.sort(null);
		return res;
	}

	//res为记录结果的List
	//str[i..]范围上，所有的字符，都可以在i位置上，后续都去尝试
	//str[0..i-1]范围上，是之前做的选择
	//请把所有的字符串形成的全排列，加入到res里去
	public static void process(char[] chs, int i, ArrayList<String> res) {
		//如果i等于chs的最后一位，把chs添加入res
		if (i == chs.length) {
			res.add(String.valueOf(chs));
		}
		boolean[] visit = new boolean[26];
		//遍历i之后j的数组
		for (int j = i; j < chs.length; j++) {
			//如果chs[j]不为a，意思是如果已经尝试过a进行位置置换，则下次遇到就不尝试了
			//其实不加这段判断同样可以获得结果，但是结果需要去重
			if (!visit[chs[j] - 'a']) {
				visit[chs[j] - 'a'] = true;
				//交换字符串实现后续process，随后交换回来
				swap(chs, i, j);
				process(chs, i + 1, res);
				swap(chs, i, j);
			}
		}
	}

	//交换chs[i]和chs[j]的位置
	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

}
