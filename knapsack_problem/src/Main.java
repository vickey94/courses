
public class Main {

    public static void main(String[] args) {

        //动态规划
      //  DP dp= new DP();
      //  dp.dp();

        //回溯法
       // BT bt = new BT();
       // bt.backTrack(0);

        //分支限界法
        BBM bbm = new BBM();
        bbm.getMaxValue();
        System.out.println("该背包能够取到的最大价值为:"+bbm.maxValue);
        for(int i : bbm.bestWay)
            System.out.print(i+"\t");


    }
}
