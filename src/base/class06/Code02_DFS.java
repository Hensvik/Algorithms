package base.class06;

import java.util.HashSet;
import java.util.Stack;

//广度优先遍历
//1，利用栈实现
//2，从源节点开始把节点按照深度放入栈，然后弹出
//3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
//4，直到栈变空
public class Code02_DFS {

	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>();
		stack.add(node);
		set.add(node);
		System.out.println(node.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			for (Node next : cur.nexts) {
				//set是用来记录某个点的邻点确保不会重复获取同一个邻点的，确保图里存在环的情况的正确处理
				if (!set.contains(next)) {
					//取出来的点又一次压入栈中
					stack.push(cur);
					stack.push(next);
					set.add(next);
					System.out.println(next.value);
					break;
				}
			}
		}
	}

}
