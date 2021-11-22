package intermediate.class08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Problem07_WordMinPaths {

	public static List<List<String>> findMinPaths(String start, String to,
			List<String> list) {
		list.add(start);
		HashMap<String, ArrayList<String>> nexts = getNexts(list);
		HashMap<String, Integer> distances = getDistances(start, nexts);
		LinkedList<String> pathList = new LinkedList<>();
		List<List<String>> res = new ArrayList<>();
		getShortestPaths(start, to, nexts, distances, pathList, res);
		return res;
	}

	public static HashMap<String, ArrayList<String>> getNexts(List<String> words) {
		Set<String> dict = new HashSet<>(words);
		HashMap<String, ArrayList<String>> nexts = new HashMap<>();
		for (int i = 0; i < words.size(); i++) {
			nexts.put(words.get(i), new ArrayList<>());
		}
		for (int i = 0; i < words.size(); i++) {
			nexts.put(words.get(i), getNext(words.get(i), dict));
		}
		return nexts;
	}

	private static ArrayList<String> getNext(String word, Set<String> dict) {
		ArrayList<String> res = new ArrayList<String>();
		char[] chs = word.toCharArray();
		for (char cur = 'a'; cur <= 'z'; cur++) {
			for (int i = 0; i < chs.length; i++) {
				if (chs[i] != cur) {
					char tmp = chs[i];
					chs[i] = cur;
					if (dict.contains(String.valueOf(chs))) {
						res.add(String.valueOf(chs));
					}
					chs[i] = tmp;
				}
			}
		}
		return res;
	}

	public static HashMap<String, Integer> getDistances(String start,
			HashMap<String, ArrayList<String>> nexts) {
		HashMap<String, Integer> distances = new HashMap<>();
		distances.put(start, 0);
		Queue<String> queue = new LinkedList<String>();
		queue.add(start);
		HashSet<String> set = new HashSet<String>();
		set.add(start);
		while (!queue.isEmpty()) {
			String cur = queue.poll();
			for (String str : nexts.get(cur)) {
				if (!set.contains(str)) {
					distances.put(str, distances.get(cur) + 1);
					queue.add(str);
					set.add(str);
				}
			}
		}
		return distances;
	}

	private static void getShortestPaths(String cur, String to,
			HashMap<String, ArrayList<String>> nexts,
			HashMap<String, Integer> distances, LinkedList<String> solution,
			List<List<String>> res) {
		solution.add(cur);
		if (to.equals(cur)) {
			res.add(new LinkedList<String>(solution));
		} else {
			for (String next : nexts.get(cur)) {
				if (distances.get(next) == distances.get(cur) + 1) {
					getShortestPaths(next, to, nexts, distances, solution, res);
				}
			}
		}
		solution.pollLast();
	}

	public static void main(String[] args) {
		String start = "abc";
		String end = "cab";
		String[] test = { "abc", "cab", "acc", "cbc", "ccc", "cac", "cbb",
				"aab", "abb" };
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < test.length; i++) {
			list.add(test[i]);
		}
		List<List<String>> res = findMinPaths(start, end, list);
		for (List<String> obj : res) {
			for (String str : obj) {
				System.out.print(str + " -> ");
			}
			System.out.println();
		}

	}

}
