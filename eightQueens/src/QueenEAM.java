//穷举法Exhaustive Attack method
public class QueenEAM {

    private static int []chess;
    private static int num;

    public QueenEAM(){
        chess = new int[8];
        num = 0;
    }

    public void enumQueen(){

        for(chess[0] = 0; chess[0] < 8; chess[0]++)
            for(chess[1] = 0;chess[1] < 8; chess[1]++)
                for(chess[2] = 0;chess[2] < 8; chess[2]++)
                    for(chess[3]=0;chess[3] < 8; chess[3]++)
                        for(chess[4] = 0;chess[4] < 8; chess[4]++)
                            for(chess[5] = 0;chess[5] < 8; chess[5]++)
                                for(chess[6] = 0; chess[6] < 8; chess[6]++)
                                    for(chess[7] = 0; chess[7] < 8; chess[7]++){
                                        if(rule(chess)){
                                      //   showAnswer();
                                        }
                                    }
    //    System.out.println(num);
    }

    private boolean rule(int []chess){

        for(int i = 0 ; i < 8  ; i++){
            for(int j = i+1 ; j < 8 ;j++ ){
                //判断皇后是否在同一列
                if(chess[i] == chess[j]) return false;
                //判断是否在对角线上
                if(Math.abs(chess[i]-chess[j]) == Math.abs(i - j)) return false;
            }
        }
        return true;
    }

    private void showAnswer(){
        num++;
        System.out.println("\n解答"+num);
        for(int y=0;y<8;y++){
            for(int x=0;x<8;x++){
                if(chess[y]==x){
                    System.out.print("Q");
                }else{
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
