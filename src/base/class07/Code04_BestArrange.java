package base.class07;

import java.util.Arrays;
import java.util.Comparator;

//贪心策略安排最佳会议时间问题
public class Code04_BestArrange {

	public static class Program {
		//会议开始时间
		public int start;
		//会议结束时间
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	//比较器，比较两场会议的结束时间差
	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	//programs为所有会议
	public static int bestArrange(Program[] programs, int start) {
		//将所有会议按照会议结束时间进行排序
		Arrays.sort(programs, new ProgramComparator());
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			//只要开始时间<=某个会议的开始时间，就安排该会议
			if (start <= programs[i].start) {
				result++;
				start = programs[i].end;
			}
		}
		return result;
	}

	public static void main(String[] args) {

	}

}
