/*
 * Copyright (c) 2018.12
 * author: Kailun Yan(Vickey)
 * e-mail: vk@vickey.me
 */

public class BT {
    private int n = 4 ; //n种物品
    private int c = 7 ; //背包容量为c
    private float[] w = {3,5,2,1}; //物品i重量为w_i
    private float[] p = {9,10,7,4}; //物品i的价值为p_i


    private int cw = 0; //当前已拿重量
    private float cp = 0; //当前已拿价格
    private float bestp = 0 ; //当前最优价值

    public void backtrack(){


    }

    public BT(){
        //对价值按照从大到小排序
        for(int i = 0; i < n -1; i++){
            for(int j = 0; j < n -i-1; j++){
                if(p[j]/ w[j]< p[j+1]/ w[j+1]){
                    float tw = w[j];
                    w[j] = w[j+1];
                    w[j+1] = tw;

                    float tp = p[j];
                    p[j] = p[j+1];
                    p[j+1] = tp;
                }
            }
        }

        for(int i = 0; i < n; i++){
            System.out.print(p[i]/ w[i]+"\t");
        }

    }

    public void backTrack(int t){
        //已经搜索到叶节点
        if(t >  n -1){
            if(cp > bestp)
                bestp = cp ;

            System.out.println(bestp);

            return;
        }

        //搜索左边节点 对于左子树，如果放入物品的重量已经超过了背包的容量，则直接进行剪枝。
        if(cw + w[t] <= c){
            cw += w[t];
            cp += p[t];

            backTrack(t+1);

            cw -= w[t];
            cp -= p[t];
        }

        if(bound(t+1) >= bestp){  backTrack(t+1);}
    }

    //计算剩余物品的最高价值上界
    public float bound(int k){
        float maxLeft = cp;
        float LeftW = c - cw;

        while (k <= n -1 && LeftW >= w[k]){
            LeftW -= w[k];
            maxLeft += p[k];
            k++;
        }

        //不能装时，用下一个物品的单位重量价值折算到剩余空间。
        if(k <= n -1 ){
            maxLeft += p[k] / w[k] * LeftW ;
        }

        return  maxLeft;
    }

}
