package basePromote.class01;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

//并查集结构的详解和实现
public class Code04_UnionFind {

	public static class Element<V> {
		public V value;

		public Element(V value) {
			this.value = value;
		}

	}

	public static class UnionFindSet<V> {
		//elementMap为样本对应自己的元素表
		public HashMap<V, Element<V>> elementMap;
		//fatherMap的value为指向自己父节点的元素表
		public HashMap<Element<V>, Element<V>> fatherMap;
		//rankMap的value为代表元素的数量的元素表
		public HashMap<Element<V>, Integer> rankMap;

		public UnionFindSet(List<V> list) {
			elementMap = new HashMap<>();
			fatherMap = new HashMap<>();
			rankMap = new HashMap<>();
			for (V value : list) {
				Element<V> element = new Element<V>(value);
				elementMap.put(value, element);
				fatherMap.put(element, element);
				rankMap.put(element, 1);
			}
		}

		private Element<V> findHead(Element<V> element) {
			//新建栈结构
			Stack<Element<V>> path = new Stack<>();
			//当element不和父节点对应元素表相等，则将element推入栈中
			while (element != fatherMap.get(element)) {
				path.push(element);
				//同时将element改为其父节点，此时element已经为代表节点
				element = fatherMap.get(element);
			}
			//当path不为空，将path中推出的元素推入fatherMap中，即将沿途所有节点的指向修改为指向element
			while (!path.isEmpty()) {
				fatherMap.put(path.pop(), element);
			}
			return element;
		}

		//判断两个节点是否是同属一个集合
		public boolean isSameSet(V a, V b) {
			if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
				return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
			}
			return false;
		}

		public void union(V a, V b) {
			if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
				//a圈往上至不能再往上找到代表节点，b圈同理
				Element<V> aF = findHead(elementMap.get(a));
				Element<V> bF = findHead(elementMap.get(b));
				//当a的最终父节点和b的最终父节点不相同，就合并
				if (aF != bF) {
					//如果a的父节点的个数>b的父节点的个数，就把af给big，反之
					Element<V> big = rankMap.get(aF) >= rankMap.get(bF) ? aF : bF;
					Element<V> small = big == aF ? bF : aF;
					//同时把small的节点的走向指向big
					fatherMap.put(small, big);
					//将big的节点放入rankMap，且数量修改为a和b的和
					rankMap.put(big, rankMap.get(aF) + rankMap.get(bF));
					//将small的代表节点从rank中移除
					rankMap.remove(small);
				}
			}
		}

	}

}
