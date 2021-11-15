package basePromote.class05;

import java.util.List;

public class Code03_MaxHappy2 {

    class Employee {
        public int happy; // 这名员工可以带来的快乐值
        List<Employee> nexts; // 这名员工有哪些直接下级
    }

    public static int maxHappy(Employee boss){
        Info headInfo = process(boss);
        return Math.max(headInfo.laiMaxHappy, headInfo.buMaxHappy);
    }

    public static class Info{
        public int laiMaxHappy;
        public int buMaxHappy;
        public Info(int lai,int bu){
            laiMaxHappy = lai;
            buMaxHappy = bu;
        }
    }

    public static Info process(Employee x){
        if(x.nexts.isEmpty()){  //x是基层员工的时候
            return new Info(x.happy,0);
        }
        int lai = x.happy;  //x来的情况下，整棵树的最大收益
        int bu = 0;     //x不来的情况下，整棵树最大收益

        for(Employee next: x.nexts){
            Info nextInfo = process(next);
            lai += nextInfo.buMaxHappy;
            bu += Math.max(nextInfo.laiMaxHappy,nextInfo.buMaxHappy);
        }
        return new Info(lai,bu);
    }
}
