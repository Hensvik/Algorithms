package basePromote.class01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

//哈希函数的特点
//1.哈希函数的入参和出参都具有一定的取值范围，比如java中的输出与在2的31次方-1内
//2.同一个入参必定得到唯一的结果
//3.在模板的量足够大时，会发生哈希碰撞
//4.离散型和均匀性：离散型，哈希值的分布没有规律；均匀性：哈希值的结果出现频率分布均匀

public class Code01_HashMap {

	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		map.put("zuo", "31");

		System.out.println(map.containsKey("zuo"));
		System.out.println(map.containsKey("chengyun"));
		System.out.println("=========================");

		System.out.println(map.get("zuo"));
		System.out.println(map.get("chengyun"));
		System.out.println("=========================");

		System.out.println(map.isEmpty());
		System.out.println(map.size());
		System.out.println("=========================");

		System.out.println(map.remove("zuo"));
		System.out.println(map.containsKey("zuo"));
		System.out.println(map.get("zuo"));
		System.out.println(map.isEmpty());
		System.out.println(map.size());
		System.out.println("=========================");

		map.put("zuo", "31");
		System.out.println(map.get("zuo"));
		map.put("zuo", "32");
		System.out.println(map.get("zuo"));
		System.out.println("=========================");

		map.put("zuo", "31");
		map.put("cheng", "32");
		map.put("yun", "33");

		for (String key : map.keySet()) {
			System.out.println(key);
		}
		System.out.println("=========================");

		for (String values : map.values()) {
			System.out.println(values);
		}
		System.out.println("=========================");

		map.clear();
		map.put("A", "1");
		map.put("B", "2");
		map.put("C", "3");
		map.put("D", "1");
		map.put("E", "2");
		map.put("F", "3");
		map.put("G", "1");
		map.put("H", "2");
		map.put("I", "3");
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "," + value);
		}
		System.out.println("=========================");

		// you can not remove item in map when you use the iterator of map
//		 for(Entry<String,String> entry : map.entrySet()){
//			 if(!entry.getValue().equals("1")){
//				 map.remove(entry.getKey());
//			 }
//		 }

		// if you want to remove items, collect them first, then remove them by
		// this way.
		List<String> removeKeys = new ArrayList<String>();
		for (Entry<String, String> entry : map.entrySet()) {
			if (!entry.getValue().equals("1")) {
				removeKeys.add(entry.getKey());
			}
		}
		for (String removeKey : removeKeys) {
			map.remove(removeKey);
		}
		for (Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + "," + value);
		}
		System.out.println("=========================");

	}

}
