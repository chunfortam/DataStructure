import javafx.util.Pair;

import java.util.*;

/**
 * Created by ctam on 2/23/18.
 */
public class main {

    private static Algothrim algo = new Algothrim();


    public static void main(String[] args) {

        //testFindMaxSubArray();
        //testfindMaxSubsequence();

        //System.out.println("+" == "+");
        //testBaseBall();
        testIsToeplitzMatrix();


    }
       //int[] result = obj.mergeSort(test);

    private static void testKnapSack(){
        ArrayList<Pair<Integer,Integer>> test2 = new ArrayList<Pair<Integer,Integer>>();
        test2.add(new Pair<>(1,5));
        test2.add(new Pair<>(2,3));
        test2.add(new Pair<>(4,6));
        test2.add(new Pair<>(2,3));
        test2.add(new Pair<>(5,2));


        Algothrim algo = new Algothrim();
        System.out.println(algo.knapSack(test2,10));
    }

    private static void testCountNegative(){
        int[][] input = new int[5][5];
        int[] temp = new int[25];
        int max = 99;
        int min = -99;
        int count = 0;
        Random random = new Random();

        for(int i = 0; i < temp.length; i ++){
            int number = random.nextInt(max + 1 -min) + min;
            if(number < 0){
                count++;
            }
            temp[i] = number;
        }

        Arrays.sort(temp);
        for(int i = 0; i < input.length; i ++){
            for(int j = 0; j < input[0].length; j ++){
                input[i][j] = temp[(input.length * i) + j];
            }
        }


        Algothrim algo = new Algothrim();
        int mineResult = algo.countNegative(input);
        int correctResult = count;

        if(mineResult != correctResult){
            for(int[] i : input){
                for(int j : i){
                    System.out.print(j + " ");
                }
                System.out.println();
            }
            System.out.println("my result = " + algo.countNegative(input));
            System.out.println("correct result = " + count);
        }

    }

    private static void testFindMaxSubArray() {

        Algothrim obj = new Algothrim();
            for(int j = 0; j < 1000000 ; j ++){
                int[] test1 = new int[10];
                for (int i=0; i<10; i++){
                    int ii = -10 + (int) (Math.random() * ((10 - (-10)) + 1));
                    test1[i] = ii;
                }

                int[] result = obj.findMaxSubArrayBad(test1);
                int[] result2 = obj.findMaxSubArray(test1);
                //printArray(test1);
                if(compareArray(result,result2)){
                    printArray(test1);
                }
            }

        }

    private static void testfindMaxSubsequence() {
        Algothrim obj = new Algothrim();
        for (int j = 0; j < 1000000; j++) {
            int[] test1 = new int[10];
            for (int i = 0; i < 10; i++) {
                int ii = -10 + (int) (Math.random() * ((10 - (-10)) + 1));
                test1[i] = ii;
            }
        }

        String input1 = "BAD";
        String input2 = "ABACD";

        System.out.println(obj.findMaxSubsequence(input1,input2));

    }

    private static void testLCANaive(){
        Algothrim algo = new Algothrim();
        Tree tree = new Tree(1);
        Tree.Node root = new Tree(1).new Node(1);
        root.setLeft(new Tree(1).new Node(3));
        root.getLeft().setLeft(new Tree(1).new Node(4));
        root.getLeft().setRight(new Tree(1).new Node(6));
        root.getLeft().getRight().setLeft(new Tree(1).new Node(5));
        root.setRight(new Tree(1).new Node(2));
        tree.setNode(root);

        System.out.println(algo.LCANaive(tree,4,5).getValue());
        System.out.println(algo.LCANaive(tree,3,5).getValue());
        System.out.println(algo.LCANaive(tree,4,2).getValue());
        System.out.println(algo.LCANaive(tree,6,6).getValue());
    }

    private static void testLCA(){
        Algothrim algo = new Algothrim();
        Tree tree = new Tree(1);
        Tree.Node root = new Tree(1).new Node(1);
        root.setLeft(new Tree(1).new Node(3));
        root.getLeft().setLeft(new Tree(1).new Node(4));
        root.getLeft().setRight(new Tree(1).new Node(6));
        root.getLeft().getRight().setLeft(new Tree(1).new Node(5));
        root.setRight(new Tree(1).new Node(2));
        tree.setNode(root);

        System.out.println(algo.LCA(tree,4,5));
        System.out.println(algo.LCA(tree,3,5));
        System.out.println(algo.LCA(tree,4,2));
        System.out.println(algo.LCA(tree,6,6));
    }

    private static void testLCCNaive(){
        String input = "AABCDDBBBEA";
        Map<Character,Integer> test = algo.longestConsecutiveNaive(input);
        System.out.println(test.toString());
    }

    private static void printArray(int[] printArray){
        for(int i: printArray){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static boolean compareArray(int[] first, int[] second){
        ArrayList<Integer> result = new ArrayList<Integer>();
        ArrayList<Integer> result2 = new ArrayList<Integer>();
        Boolean different = false;
        if(first.length != second.length){
            System.out.println("Length does not match : " + first.length + " vs " + second.length);
        }else{
            for(int i = 0;i < first.length; i ++){
                if(first[i] == second[i]){
                    result.add(0);
                    result2.add(0);
                }else{
                    result.add(first[i]);
                    result2.add(second[i]);
                    different = true;
                }
            }
        }

        if(different == true){
            System.out.println("Detect difference!");
            System.out.println();
            for(int i : result){
                System.out.print(i + " ");
            }
            System.out.println();
            for(int i : result2){
                System.out.print(i + " ");
            }

            System.out.println();
        }else{
            //System.out.println("Array is the same");
        }

        return different;
    }

    private static void testRandomArray(){
        Algothrim algo = new Algothrim();
        int[] list = { 1, 4, 9, 16, 25, 36, 49 };
        int[] result = algo.randomArray(list);
        printArray(result);
    }

    private static void testTowerHopper(){
        int[] list = { 4,2,1,0,2,0 };
        System.out.println(algo.towerHopper(list));
    }

    private static void testKClosestPointNaive(){
        ArrayList<Map.Entry<Integer,Integer>> points = new ArrayList<>();

        points.add(new AbstractMap.SimpleEntry<>(-2,-4));
        points.add(new AbstractMap.SimpleEntry<>(0,-2));
        points.add(new AbstractMap.SimpleEntry<>(-1,0));
        points.add(new AbstractMap.SimpleEntry<>(3,5));
        points.add(new AbstractMap.SimpleEntry<>(-2,-3));
        points.add(new AbstractMap.SimpleEntry<>(3,2));

       // Map.Entry<Integer,Integer> resultNaive = algo.kClosestPointNaive(points,4);
        Map.Entry<Integer,Integer> result = algo.kClosestPoint(points,5);
        System.out.println(result.getKey() + "," + result.getValue());
       // System.out.println(resultNaive.getKey() + "," + resultNaive.getValue());
    }

    private static void testBaseBall(){
        String[] input1 = {"5","2","C","D","+"};
        String[] input2 = {"5","-2","4","C","D","9","+","+"};

        System.out.println(algo.baseBall(input1));
        System.out.println(algo.baseBall(input2));

    }

    private static void testCandies(){
        int[] input1 = {1,1,2,2,3,3};
        int[] input2 = {1,1,2,3};
        int[] input3 = {1,2,3,4,5,6,7};
        int[] input4 = {1,1,1,1,1};

        System.out.println(algo.distributeCandies(input1));
        System.out.println(algo.distributeCandies(input2));
        System.out.println(algo.distributeCandies(input3));
        System.out.println(algo.distributeCandies(input4));
    }
    private static void testIsToeplitzMatrix(){
        int[][] matrix = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        int[][] matrix2 = {{6,7},{4,6},{4,5}};
        int[][]  matrix3 = {{1,3,3,4},{1,1,2,3}};
        System.out.println(algo.isToeplitzMatrix(matrix));
        System.out.println(algo.isToeplitzMatrix(matrix2));
        System.out.println(algo.isToeplitzMatrix(matrix3));
    }
}
