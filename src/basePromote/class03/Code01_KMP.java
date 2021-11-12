package basePromote.class03;

//KMP算法解决的问题
//字符串str1和str2，str1是否包含str2，如果包含返回str2在str1中开始的位置。
//如何做到时间复杂度O(N)完成
public class Code01_KMP {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str1 = s.toCharArray();
		char[] str2 = m.toCharArray();
		int i1 = 0;
		int i2 = 0;
		//next为指定i2下一个跳跃节点的数量，即往前跳next[i]个字符再进行比较
		int[] next = getNextArray(str2);
		//要求i1和i2均不越界
		while (i1 < str1.length && i2 < str2.length) {
			//如果str1和str2来到共同字符，二者均++
			if (str1[i1] == str2[i2]) {
				i1++;
				i2++;
				//next[i2] == -1表示已经越界
			} else if (next[i2] == -1) {
				i1++;
				//i2可以继续往前跳
			} else {
				i2 = next[i2];
			}
		}
		//i1越界或者i2越界了，返回
		return i2 == str2.length ? i1 - i2 : -1;
	}

	public static int[] getNextArray(char[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];
		//人为规定0位置-1,1位置为0
		next[0] = -1;
		next[1] = 0;
		int i = 2;	//next数组的位置
		int cn = 0;
		while (i < next.length) {
			if (ms[i - 1] == ms[cn]) {		//i-1与cn位置比较，如果相等，next[i]改为cn+1
				next[i++] = ++cn;
			} else if (cn > 0) {	//当前跳到cn位置的字符，和i-1位置的字符匹配不上
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String str = "abbtabbzcabbtabbe";
		String match = "cabbtabbe";
		System.out.println(getIndexOf(str, match));

	}

}
