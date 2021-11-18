package basePromote.class03;

//Manacher算法解决的问题
//字符串str中，最长回文子串的长度如何求解？
//如何做到时间复杂度O(N)完成？
public class Code02_Manacher {

//	//伪代码
//	public static int[] manacher(String str){
//		//1221 -> #1#2#2#2#1
//		//s -> 处理串 str
//		char[] str;
//		int[] pArr = new int[str.length];
//		int R =?;
//		int C =?;
//
//		for (int i = 0; i < str.length; i++) {
//			if(i在R外部){    //就是#的位置
//				从i开始往两边暴力扩;
//			}else{
//				if(i”回文区域彻底在L..R内){
//					pArr[i] = 某个O(1)表达式;
//				}else if(i“回文区域有一部分在L..R外){
//					pArr[i] = 某个O(1)表达式;
//				}else{	//i”回文区域与L..R的左边界压线
//					从R之外的字符开始，往外扩，然后确定pArr[i]的答案;
//					第一步扩失败了，R不变
//					否则R变大
//				}
//			}
//		}
//	}

	//填充#
	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	public static int maxLcpsLength(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] charArr = manacherString(str);	//1221 -> #1#2#2#1#
		int[] pArr = new int[charArr.length];	//回文半径数组
		int C = -1;		//记录中心
		int R = -1;		//回文右边界再往右一个位#，最右的有效区是R-1位置，注意这个边界是在遍历途中慢慢增加而不是str的长度
		int max = Integer.MIN_VALUE;	//max记录扩出来的最大值
		for (int i = 0; i != charArr.length; i++) {	//每一个位置求回文半径
			// i至少的回文区域，先给pArr[i]

			//至少不需要检验的区域
			//如果R在i外，则为1
			//否则则为其余两个瓶颈中，更小的那个则为半径
			//2*C-i为i“的位置
			//初始设置为1或
			pArr[i] = R > i ? Math.min(pArr[2 * C - i],R - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {		//当i+半径未超过长度且i-半径也未超过时
				//如果二者的值相等，则半径++继续比较
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			//如果i+半径突破右边界，那么C和R做相应改变
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				C = i;
			}
			max = Math.max(max, pArr[i]);
		}
		/*for (int p:pArr) {
			System.out.print(p+" ");
		}
		System.out.println();*/
		return max - 1;
	}

	public static void main(String[] args) {
		String str1 = "abc1234321ab";
		System.out.println(maxLcpsLength(str1));
	}

}
