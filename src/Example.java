import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by ctam on 2/26/18.
 */
public class Example {


    public static void main(String[] args){


         class Pair<L,R> {
            private L l;
            private R r;
            public Pair(L l, R r){
                this.l = l;
                this.r = r;
            }
            public L getL(){ return l; }
            public R getR(){ return r; }
            public void setL(L l){ this.l = l; }
            public void setR(R r){ this.r = r; }
        }

        int k = 4;
        int[] example = new int[]{1,3,5,9,2,12,3};
        ArrayList<Pair<Integer,Integer>> pairList = new ArrayList<>();
        //Assuming quick sort so nlogn
        Arrays.sort(example);
        for(int i = 0; i < example.length -1; i ++){
            int pointer = i + 1;
            while(pointer < example.length-1){
                int difference = Math.abs(example[i] - example[pointer]);
                if(difference ==k ){
                    pairList.add(new Pair(example[i], example[pointer]));
                    break;
                }else if (difference >k){
                    break;
                }else{
                    pointer += 1;
                }
            }
        }

        for(Pair i : pairList){
            System.out.println("(" + i.getL() + ", " + i.getR() + ")");
        }
    }
}
