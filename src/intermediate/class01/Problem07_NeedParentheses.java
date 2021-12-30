package intermediate.class01;

//一个完整的括号字符串定义规则如下:
//①空字符串是完整的。
//②如果s是完整的字符串，那么(s)也是完整的。
//③如果s和t是完整的字符串，将它们连接起来形成的st也是完整的。
//例如，"(()())", ""和"(())()"是完整的括号字符串，"())(", "()(" 和 ")" 是不完整的括号字符串。
//牛牛有一个括号字符串s,现在需要在其中任意位置尽量少地添加括号,将其转化
//为一个完整的括号字符串。请问牛牛至少需要添加多少个括号。
public class Problem07_NeedParentheses {

	//leftRest记录还有多少左括号没对上
	//needSolveRig记录没有被括上的右括号的数量
	public static int needParentheses(String str) {
		int leftRest = 0;
		int needSolveRight = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				leftRest++;
			} else {
				if (leftRest == 0) {
					needSolveRight++;
				} else {
					leftRest--;
				}
			}
		}
		//右括号没括上以及左括号没括上的总和即为总共的数量
		return leftRest + needSolveRight;
	}

	public static void main(String args[]) {

	}

}
