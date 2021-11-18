package basePromote.class05;

//叉树节点间的最大距离问题
//从二叉树的节点a出发，可以向上或者向下走，但沿途的节点只能经过一次，到达节点b时路
//径上的节点个数叫作a到b的距离，那么二叉树任何两个节点之间都有距离，求整棵树上的最
//大距离
public class Code02_MaxDistanceInTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxDistance(Node head) {
		int[] record = new int[1];
		return posOrder(head, record);
	}

	//h记录树的最大高度
	public static class ReturnType{
		public int maxDistance;
		public int h;
		
		public ReturnType(int m, int h) {
			this.maxDistance = m;;
			this.h = h;
		}
	}

	//返回以x为头的整棵树，两个信息
	public static ReturnType process(Node head) {
		//当x为空时
		if(head == null) {
			return new ReturnType(0,0);
		}
		//递归处理左树和右树
		ReturnType leftReturnType = process(head.left);
		ReturnType rightReturnType = process(head.right);
		//includeHeadDistance=左树高+右树高+1
		int includeHeadDistance = leftReturnType.h + 1 + rightReturnType.h;
		int p1 = leftReturnType.maxDistance;
		int p2 = rightReturnType.maxDistance;
		//结果距离为新的p1和p2中和当前节点
		int resultDistance = Math.max(Math.max(p1, p2), includeHeadDistance);
		int hitself  = Math.max(leftReturnType.h, leftReturnType.h) + 1;
		return new ReturnType(resultDistance, hitself);
	}

	public static int posOrder(Node head, int[] record) {
		if (head == null) {
			record[0] = 0;
			return 0;
		}
		int lMax = posOrder(head.left, record);
		int maxfromLeft = record[0];
		int rMax = posOrder(head.right, record);
		int maxFromRight = record[0];
		int curNodeMax = maxfromLeft + maxFromRight + 1;
		record[0] = Math.max(maxfromLeft, maxFromRight) + 1;
		return Math.max(Math.max(lMax, rMax), curNodeMax);
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.left = new Node(2);
		head1.right = new Node(3);
		head1.left.left = new Node(4);
		head1.left.right = new Node(5);
		head1.right.left = new Node(6);
		head1.right.right = new Node(7);
		head1.left.left.left = new Node(8);
		head1.right.left.right = new Node(9);
		System.out.println(maxDistance(head1));

		Node head2 = new Node(1);
		head2.left = new Node(2);
		head2.right = new Node(3);
		head2.right.left = new Node(4);
		head2.right.right = new Node(5);
		head2.right.left.left = new Node(6);
		head2.right.right.right = new Node(7);
		head2.right.left.left.left = new Node(8);
		head2.right.right.right.right = new Node(9);
		System.out.println(maxDistance(head2));

	}

}
