package base.class06;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//kruskal算法，生成最小生成树
//最小生成树：在图的基础上令各点连通，且去除了权值比较大的边，保持权值最小
//适用范围：要求无向图

//首先我们把所有的边按照权值先从小到大排列，接着按照顺序选取每条边，如果这条边的两个端点不属于同一集合，
//那么就将它们合并，直到所有的点都属于同一个集合为止。
//undirected graph only
public class Code04_Kruskal {

	// Union-Find Set
	public static class UnionFind {
		private HashMap<Node, Node> fatherMap;
		private HashMap<Node, Integer> rankMap;

		public UnionFind() {
			fatherMap = new HashMap<Node, Node>();
			rankMap = new HashMap<Node, Integer>();
		}

		//查找某一个节点所在的集合
		private Node findFather(Node n) {
			Node father = fatherMap.get(n);
			if (father != n) {
				father = findFather(father);
			}
			fatherMap.put(n, father);
			return father;
		}

		//初始化sets？
		public void makeSets(Collection<Node> nodes) {
			fatherMap.clear();
			rankMap.clear();
			for (Node node : nodes) {
				//fatherMap放入节点信息
				//rankMap放入value为1的节点信息
				fatherMap.put(node, node);
				rankMap.put(node, 1);
			}
		}

		//判断Node a和Node b是否存在同一个集合中
		public boolean isSameSet(Node a, Node b) {
			return findFather(a) == findFather(b);
		}

		//将a节点和b节点放到同一个集合中
		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aFather = findFather(a);
			Node bFather = findFather(b);
			if (aFather != bFather) {
				int aFrank = rankMap.get(aFather);
				int bFrank = rankMap.get(bFather);
				if (aFrank <= bFrank) {
					fatherMap.put(aFather, bFather);
					rankMap.put(bFather, aFrank + bFrank);
				} else {
					fatherMap.put(bFather, aFather);
					rankMap.put(aFather, aFrank + bFrank);
				}
			}
		}
	}

	public static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}

	}

	public static Set<Edge> kruskalMST(Graph graph) {
		UnionFind unionFind = new UnionFind();
		//makeSets图的所有节点
		unionFind.makeSets(graph.nodes.values());

		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
		//将所有的边加入到优先队列中
		for (Edge edge : graph.edges) {
			priorityQueue.add(edge);
		}
		Set<Edge> result = new HashSet<>();
		//当优先队列不为空，弹出边
		while (!priorityQueue.isEmpty()) {
			Edge edge = priorityQueue.poll();
			//判断边的入点和出点是否在相同集合中
			if (!unionFind.isSameSet(edge.from, edge.to)) {

				result.add(edge);
				unionFind.union(edge.from, edge.to);
			}
		}
		return result;
	}
}
