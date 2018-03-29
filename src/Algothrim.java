/**
 * Created by ctam on 3/5/18.
 */

import java.util.Arrays;
import java.util.*;
import javafx.util.Pair;

public class Algothrim {

    public int[] findMaxSubArrayBad(int[] input){

        int startPointer = 0;
        int endPointer = 0;
        int max = input[0];
        for(int i = 0; i < input.length ; i ++){
            for(int j = i; j < input.length ; j ++){
                int tempMax = 0;
                for(int k = i; k <= j; k++){
                    tempMax += input[k];
                }
                if(tempMax > max){
                    max = tempMax;
                    startPointer = i;
                    endPointer = j;
                }
            }
        }

        int[] result = new int[endPointer - startPointer + 1];
        System.arraycopy(input, startPointer, result, 0, result.length);
        return result;
    }

    public int[] findMaxSubArray(int [] input){

        int startPointer = 0;
        int endPointer = 0;
        int maxCurrent = input[0];
        int maxGlobal = maxCurrent;
        int tempStartPointer = 0;

        for(int i = 1; i < input.length; i ++){
            int current = maxCurrent + input[i];
            if(input[i] > current){
                tempStartPointer = i;
                maxCurrent = input[i];
            }else{
                maxCurrent = current;
            }
            if(maxCurrent > maxGlobal){
                startPointer = tempStartPointer;
                maxGlobal = maxCurrent;
                endPointer = i;
            }
        }

        int resultLength = endPointer - startPointer + 1;
        int[] result = new int[resultLength];
        System.arraycopy(input, startPointer, result, 0, resultLength);
        return result;

    }
    public int[] quickSort(int[] input){

        if(input.length <= 1){
            return input;
        }else{
            int pivot = input.length/2;
            //swaping last element with pivot element
            int tempPivot = input[pivot];
            input[pivot] = input[input.length -1];
            input[input.length -1] = tempPivot;
            int biggerArrayStarter = 0;
            int searchStart = 0;
            boolean cont = false;

            while(searchStart < input.length -1){
                if(input[searchStart] > tempPivot ){
                    if(!cont){
                        //if this is the first time we encounter the first bigger elemnt, if so set the bigstarter on it
                        biggerArrayStarter = searchStart;
                        cont = true;
                    }
                }else{
                    int temp = input[searchStart];
                    input[searchStart] = input[biggerArrayStarter];
                    input[biggerArrayStarter] = temp;
                    biggerArrayStarter ++;
                }
                searchStart ++;
            }

            input[input.length - 1] = input[biggerArrayStarter];
            input[biggerArrayStarter] = tempPivot;


            int[] subArray = Arrays.copyOfRange(input,0,biggerArrayStarter);
            int[] subArray2 = Arrays.copyOfRange(input,biggerArrayStarter,input.length);
            int[] firstPart = quickSort(subArray);
            int[] secondPart = quickSort(subArray2);

            int[] result = new int[firstPart.length + secondPart.length];
            System.arraycopy(firstPart,0,result,0,firstPart.length);
            System.arraycopy(secondPart,0,result,firstPart.length,secondPart.length);

            return result;
        }
    }

    public int[] mergeSort(int[] input){

        if(input.length >= 2){
            int half = input.length / 2;
            int[] subArray = mergeSort(Arrays.copyOfRange(input,0,half));
            int[] subArray2 = mergeSort(Arrays.copyOfRange(input,half, input.length));
            return mergeHelper(subArray,subArray2);
        }else{
            return input;
        }
    }

    private int[] mergeHelper(int[] input1, int[] input2){
        int[] result = new int[input1.length + input2.length];
        int i = 0;
        int j = 0;
        int index = 0;
        while(i <= input1.length -1 && j <= input2.length -1){
            if(input1[i] > input2[j]){
                result[index] = input2[j];
                j++;
            }else{
                result[index] = input1[i];
                i++;
            }
            index++;
        }

        if(i < input1.length){
            for(int start=i; start <= input1.length-1; start++){
                result[index] = input1[start];
                index++;
            }
        }
        if(j < input2.length){
            for(int start=j; start <= input2.length-1; start++){
                result[index] = input2[start];
                index++;
            }
        }
        return result;

    }

    public int findMaxSubsequence(String A, String B){

        int[][] result = new int[A.length()][B.length()];
        for(int i = 0; i < A.length(); i ++){
            for(int j = 0; j < B.length(); j++){
                result[i][j] = -1;
            }
        }
      return findMaxSubsequence(A,B,A.length() -1, B.length() -1,result);
    }

    public int findMaxSubsequence(String A, String B, int aPointer, int bPointer, int[][]result){
        if(aPointer ==-1 || bPointer ==-1){
            return 0;
        }else if(result[aPointer][bPointer] != -1) {
            return result[aPointer][bPointer];
        }else{
            char aChar = A.charAt(aPointer);
            char bChar = B.charAt(bPointer);

            if(aChar == bChar){
                result[aPointer][bPointer] =  1 + findMaxSubsequence(A, B, aPointer - 1, bPointer -1,result);
                return result[aPointer][bPointer];
            }else{
                return Math.max(findMaxSubsequence(A, B, aPointer, bPointer -1,result)
                        ,findMaxSubsequence(A,B,aPointer - 1, bPointer,result));
            }
    }
    }

    public int myKnapSack(ArrayList<Pair<Integer,Integer>> pair, int maxWeight){

        int globalMax = 0;
        List<Pair<Integer,Integer>> globalResult = new ArrayList<>();

        for(int i =0; i < pair.size(); i ++){
            int currentWeight = 0;
            int currentMax = 0;
            List<Pair<Integer,Integer>> currentResult = new ArrayList<>();
            for(int j = i; j < pair.size();j++){
                if(currentWeight + pair.get(j).getKey()< maxWeight){
                    currentWeight += pair.get(j).getKey();
                    currentMax += pair.get(j).getValue() ;
                    currentResult.add(pair.get(j));
                }
            }

            if(currentMax > globalMax){
                globalMax = currentMax;
                globalResult = currentResult;
            }
        }

        for(Pair<Integer,Integer> i : globalResult){
            System.out.println("Weight : " + i.getKey() + ", Value : " + i.getValue());
        }
        return globalMax;
    }

    public int knapSack(ArrayList<Pair<Integer,Integer>> pair, int Max){

        int[][] intermittent = new int[pair.size()][Max];

        for(int i = 0; i < pair.size(); i ++){
            for(int j = 0; j < Max; j++){
                intermittent[i][j] = -1;
            }
        }
       /* ArrayList<Pair<Integer,Integer>> temp1 = knapSackHelper(pair,Max,0,0,pair.size()-1,new ArrayList<Pair<Integer,Integer>>());

        int temp1Value = 0;

        for(Pair<Integer,Integer> i : temp1){
            System.out.println("Key is "+ i.getKey() + " Value is " + i.getValue());
            temp1Value += i.getValue();
        }

        return temp1Value; */

        return knapSack(pair,Max,0,0,pair.size() -1, intermittent);
    }

    public ArrayList<Pair<Integer,Integer>> arrayKnapSackHelper(ArrayList<Pair<Integer,Integer>> pair, int Max,
                                                           int currentWeight, int currentValue, int n,
                                                           ArrayList<Pair<Integer,Integer>> currentResult){


        if( n == -1 || pair.get(n).getKey() + currentWeight > Max){
            return currentResult;
        }else{
            int nWeight = pair.get(n).getKey();
            int nValue = pair.get(n).getValue();
            ArrayList<Pair<Integer,Integer>> temp2 = arrayKnapSackHelper(pair,Max,currentWeight,currentValue, n -1,currentResult);
            ArrayList<Pair<Integer,Integer>> tempResult = new ArrayList<>(currentResult);
            tempResult.add(pair.get(n));
            ArrayList<Pair<Integer,Integer>> temp1 = arrayKnapSackHelper(pair,Max,currentWeight + nWeight, currentValue + nValue, n -1,tempResult);


            int temp1Value = 0;
            int temp2Value = 0;

            for(Pair<Integer,Integer> i : temp1){
                temp1Value += i.getValue();
            }
            for(Pair<Integer,Integer> j : temp2){
                temp2Value += j.getValue();
            }

            if(temp1Value > temp2Value){
                return temp1;
            }else{
                return temp2;
            }
        }
    }
    public int knapSack(ArrayList<Pair<Integer,Integer>> pair, int Max,
                        int currentWeight, int currentValue, int n,
                        int[][] tempResult){



        if( n == -1 || pair.get(n).getKey() + currentWeight > Max){
            return currentValue;
        }else if(tempResult[n][currentWeight] != -1 ) {
            return tempResult[currentValue][currentWeight];
        }else{
            int nWeight = pair.get(n).getKey();
            int nValue = pair.get(n).getValue();

            int temp1Value = knapSack(pair,Max,currentWeight,currentValue,n -1, tempResult);
            int temp2Value = knapSack(pair, Max,currentWeight + nWeight, currentValue + nValue, n-1, tempResult);
            return Math.max(temp1Value,temp2Value);
        }
    }

    public int countNegativeNaive(int[][] matrix){

        int result = 0;
        for(int[] i : matrix){
            for(int j : i){
                if(j < 0){
                    result ++;
                }
            }
        }
        return result;
    }

    public int countNegative(int[][] matrix){

        if(matrix[0][0] > 0){
            return 0;
        }
        int row = matrix.length - 1;
        int column = 0;
        int result = 0;
        int lastColumn = 0;

       for(int i = 0; i < matrix.length; i ++){
           if(matrix[i][column] >= 0){
               row = i - 1; //we found the first positive number, move 1 backward to the last negative
               break;
           }
       }
        while(row >= 0 && column < matrix[0].length){
            if(matrix[row][column] >= 0){
                result += (row + 1) * (column - lastColumn);
                lastColumn = column;
                column --;
                row --;

            }else {
                column++;
            }

        }

        if(column == matrix[0].length){
                result += (row +1 ) * (column - lastColumn);
        }
        return result;
    }

    public Tree.Node LCANaive(Tree tree, int target1, int target2){

        ArrayList<Tree.Node> leftResult = LCANaiveHelper(tree.head,target1, new ArrayList<Tree.Node>());
        ArrayList<Tree.Node> rightResult = LCANaiveHelper(tree.head,target2, new ArrayList<Tree.Node>());
        //System.out.println(leftResult.size());
        //System.out.println(rightResult.size());

        for(Tree.Node i : leftResult){
            //System.out.print(" " + i.getValue() + " ");
        }

        System.out.println();
        for(Tree.Node j :rightResult ){
           // System.out.print(" " + j.getValue() + " ");
        }

        Tree.Node lowest = null;

        for(Tree.Node i : leftResult){
            for(Tree.Node j : rightResult){
                if(i.getValue() == j.getValue()){
                    lowest = i;
                }
            }
        }
        return lowest;
    }

    public int LCA(Tree tree, int target1, int target2){

        Map<Integer, Integer>  list = new HashMap<>();
        return LCAHelper(tree, new Tree(1).new Node(target1), new Tree(1).new Node(target2), list );

    }

    private int LCAHelper(Tree tree,Tree.Node node1, Tree.Node node2, Map<Integer, Integer> list){
        LCAOneNodeHelper(tree.getNode(),null, node1.getValue(), list);
        LCAOneNodeHelper(tree.getNode(),null, node2.getValue(), list);

        for (int key : list.keySet()) {
          //  System.out.println(key + " " + list.get(key));
        }
        Stack<Integer> node1Stack = new Stack<>();
        node1Stack.add(node1.getValue());
        Integer temp = list.get(node1.getValue());

        while(temp != null){
            node1Stack.add(temp);
            temp = list.get(temp);
        }

        Stack<Integer> node2Stack = new Stack<>();
        node2Stack.add(node2.getValue());
        Integer temp2 = list.get(node2.getValue());

        while(temp2 != null){
            node2Stack.add(temp2);
            temp2 = list.get(temp2);
        }

        Boolean found = false;
        int lowestCommon = tree.getNode().getValue();

        while(!found && !node1Stack.empty() && !node2Stack.empty()){
            lowestCommon = node1Stack.pop();
            if(lowestCommon != node2Stack.pop()){
                found = true;
            }
        }
        return lowestCommon;
    }

    private void LCAOneNodeHelper(Tree.Node currentNode, Integer parent, int target, Map<Integer, Integer>  list){
        list.put(currentNode.getValue(), parent);
        if(currentNode.getValue() != target){
            if(currentNode.hasLeft()){
                LCAOneNodeHelper(currentNode.getLeft(), currentNode.getValue(), target, list);
            }
            if(currentNode.hasRight()){
                LCAOneNodeHelper(currentNode.getRight(), currentNode.getValue(), target, list);
            }
        }

    }

    public Map<Character,Integer> longestConsecutiveNaive(String input1){

        char current = input1.charAt(0);
        char maxChar = current;

        int index = 1;
        int currenLength = 1;
        int maxLenght = 1;

        Map<Character,Integer> result = new HashMap<>();

        while(index < input1.length()){
            if(input1.charAt(index) == current){
                currenLength ++;
                if(currenLength > maxLenght){
                    maxChar = current;
                    maxLenght = currenLength;
                }
            }else{
                current = input1.charAt(index);
                currenLength = 1;
            }
            index ++;
        }

        result.put(maxChar,maxLenght);
        return result;
    }


    private ArrayList<Tree.Node> LCANaiveHelper(Tree.Node node, int target, ArrayList<Tree.Node> currentResult){
        if(node.getValue() == target){
            currentResult.add(node);
            return currentResult;
        }else{
            ArrayList<Tree.Node> copyLeft = new ArrayList<>(currentResult);
            copyLeft.add(node);
            ArrayList<Tree.Node> copyRight = new ArrayList<>(currentResult);
            copyRight.add(node);

            /*if(node.hasLeft() && LCAHelper(node.getLeft(),target,copyLeft) != null){
                return LCAHelper(node.getLeft(),target,copyLeft);
            }else if(node.hasRight() && LCAHelper(node.getRight(),target,copyRight) != null){
                return LCAHelper(node.getRight(),target,copyRight);
            }else{
                return null;
            }*/

            return null;
        }
    }

    public int[] randomArray(int[] input){
        Random rand = new Random();

        for(int i = 0; i < input.length; i ++){
            int random = (int)Math.floor(rand.nextFloat() * input.length);
            int temp = input[i];
            input[i] = input[random];
            input[random] = temp;
        }
        return input;
    }

    public Boolean towerHopper(int[] tower){
        int i = 0;

        while(i < tower.length){
            int max = 0;
            int maxIndex = i;
            for(int j =1; j <= tower[i]; j ++){
                if(i + j >= tower.length){
                    return true;
                }
                if( j + tower[i+j] > max){
                    max = j + tower[i+j];
                    maxIndex = (i+j);
                }
            }
            if(maxIndex == i){
                return false;
            }
                i = maxIndex;
        }
        return true;
    }

    public Map.Entry<Integer,Integer> kClosestPointNaive(ArrayList<Map.Entry<Integer,Integer>> points, int target){

        Iterator<Map.Entry<Integer, Integer>> it = points.iterator();

        ArrayList<Map.Entry<Map.Entry<Integer,Integer>, Double>> result = new ArrayList<>();
        while(it.hasNext()){
            Map.Entry<Integer, Integer> pair = it.next();
            Double distance = Math.sqrt(Math.pow(pair.getKey(),2) + Math.pow(pair.getValue(),2));
            result.add(new AbstractMap.SimpleEntry<>(pair,distance));
        }

        result.sort(new Comparator<Map.Entry<Map.Entry<Integer,Integer>, Double>>() {

            @Override
            public int compare(Map.Entry<Map.Entry<Integer,Integer>, Double> o1, Map.Entry<Map.Entry<Integer,Integer>, Double> o2){
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        return result.get(target - 1).getKey();
    }

    public Map.Entry<Integer,Integer> kClosestPoint(ArrayList<Map.Entry<Integer,Integer>> points, int target){
        int targetSize = target - 1;
        Iterator<Map.Entry<Integer, Integer>> it = points.iterator();

        ArrayList<Map.Entry<Map.Entry<Integer,Integer>, Double>> result = new ArrayList<>();
        while(it.hasNext()){
            Map.Entry<Integer, Integer> pair = it.next();
            Double distance = Math.sqrt(Math.pow(pair.getKey(),2) + Math.pow(pair.getValue(),2));
            result.add(new AbstractMap.SimpleEntry<>(pair,distance));
        }

        ArrayList<Map.Entry<Map.Entry<Integer,Integer>, Double>> smaller = new ArrayList<>();
        ArrayList<Map.Entry<Map.Entry<Integer,Integer>, Double>> bigger = new ArrayList<>();

        Iterator<Map.Entry<Map.Entry<Integer,Integer>, Double>>  itResult;

        while(!result.isEmpty()){
            itResult = result.iterator();
            Map.Entry<Map.Entry<Integer,Integer>, Double> pivot = itResult.next();
            while(itResult.hasNext()){
                Map.Entry<Map.Entry<Integer,Integer>, Double> resultPair = itResult.next();
                if(resultPair.getValue() > pivot.getValue()){
                    bigger.add(resultPair);
                }else if(resultPair.getValue() < pivot.getValue()){
                    smaller.add(resultPair);
                }else{
                    smaller.add(resultPair);
                }
            }
            System.out.println("Smaller size is " + smaller.size()  + " and targetSize is " + targetSize);
            if(smaller.size() == targetSize){ //if we are looking for the 4th element, the size of smaller should be 3, hence smaller.size(3) should = 4 -1
                return pivot.getKey();
            }else if(smaller.size() > targetSize){// if smallest size is larger or equal to target size, then what we are looking for is in smaller array
                result = new ArrayList<>(smaller);
                smaller = new ArrayList<>();
                bigger = new ArrayList<>();
            }else{//then it is in the bigger array. Need to loop through the bigger list
                result = new ArrayList<>(bigger);
                targetSize = targetSize - smaller.size() - 1;
                smaller = new ArrayList<>();
                bigger = new ArrayList<>();
            }
        }

        return null;
    }


}
