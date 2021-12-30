package intermediate.class02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//给定一个数组arr，求差值为k的去重数字对。
public class Problem01_SubvalueEqualK {

	public static List<List<Integer>> allPair(int[] arr, int k) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}
		List<List<Integer>> res = new ArrayList<>();
		for (Integer cur : set) {
			if (set.contains(cur + k)) {
				res.add(Arrays.asList(cur, cur + k));
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int []arr = {3,2,5,7,0};
		List<List<Integer>> a = allPair(arr,2);
	}
}
