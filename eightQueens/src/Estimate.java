public class Estimate {
    private int[][] chess;//八皇后约束集合
    private int N;
    private int[] count; //记录第k行m_i的值

    public Estimate(){
        chess = new int[8][8];
        N = 8;
        count = new int[8];
    }

    public int run(){
        int m = 1 ,r = 1, k = 0;
        while (k < N){
            if (getXcount(k) == 0 ) return m;

            r *= getXcount(k);
            m += r;
            Choose(k);
            k++;
        }
        return m;
    }

    private int getXcount(int k){
        int num = 0;
        for(int i = 0 ; i < N ; i++){
            if( chess[k][i] != -1 ) num++;
        }
        //记录第K行m的值
        count[k] = num;

        return num;
    }

    private void Choose(int k){
        int []al = new int [8]; //本行剩余可放皇后位置
        int index = 0;  //本行剩余可放皇后数量

        for(int i = 0 ; i < 8 ; i++){
            if(chess[k][i] != -1){
                al[index] = i;
                index++;
            }
        }
        //在剩余位置中随机选择
        int x = al[(int)(Math.random()*10%(index))];
        //放置皇后
        chess[k][x] = 1;

        //设置放置皇后禁用的位置
        int i = k+1;
        int j = x;
        //设置列
        while(i < 8){
            chess[i][j] = -1;
            i++;
        }
        //设置左下对角线
        i = k + 1;
        j = x - 1;
        while(j >= 0 && i < 8){
            chess[i][j] = -1;
            j--;
            i++;
        }
        //设置右下对角线
        i = k + 1;
        j = x + 1;
        while(j < 8 && i < 8){
            chess[i][j] = -1;
            j++;
            i++;
        }
    }

    public void showAnswer(){
        //输出m_i
        int ii = 0;
        System.out.print("m_i is ");
        while (ii < 8 && count[ii] != 0){
            System.out.print(count[ii]+" ");
            ii++;
        }
        System.out.println();

        int [] r = {-1,-1,-1,-1,-1,-1,-1,-1};
        int index = 0;

        //输出棋盘
        for(int i = 0; i < 8; i++){
            for(int j = 0 ; j < 8 ; j++){

                if(chess[i][j] != -1 && chess[i][j] != 0){
                    System.out.print("Q");
                    r[index] = j;
                    index++;
                }else{
                   System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.print("棋子位置为：");
        for(int i = 0; i < 8; i++){
            if(r[i] > -1) System.out.print(r[i]+1+" ");
        }
        System.out.println();
    }

}
