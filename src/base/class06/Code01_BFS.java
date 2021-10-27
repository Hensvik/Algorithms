package base.class06;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//图的宽度优先遍历
//1，利用队列实现
//2，从源节点开始依次按照宽度进队列，然后弹出
//3，每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
//4，直到队列变空
public class Code01_BFS {

	public static void bfs(Node node) {
		if (node == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> map = new HashSet<>();
		//将节点添加入队列和map
		queue.add(node);
		map.add(node);
		//当队列不为空时，令cur为队列推出的一个

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			//遍历cur的所有后继节点
			for (Node next : cur.nexts) {
				//如果map不包含后继节点，就加入map
				if (!map.contains(next)) {
					map.add(next);
					queue.add(next);
				}
			}
		}
	}

}
