package base.class02;

import java.util.PriorityQueue;

public class Code04_SortArrayDistanceLessK {

	//堆排序扩展题目
	//已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元
	//素移动的距离可以不超过k，并且k相对于数组来说比较小。请选择一个合适的
	//排序算法针对这个数据进行排序。
	public void sortedArrDistanceLessK(int[] arr, int k) {
		//优先队列
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int index = 0;
		//遍历 k相比数组长度 中的0至小值，加入heap
		for (; index < Math.min(arr.length, k); index++) {
			heap.add(arr[index]);
		}
		int i = 0;
		//遍历所有arr
		for (; index < arr.length; i++, index++) {
			//这里不太懂为什么要重复add
			heap.add(arr[index]);
			arr[i] = heap.poll();
		}
		//arr[i]弹一个插一个
		while (!heap.isEmpty()) {
			arr[i++] = heap.poll();
		}
	}
}
