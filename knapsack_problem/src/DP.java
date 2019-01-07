/*
 * Copyright (c) 2018.12
 * author: Kailun Yan(Vickey)
 * e-mail: vk@vickey.me
 */

//dynamic programming
public class DP {
    private final static int n = 4 ; //n种物品
    private final static int c = 7 ; //背包容量为c
    private final static int[] w = {3,5,2,1}; //物品i重量为w_i
    private final static int[] p = {9,10,7,4}; //物品i的价值为p_i

    public void  dp(){

        int[][] maxValue = new int[n +1][c +1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= c; j++){
                maxValue[i][j] = maxValue[i-1][j];

                //如果当前物品重量小于总重量可以放进去或者拿出其他物品
                if(w[i-1] <= j){
                    //（这个物品的价值加上当前能放的总重量减去当前物品重量时取前i-1个物品时的对应重量时候的最高价值）
                    if(maxValue[i-1][j- w[i-1]] + p[i-1] > maxValue[i-1][j]){
                        maxValue[i][j] = maxValue[i-1][j- w[i-1]] + p[i-1];
                    }
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= c; j++){
                System.out.print(maxValue[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println(maxValue[n][c]);

        //输出结果
        int x[] = new int[n];
        for(int i = n, j = c; i > 0 ; i--){
            if(maxValue[i][j] > maxValue[i-1][j]){
                x[i-1] = 1;
                j = j - w[i-1];
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(x[i] + "\t");
        }
    }

}
