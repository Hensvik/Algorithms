package base.class03;

import java.util.Arrays;

public class Code02_RadixSort {

	// only for no-negative value
	public static void radixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		radixSort(arr, 0, arr.length - 1, maxbits(arr));
	}

	//获取最大位
	public static int maxbits(int[] arr) {
		int max = Integer.MIN_VALUE;
		//获取arr中的最大值
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		//获取arr中的最大值的最高位的数（123则返回第百位即2）
		int res = 0;
		while (max != 0) {
			res++;
			max /= 10;
		}
		return res;
	}

	//arr[begin..end]排序
	public static void radixSort(int[] arr, int begin, int end, int digit) {
		final int radix = 10;
		int i = 0, j = 0;
		//有多少个数准备多少个辅助空间
		int[] bucket = new int[end - begin + 1];
		for (int d = 1; d <= digit; d++) {
			//count是一个词频表，即统计0-9(i)的数量
			//最多10个空间
			//count[0] 当前位（d位）是0的数字有多少个
			//count[1] 当前位（d位）是（0和1）的数字有多少个
			//count[2] 当前位（d位）是（0、1和2）的数字有多少个
			//count[i] 当前位（d位）是（0-i）的数字有多少个
			int[] count = new int[radix];	// count[0..9]
			//
			for (i = begin; i <= end; i++) {
				//举例，d==1则取个位数字，d==2则取十位
				j = getDigit(arr[i], d);
				count[j]++;
			}
			//将count处理为前缀数组
			for (i = 1; i < radix; i++) {
				count[i] = count[i] + count[i - 1];
			}
			//数组从右往左遍历，每次都从对应的count减去1，且对应的count位-1即为结果桶bucket中的对应位置
			for (i = end; i >= begin; i--) {
				j = getDigit(arr[i], d);
				bucket[count[j] - 1] = arr[i];
				count[j]--;
			}
			//将bucket桶的结果写回arr
			for (i = begin, j = 0; i <= end; i++, j++) {
				arr[i] = bucket[j];
			}
		}
	}

	//计算x/(10的d-1次方)的结果取余10
	public static int getDigit(int x, int d) {
		return ((x / ((int) Math.pow(10, d - 1))) % 10);
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
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
		int maxValue = 100000;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			radixSort(arr1);
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
		radixSort(arr);
		printArray(arr);

	}

}
