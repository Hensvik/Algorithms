package base.class02;

import java.util.Arrays;

public class Code03_HeapSort {

	//堆排序
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			heapInsert(arr, i);
		}
		int size = arr.length;
		//第0和size位（即最小值和最大值）交换位置，然后size--推出最大值，持续进行堆化操作
		swap(arr, 0, --size);
		while (size > 0) {
			//让堆重新变为大根堆，即将0从堆顶有一次移动到堆底
			heapify(arr, 0, size);
			swap(arr, 0, --size);
		}
	}

	//堆插入内容（这里依旧是数组，但是按照大根堆排序）
	public static void heapInsert(int[] arr, int index) {
		//左孩子节点>父节点 则交换，然后index修改为父节点
		while (arr[index] > arr[(index - 1) / 2]) {
			swap(arr, index, (index - 1) /2);
			index = (index - 1)/2 ;
		}
	}

	//将数组堆化 为大根堆
	public static void heapify(int[] arr, int index, int size) {
		//left为新建的左孩子节点的序号
		int left = index * 2 + 1;
		//当left<size
		while (left < size) {
			//右节点也未超size 且 右节点>左节点 则最大值设为右节点，否则为左节点（左右节点比较）
			int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
			//同上，左右孩子节点大者和父节点比较
			largest = arr[largest] > arr[index] ? largest : index;
			if (largest == index) {
				break;
			}
			//交换largest和index
			swap(arr, largest, index);
			//index修改为最大值
			index = largest;
			left = index * 2 + 1;
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			heapSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		heapSort(arr);
		printArray(arr);
	}

}
