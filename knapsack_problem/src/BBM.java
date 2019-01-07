/*
 * Copyright (c) 2018.12
 * author: Kailun Yan(Vickey)
 * e-mail: vk@vickey.me
 */


//Branch and Bound Method
import java.util.PriorityQueue;

class thingNode implements Comparable<thingNode> {
    int weight; //该节点目前背包中的重量
    float value; //该节点目前背包中的总价值
    float upprofit;//该节点能够达到的价值上界
    int Left; //该节点是否属于左节点(用于最终构造最优解)
    int level; //该节点是第几个物品的选择
    thingNode father; //该节点的父节点

    @Override
    public int compareTo(thingNode node) {
        if (this.upprofit < node.upprofit)
            return 1;
        else if (this.upprofit == node.upprofit)
            return 0;
        else
            return -1;

    }
}

public class BBM {

    int n = 4; //n种物品
    int c = 7; //背包容量为c
    int[] w = {3, 5, 2, 1}; //物品i重量为w_i
    float[] p = {9, 10, 7, 4}; //物品i的价值为p_i
    float maxValue = 0;
    int[] bestWay = new int[n];

    public void getMaxValue() {
        //对价值按照从大到小排序
       for(int i = 0; i < n -1; i++){
            for(int j = 0; j < n -i-1; j++){
                if(p[j]/ w[j]< p[j+1]/ w[j+1]){
                    int tw = w[j];
                    w[j] = w[j+1];
                    w[j+1] = tw;

                    float tp = p[j];
                    p[j] = p[j+1];
                    p[j+1] = tp;
                }
            }
        }

        PriorityQueue<thingNode> pq = new PriorityQueue<thingNode>();

        //构造一个初始化节点属于-1层
        thingNode initial = new thingNode();
        initial.level = -1;
        initial.upprofit = 30;
        pq.add(initial);

        while (!pq.isEmpty()) {
            thingNode fatherNode = pq.poll();

            //当已经搜索到叶子节点时
            if (fatherNode.level == n - 1) {
                if (fatherNode.value > maxValue) {
                    maxValue = fatherNode.value;

                    for (int i = n - 1; i >= 0; i--) {
                        bestWay[i] = fatherNode.Left;
                        fatherNode = fatherNode.father;
                    }
                }
            }else{
                //先统计其左节点信息,判断是否加入队列。
                if(w[fatherNode.level + 1] + fatherNode.weight <= c){
                    thingNode newNode = new thingNode();
                    newNode.level = fatherNode.level + 1;
                    newNode.value = fatherNode.value + p[fatherNode.level+1];
                    newNode.weight = w[fatherNode.level + 1] + fatherNode.weight;
                    newNode.upprofit = Bound(newNode);
                    newNode.father = fatherNode;
                    newNode.Left = 1;
                    if(newNode.upprofit > maxValue){
                        pq.add(newNode);
                    }
                }

                //向右节点搜索,其能够取到的价值上界通过父亲节点的上界减去本层物品的价值。
                if((fatherNode.upprofit - p[fatherNode.level+1])> maxValue){
                    thingNode newNode2 = new thingNode();
                    newNode2.level = fatherNode.level+1;
                    newNode2.value = fatherNode.value;
                    newNode2.weight = fatherNode.weight;
                    newNode2.father = fatherNode;
                    newNode2.upprofit = fatherNode.upprofit - p[fatherNode.level+1];
                    newNode2.Left = 0;
                    pq.add(newNode2);
                }
            }
        }


    }

    //计算该节点的最高价值上界
    public float Bound(thingNode node) {
        float maxLeft = node.value;
        int leftW = c - node.weight;
        int tempLevel = node.level;

        //依照单位重量价值次序装入剩余的物品
        while (tempLevel <= n - 1 && leftW >= w[tempLevel]) {
            leftW -= w[tempLevel];
            maxLeft += p[tempLevel];
            tempLevel++;
        }

        //不能装时，用下一个物品的单位重量价值折算到剩余空间
        if (tempLevel <= n - 1) {
            maxLeft += p[tempLevel] / w[tempLevel] * leftW;
        }

        return maxLeft;
    }

}

