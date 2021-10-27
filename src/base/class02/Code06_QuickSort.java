package base.class02;

import java.util.Arrays;

public class Code06_QuickSort {

	//这是3.0版本的快速排序，分为3个部分，分别是<p ==p >p的三部分
	public static void quickSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		quickSort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int l, int r) {
		if (l < r) {
			//随机选择一个数并与最后一个数做交换
			//注意：Math.random()的取值为0-1
			//所以这里的选择范围为l-r其中一个随机数与r交换
			swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
			//此时r为随机选取的数
			//返回p的长度一定为2，且p中的两个值分别为分界<p和==p，以及分界==p和>p的数
			int[] p = partition(arr, l, r);
			quickSort(arr, l, p[0] - 1);		//“小于”区域排序
			quickSort(arr, p[1] + 1, r);		//“大于”区域排序
		}
	}

	//这是一个处理arr[l...r]的函数
	//默认以arr[r]作划分，arr[r]--> p 划分为 <p ==p >p 和 p
	//返回等于区域（左边界，右边界），所以返回一个长度为2的数组
	public static int[] partition(int[] arr, int l, int r) {
		int less = l - 1;	//记录<区的右边界
		int more = r;		//记录>区的左边界
		while (l < more) {
			if (arr[l] < arr[r]) {
				//交换当前左边界和arr[l]的位置，同时l++进行循环
				swap(arr, ++less, l++);
			} else if (arr[l] > arr[r]) {
				//交换当前右边界和arr[l]的位置，将大于边界的数组移动到最右边
				swap(arr, --more, l);
			} else {
				l++;
			}
		}
		swap(arr, more, r);
		return new int[] { less + 1, more };
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
			quickSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		quickSort(arr);
		printArray(arr);

	}

}
