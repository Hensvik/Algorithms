package class06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//拓扑排序算法
//适用范围：要求有向图，且有入度为0的节点，且没有环
public class Code03_TopologySort {

	// directed graph and no loop
	public static List<Node> sortedTopology(Graph graph) {
		HashMap<Node, Integer> inMap = new HashMap<>();
		Queue<Node> zeroInQueue = new LinkedList<>();
		//遍历graph的值并放入inMap
		for (Node node : graph.nodes.values()) {
			//放入node节点的入度
			inMap.put(node, node.in);
			//当入度为0时，将节点node添加入zeroInQueue队列中
			if (node.in == 0) {
				zeroInQueue.add(node);
			}
		}
		List<Node> result = new ArrayList<>();
		//当zeroInQueue队列不为空时，令cur为队列出列节点
		//添加cur入result
		//遍历cur的所有后继节点并放入inMap
		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			result.add(cur);
			for (Node next : cur.nexts) {
				inMap.put(next, inMap.get(next) - 1);
				if (inMap.get(next) == 0) {
					zeroInQueue.add(next);
				}
			}
		}
		return result;
	}
}
