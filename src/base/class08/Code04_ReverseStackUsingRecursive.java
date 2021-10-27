package base.class08;

import java.util.Stack;

//给你一个栈，请你逆序这个栈，不能申请额外的数据结构，只能使用递归函数。
//如何实现?
public class Code04_ReverseStackUsingRecursive {

	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		//递归获取了栈底的元素
		int i = getAndRemoveLastElement(stack);
		//反转栈并push i进入
		reverse(stack);
		stack.push(i);
	}

	//获取栈底的元素
	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		//每次递归弹出一个元素，直到栈为空，返回栈底的元素
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			//如果栈底不为空，递归获取，随后stack把弹出的元素放回，然后返回栈底的元素
			int last = getAndRemoveLastElement(stack);
			stack.push(result);
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
