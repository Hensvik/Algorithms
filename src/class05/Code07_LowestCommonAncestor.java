package class05;

import java.util.HashMap;
import java.util.HashSet;

public class Code07_LowestCommonAncestor {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node lowestAncestor(Node head, Node o1, Node o2) {
		if (head == null || head == o1 || head == o2) {
			return head;
		}
		Node left = lowestAncestor(head.left, o1, o2);
		Node right = lowestAncestor(head.right, o1, o2);
		if (left != null && right != null) {
			return head;
		}
		return left != null ? left : right;
	}

	// ��������--����һ
	public static class Record1 {
		private HashMap<Node, Node> map;

		public Record1(Node head) {
			map = new HashMap<Node, Node>();
			if (head != null) {
				map.put(head, null);
			}
			setMap(head);
		}

		private void setMap(Node head) {
			if (head == null) {
				return;
			}
			if (head.left != null) {
				map.put(head.left, head);
			}
			if (head.right != null) {
				map.put(head.right, head);
			}
			setMap(head.left);
			setMap(head.right);
		}

		public Node query(Node o1, Node o2) {
			HashSet<Node> path = new HashSet<Node>();
			while (map.containsKey(o1)) {
				path.add(o1);
				o1 = map.get(o1);
			}
			while (!path.contains(o2)) {
				o2 = map.get(o2);
			}
			return o2;
		}

	}

	// ��������--������
	public static class Record2 {
		private HashMap<Node, HashMap<Node, Node>> map;

		public Record2(Node head) {
			map = new HashMap<Node, HashMap<Node, Node>>();
			initMap(head);
			setMap(head);
		}

		private void initMap(Node head) {
			if (head == null) {
				return;
			}
			map.put(head, new HashMap<Node, Node>());
			initMap(head.left);
			initMap(head.right);
		}

		private void setMap(Node head) {
			if (head == null) {
				return;
			}
			headRecord(head.left, head);
			headRecord(head.right, head);
			subRecord(head);
			setMap(head.left);
			setMap(head.right);
		}

		private void headRecord(Node n, Node h) {
			if (n == null) {
				return;
			}
			map.get(n).put(h, h);
			headRecord(n.left, h);
			headRecord(n.right, h);
		}

		private void subRecord(Node head) {
			if (head == null) {
				return;
			}
			preLeft(head.left, head.right, head);
			subRecord(head.left);
			subRecord(head.right);
		}

		private void preLeft(Node l, Node r, Node h) {
			if (l == null) {
				return;
			}
			preRight(l, r, h);
			preLeft(l.left, r, h);
			preLeft(l.right, r, h);
		}

		private void preRight(Node l, Node r, Node h) {
			if (r == null) {
				return;
			}
			map.get(l).put(r, h);
			preRight(l, r.left, h);
			preRight(l, r.right, h);
		}

		public Node query(Node o1, Node o2) {
			if (o1 == o2) {
				return o1;
			}
			if (map.containsKey(o1)) {
				return map.get(o1).get(o2);
			}
			if (map.containsKey(o2)) {
				return map.get(o2).get(o1);
			}
			return null;
		}

	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		head.right.right.left = new Node(8);
		printTree(head);
		System.out.println("===============");

		Node o1 = head.left.right;
		Node o2 = head.right.left;

		System.out.println("o1 : " + o1.value);
		System.out.println("o2 : " + o2.value);
		System.out.println("ancestor : " + lowestAncestor(head, o1, o2).value);
		System.out.println("===============");

	}

}
