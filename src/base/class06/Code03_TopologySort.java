package base.class06;

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
		//遍历graph后把入读值放入inMap
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
			//遍历当前节点的所有后续节点
			for (Node next : cur.nexts) {
				//inMap放入next节点并将入度-1，最后只要没有环，所有都会入
				inMap.put(next, inMap.get(next) - 1);
				if (inMap.get(next) == 0) {
					//当入度为0时，才把节点加入zeroInQueue中，与21行如出一辙
					zeroInQueue.add(next);
				}
			}
		}
		return result;
	}
}
