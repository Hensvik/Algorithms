package basePromote.class06;

//位运算的题目
//给定两个有符号32位整数a和b，返回a和b中较大的。
//【要求】
//不用做任何比较判断。
public class Code01_GetMax {

	//取反
	public static int flip(int n) {
		return n ^ 1;
	}

	//n是非负数，返回1
	//n是负数，返回0
	public static int sign(int n) {
		return flip((n >> 31) & 1);
	}

	public static int getMax1(int a, int b) {
		//注意此处可能会溢出
		int c = a - b;				//取差
		int scA = sign(c);			//a-b为非负数，scA为1；a-b为负数，scA为0
		int scB = flip(scA);		//scA为0，scB为1；scA为1，scB为0
		// scA为0，scB为1；scA为1，scB必为0
		//此时scA和scB其中一个为0，一个为1
		return a * scA + b * scB;
	}

	//解决getMax1的可能存在的溢出问题
	public static int getMax2(int a, int b) {
		int c = a - b;
		//分别记录abc的状态
		int sa = sign(a);
		int sb = sign(b);
		int sc = sign(c);
		//记录sa和sb的异或，如果sa和sb不一样，则为1,；一样，则为0
		int difSab = sa ^ sb;
		//sa和sb的符号一样，为1；不一样，为0
		int sameSab = flip(difSab);

		//注意此时a和b如果符号相同，则a-b不会溢出
		//返回a的情况：reaturnA=1,returnB=0
		// 			1)a和b相同符号且a-b>=0;此时difSab=0，sameSab=1,则sc也必须为1即c必须a>b
		// 			2)a和b符号不相同并且a>0;此时difSab=1，sameSab=0，则sc无所谓但sa必须为1即a必须>0

		//返回b的情况:returnA=0,returnB=1
		//			1)a和b相同符号a-b<0;此时difSab=0，sameSab=1,sa无所谓，但sc必须为0即必须a<b
		//			2)a和b符号不相同并且a<0;此时difSab=1，sameSab=0，则sc无所谓但a必须<0

		int returnA = difSab * sa + sameSab * sc;
		int returnB = flip(returnA);
		return a * returnA + b * returnB;
	}

	public static void main(String[] args) {
		int a = -16;
		int b = 1;
		System.out.println(getMax1(a, b));
		System.out.println(getMax2(a, b));
		a = 2147483647;
		b = -2147480000;
		System.out.println(getMax1(a, b)); // wrong answer because of overflow
		System.out.println(getMax2(a, b));

	}

}
