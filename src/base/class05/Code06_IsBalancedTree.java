package base.class05;

//判断是否是平衡二叉树
//总结平衡二叉树特点：
//（1）非叶子节点最多拥有两个子节点；
//（2）非叶子节值大于左边子节点、小于右边子节点；
//（3）树的左右两边的层级数相差不会大于1;暂时逻辑判断似乎仅存在该条件
//（4）没有值相等重复的节点;
public class Code06_IsBalancedTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBalanced(Node head) {
		return process(head).isBalanced;
	}

	public static class ReturnType {
		public boolean isBalanced;
		public int height;

		public ReturnType(boolean isB, int hei) {
			isBalanced = isB;
			height = hei;
		}
	}

	public static ReturnType process(Node x) {
		if (x == null) {
			return new ReturnType(true, 0);
		}
		//递归遍历整棵树，获取树的最高值
		ReturnType leftData = process(x.left);
		ReturnType rightData = process(x.right);
		int height = Math.max(leftData.height, rightData.height);
		//判断是否平衡：每棵子树的左右高度差小于2
		boolean isBalanced = leftData.isBalanced && rightData.isBalanced
				&& Math.abs(leftData.height - rightData.height) < 2;
		return new ReturnType(isBalanced, height);
	}
}
