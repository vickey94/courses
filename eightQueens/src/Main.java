import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //EAM和Backtracking时间对比
        long[] time1 = new long[10];
        long[] time2 = new long[10];


        for(int x = 0 ; x < 10 ;x++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {

                QueenEAM qEAM = new QueenEAM();
                qEAM.enumQueen();
            }
            long finish = System.currentTimeMillis();
        //    System.out.println(finish - start);

            long start1 = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                Queen queen = new Queen();
                queen.backtrack(1);

            }
            long finish1 = System.currentTimeMillis();

            System.out.println((finish-start)+"\t"+(finish1 - start1));

        }

      //estimate
        /*int sum = 0;
        for(int i = 0 ; i < 10; i++){
            System.out.println("第"+(i+1)+"次估计");
            Estimate estimate = new Estimate();
            int temp = estimate.run();
            System.out.println("m is "+temp);
            sum += temp;
            estimate.showAnswer();
            Thread.sleep(1000);
        }

        System.out.println("m 的平均值为："+sum/10);*/
    }
}
