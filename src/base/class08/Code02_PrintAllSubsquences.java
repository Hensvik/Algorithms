package base.class08;

import java.util.ArrayList;
import java.util.List;

//打印一个字符串的全部子序列，包括空字符串
public class Code02_PrintAllSubsquences {

	public static void printAllSubsquence(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0);
	}

	//当前来到i位置
	public static void process(char[] chs, int i) {
		//如果i已经抵达终止位置，直接打印值
		if (i == chs.length) {
			System.out.println(String.valueOf(chs));
			return;
		}
		//新增下一个字符进入考虑范围，并把之前的字符串也考虑进去
		process(chs, i + 1);
		char tmp = chs[i];
		chs[i] = 0;
		//不要当前字符的路
		process(chs, i + 1);
		chs[i] = tmp;
	}
	
	public static void function(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0, new ArrayList<Character>());
	}

	//当前来到i位置，要和不要，走两条路
	//之前的选择，所形成的列表
	public static void process(char[] chs, int i, List<Character> res) {
		if(i == chs.length) {
			printList(res);
		}
		List<Character> resKeep = copyList(res);
		resKeep.add(chs[i]);
		process(chs, i+1, resKeep);
		List<Character> resNoInclude = copyList(res);
		process(chs, i+1, resNoInclude);
	}
	
	public static void printList(List<Character> res) {
		// ...;
	}
	
	public static List<Character> copyList(List<Character> list){
		return null;
	}
	

	public static void main(String[] args) {
		String test = "abc";
		printAllSubsquence(test);
	}

}
