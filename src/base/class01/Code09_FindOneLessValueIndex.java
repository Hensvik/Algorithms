package base.class01;

public class Code09_FindOneLessValueIndex {

	//局部最小值问题
	public static int getLessIndex(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1; // no exist
		}
		// 数组长度==1 或 数组第零位<第一位 返回0
		if (arr.length == 1 || arr[0] < arr[1]) {
			return 0;
		}
		// 数组n-1位 < n-2位 则返回 n-1位
		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}
		int left = 1;
		int right = arr.length - 2;
		int mid = 0;
		//当left<right时
		while (left < right) {
			//mid取中点
			mid = (left + right) / 2;
			//如果数组mid位 大于 mid-1位
			if (arr[mid] > arr[mid - 1]) {
				//right修改为mid-1
				right = mid - 1;
			//如果数组mid位 大于 mid+1位
			} else if (arr[mid] > arr[mid + 1]) {
				//left修改为mid+1
				left = mid + 1;
			} else {
				//返回mid
				return mid;
			}
		}
		//返回left
		return left;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 6, 5, 3, 4, 6, 7, 8 };
		printArray(arr);
		int index = getLessIndex(arr);
		System.out.println("index: " + index + ", value: " + arr[index]);

	}

}
