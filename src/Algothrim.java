import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.*;
import javafx.util.Pair;
import java.util.Stack;

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

        ArrayList<Tree.Node> leftResult = LCANaiveHelper(tree.head,target1, new ArrayList<>());
        ArrayList<Tree.Node> rightResult = LCANaiveHelper(tree.head,target2, new ArrayList<>());

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

        result.sort((Map.Entry<Map.Entry<Integer,Integer>, Double> o1, Map.Entry<Map.Entry<Integer,Integer>, Double> o2) -> {
                return o1.getValue().compareTo(o2.getValue());
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

    public int jAndS(String j, String s){
        Map<Character,Integer> jMap = new HashMap<>();
        int result = 0;
        for(int i = 0; i < j.length(); i ++){
            jMap.put(j.charAt(i),0);
        }

        for(int i =0; i < s.length(); i ++){
            char c = s.charAt(i);
            if(jMap.containsKey(c)){
                result++;
            }
        }
        return result;
    }

    public int numOfCombination(String[] input){
        Set resultSet  = new HashSet<String>();
        String[] morse = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        for(String i : input){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < i.length(); j ++){
               char c = Character.toLowerCase(i.charAt(j));
               int asciiCode = (int)c - 97;
               sb.append(morse[asciiCode]);
            }
            resultSet.add(sb.toString());
        }
        return resultSet.size();
    }

    public List<Integer> selfDividingNumbers(int left, int right){

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = left; i <= right; i ++){
            int number = i;
            int remainder = number % 10;
            boolean selfDiving = true;

            while(number > 0){ //if number = number %10, that's the last digit;
                boolean endWithZero = remainder == 0;
                if(!endWithZero && i % remainder == 0){ //check if the digit is 0 and i / digit == 0
                    number = number / 10;
                    remainder = number % 10;
                }else{
                    selfDiving = false;
                    break;
                }
            }
            if(selfDiving){
                result.add(i);
            }
        }
        return result;
    }

    public List<String> subdomainVisits(String[] cpdomains) {

        Map<String,Integer> result = new HashMap<>();

        for(String s : cpdomains){
            String[] split = s.split(" ");
            String domains = split[1];
            int visitNumber = Integer.parseInt(split[0]);
            String[] domainSplit = domains.split("\\."); //escape special character in java \\

            //handle google.mail.com, mail.com
            for(int i = 0; i < domainSplit.length ; i ++){
                    String[] temp = Arrays.copyOfRange(domainSplit,i,domainSplit.length);
                    String key = String.join(".",temp);
                    //System.out.println(key);
                    result.put(key, result.getOrDefault(key,0) + visitNumber);
            }
        }

        Iterator<Map.Entry<String,Integer>> it = result.entrySet().iterator();
        List<String> resultList = new ArrayList<>();
        while(it.hasNext()){
            Map.Entry<String,Integer> record = it.next();
            String tempString = record.getValue().toString() + " " + record.getKey();
            resultList.add(tempString);
        }
        return resultList;
    }

    public int[] numberOfLines(int[] widths, String S) {
        if(!S.isEmpty()){
            int numOfLines = 1;
            int numOfWidth = 0;

            for(int i = 0; i < S.length(); i ++){
                char c = Character.toLowerCase(S.charAt(i));
                int ascii = (int)c - 97;
                int charWidth = widths[ascii];
                int newWidth = numOfWidth + charWidth;

                if( newWidth > 100){
                    numOfWidth = charWidth;
                    numOfLines ++;
                }else{
                    numOfWidth = newWidth;
                }
            }

            return new int[]{numOfLines,numOfWidth};
        }else{
            return new int[]{0,0};
        }
    }

    public String reverseString(String s) {

        int startPointer = 0;
        int endPointer = s.length() - 1;
        char[] charArray = s.toCharArray();

        while(startPointer < endPointer){
            char temp = charArray[startPointer];
            charArray[startPointer] = charArray[endPointer];
            charArray[endPointer] = temp;
            startPointer ++;
            endPointer --;
        }
        return String.valueOf(charArray);
    }

    public String reverseWords(String s) {

        String[] words = s.split(" ");
        for(int i =0; i < words.length; i ++){
            String reverseWord = reverseString(words[i]);
            words[i] = reverseWord;
        }
        return String.join(" ",words); //mkString
    }

    public Boolean isSubsequence(String a, String b){

        int aStarter = 0;
        int bStarter = 0;
        Boolean finishA = false;

        while(aStarter < a.length() && bStarter < b.length()){
            char aChar = a.charAt(aStarter);
            char bChar = b.charAt(bStarter);

            if(aChar == bChar){
                if(aStarter == a.length() - 1){
                    finishA = true;
                }
                aStarter++;
            }
            bStarter++;
        }

        if(finishA && aStarter == a.length() ){
            return true;
        }else{
            return false;
        }

    }

    public int[] longestIncreasingSubsequenceNaive(int[] input){
        //set up base case

        if(input.length == 0){
            return new int[]{};
        }else{
            List<Integer> inputCurrent = new ArrayList<>();
            inputCurrent.add(input[0]);
            return helperLIS(input, inputCurrent,1);
        }
    }
    public int[] helperLIS(int[] input, List<Integer> current, int index){
        if(index == input.length){
            return current.stream().mapToInt(i -> i).toArray();
        }

        int[] doesntChange = helperLIS(input,current,index +1);
        List<Integer> addedElement = new ArrayList<>(current);//copy arraylist
        int newElement = input[index];
        int lastElement = current.get(current.size() - 1);
        if(newElement >= lastElement) {
            addedElement.add(input[index]);
            int[] changed = helperLIS(input,addedElement,index + 1);
            if(doesntChange.length > changed.length){
                return doesntChange;
            }else{
                return changed;
            }
        }else{
            return doesntChange;
        }
    }

    public int longestIncreasingSubsequenceDP(int[] input){
        //set up base case

        if(input.length == 0){
            return 0;
        }else if(input.length ==1){
            return 1;
        }else{
            int[] eachMax = new int[input.length];
            for(int i = 0; i < eachMax.length; i ++){
                eachMax[i] = Integer.MIN_VALUE;
            }

            eachMax[0] = 1;

            for(int i = 1; i < eachMax.length; i ++){
                int currentIMaxCount = Integer.MIN_VALUE;
                for(int j = 0; j < i ; j ++){
                    if(input[i] > input[j]){
                        if(eachMax[j] + 1 > currentIMaxCount){
                            currentIMaxCount = eachMax[j] + 1;
                        }
                    }
                }
                eachMax[i] = currentIMaxCount;
            }
            return eachMax[input.length - 1];
        }
    }

    public int baseBall(String[] ops){
        Stack<String> stock = new Stack<>();
        int result = 0;
        for(String s: ops){
            switch(s){
                case ("+"): {
                    String score1Str = stock.pop();
                    String score2Str = stock.pop();
                    int score1 = Integer.parseInt(score1Str); //string to int
                    int score2 = Integer.parseInt(score2Str);
                    int score = score1 + score2;
                    String score3 = Integer.toString(score); //int to char
                    stock.push(score2Str);
                    stock.push(score1Str);
                    stock.push(score3);
                    break;
                }
                case ("D"): {
                    int score = Integer.parseInt(stock.peek());
                    stock.push(Integer.toString(score * 2));
                    break;
                }
                case ("C"): {
                    stock.pop();
                    break;
                }
                default: {
                    stock.push(s);
                    break;
                }
            }
        }

        while(!stock.empty()){
            result += Integer.parseInt(stock.pop());
        }
        return result;
    }

    public TreeNode trimBST(TreeNode root, int L, int R){

        return null;
    }

    public int distributeCandies(int[] candies){

        Map<Integer, Boolean> candiesKind = new HashMap<>();
        for(int i: candies){
            candiesKind.put(i,true);
        }

        int size = candiesKind.size();
        if(size > candies.length /2){
            return candies.length/2;
        }else{
            return size;
        }
    }

    public boolean isToeplitzMatrix(int[][] matrix){
        for(int startOfDia = matrix.length - 1; startOfDia >=0; startOfDia --){
            int a = startOfDia;
            int b = 0;

            while(a + 1< matrix.length && b + 1< matrix[0].length){
                if(matrix[a][b] != matrix[a+1][b+1]){
                    return false;
                }else{
                    a ++;
                    b ++;
                }
            }
        }
        for(int startOfDia = matrix[0].length - 1; startOfDia >=0; startOfDia --){
            int a = 0;
            int b = startOfDia;

            while(a + 1< matrix.length && b + 1< matrix[0].length){
                if(matrix[a][b] != matrix[a+1][b+1]){
                    return false;
                }else{
                    a ++;
                    b ++;
                }
            }
        }
        return true;
    }

    /**
     * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
     * We would like to convert the sentence to `Goat Latin`
     *
     * The rules of Goat Latin are as follows:
     *      if a word begins with a vowel(a,e,i,o,i),append "ma" to the end of the word
     *      apple becmoes applema
     *
     *      if a word begins with a consonant(not a vowel), remove the first letter and append it to the end, then add "ma"
     *      goat becomes oatgma
     *
     *      Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
     *      For example, the first world gets "a" added to the end, the second word gets "aa" added to the end and so on
     *
     *      Return the final sentence representing the conversion from S to Goat Latin
     *
     *      I speak Goat Latin
     *
     *      Imaa peaksmaaa oatGmaaaa atinLmaaaaa
     *
     *      The quick brown fox jumped over the lazy dog
     *      heTmaa uickqmaa rownbmaaaa oxfmaaaa umpedjmaaaaa overmaaaaaaa hetmaaaaaaaa azlmaaaaaaaaa ogdmaaaaaaaaaa
     */

    public String toGoatLatin(String S){

        String[] words = S.split(" ");
        for(int i =0; i < words.length; i ++){
            char firstChar = words[i].charAt(0);
            Set<Character> vowelSet = new HashSet<>();
            vowelSet.add('a');
            vowelSet.add('e');
            vowelSet.add('i');
            vowelSet.add('o');
            vowelSet.add('u');

            if(vowelSet.contains(firstChar)){
                words[i] += "ma";
            }else{
                words[i] = words[i].substring(1,words[i].length()) + firstChar  + "ma";
            }

            words[i] += new String(new char[i + 1]).replace("\0", "a"); //string repeat, repeat string
        }
        return String.join(" ",words);
    }

    /**
     * We are given two strings A and B
     * A shift on A consists of taking string A and moving the leftmost character to the rightmost position. For example
     * if A = 'abcde', then it will be 'bcdea' after one shift on A. Return True if and only if A can become B after
     * some numbers of shifts on A.
     *
     * Example 1:
     * Input: A = 'abcde', B = 'cdeab'
     * Output: True
     *
     * Example 1:
     * Input A= 'abcde', B='cdeab'
     * True
     *
     * Example 2:
     * Input A = 'abcde', b = 'abcde'
     * false
     */

    public boolean rotateString(String A, String B){

        if(A.length() != B.length()){
            return false;
        }else{
            int aPointer = 0;
            char firstCharB = B.charAt(0);

            for(int i = 0; i < A.length(); i ++){
                if(A.charAt(i) == firstCharB){
                    aPointer = i;
                    break;
                }
            }

            if(aPointer == 0){
                return false;
            }else{

                String firstHalfRev = A.substring(0, aPointer);// String Reversed
                String secondHalf = A.substring(aPointer,A.length());

                return (secondHalf+firstHalfRev).equals(B);
            }
        }
    }

    /**
     * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
     * Return a list of all possible strings we could create
     *
     * S = "a1b2"
     * ["a1b2","a1B2","A1b2","A1B2"]
     *
     * S = "3z4"
     * ["3z4',"3Z4"]
     *
     * S = "12345"
     * ["12345"]
     *
     *
     */

    public List<String>letterCasePermutation(String S){ //to be confirmed
        int pointer = 0;
        ArrayList<String> result = new ArrayList<>();
        result.add(S.toLowerCase());

        while(pointer < S.length()){
            char pointerChar = result.get(0).charAt(pointer);
            if(Character.isAlphabetic(pointerChar)){ //check if character is letter digit
                int resultSize = result.size();
                for(int i =0; i < resultSize; i ++){ //result.size() change for each i loop
                    String current = result.get(i);
                    String newString = current.substring(0,pointer) + Character.toUpperCase(pointerChar)  // change character in string
                            + current.substring(pointer +1, current.length());
                    result.add(newString);
                }
            }
            pointer ++;
        }
        return result;
    }

    /**
     * Given an array of integers where 1 <= a[i] <= n (n = size of array), some elemnts appear twice and others appear once
     *
     *
     * Find all the elemnts of[1,n] inclusive that do not appear in this array
     *
     * Do this without extra space and O(n)
     *
     * Input [4,3,2,7,8,2,3,1]
     * Output [5,6]
     */
    public List<Integer> findDisappearedNumbers(int[] nums){

        int duplicatePointer = nums.length - 1 ;
        int currentPoitner = 0;

        while(currentPoitner <= duplicatePointer){
            int currentValue = nums[currentPoitner];
            if(currentValue == currentPoitner + 1 || currentValue == 0){
                currentPoitner ++;
            }else{
                int swapTarget = nums[currentValue - 1]; //i have a 4 in my pocket, hence looking at value of nums[3]
                if( swapTarget != currentValue){
                    nums[currentPoitner] = swapTarget;
                    nums[currentValue - 1] = currentValue;
                }else{ //we have seen a duplicate
                    boolean swapped = false;
                    while(!swapped){
                        int duplicateSwapTarget = nums[duplicatePointer];
                        if(duplicateSwapTarget != duplicatePointer + 1){
                            nums[currentPoitner] = duplicateSwapTarget;
                            nums[duplicatePointer] = 0;
                            swapped = true;
                        }
                        duplicatePointer --;
                    }
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] != i + 1){
                result.add(i+1);
            }
        }

        return result;
    }

    public int maxAreaOfIsland(int [][] grid){

        int maxArea = 0;
        for(int y = 0; y < grid.length; y ++){
            for(int x = 0; x < grid[0].length; x ++){
                Stack<KeyValuePair> checkStack = new Stack<>();
                int area = 0;
                checkStack.add(new KeyValuePair(y,x));
                    while(!checkStack.isEmpty()){
                        KeyValuePair currentLocation = checkStack.pop();
                        int currentX = currentLocation.x;
                        int currentY = currentLocation.y;
                        if(grid[currentY][currentX] == 1){
                            area ++;
                            grid[currentY][currentX] = 2;
                            if(currentY - 1 >= 0){
                                KeyValuePair upper = new KeyValuePair(currentY - 1,currentX);
                                checkStack.add(upper);
                            }
                            if(currentY + 1 <= grid.length - 1){
                                KeyValuePair lower = new KeyValuePair(currentY + 1,currentX);
                                checkStack.add(lower);
                            }
                            if(currentX - 1 >= 0){
                                KeyValuePair left = new KeyValuePair(currentY,currentX - 1);
                                checkStack.add(left);
                            }
                            if(currentX + 1 <= grid[0].length - 1){
                                KeyValuePair right = new KeyValuePair(currentY,currentX + 1);
                                checkStack.add(right);
                            }
                    }
                }
                if(area > maxArea){
                        maxArea = area;
                }
            }
        }
        return maxArea;
    }

    class KeyValuePair{
        int x ;
        int y;
        public KeyValuePair(int y, int x){
            this.x = x;
            this.y = y;
        }
    }

    /**
     * Given a string S and a character C, return an array of integers represnting the shortest distance from the
     * character C in the string
     *
     * Input: S = "loveleetcode", C = 'e'
     *
     * Output = [3,2,1,0,1,0,0,1,2,2,1,0]
     *
     *
     *
     */

    public int[] shortestToChar(String S, char C){
        //Be careful with loop condition plus 1 minus 1, and start end next iterator

        int previousTarget = 0;
        int[] result = new int[S.length()];

        int leftPointer = 0;
        int rightPointer = -S.length();

        for(int i = 0; i < S.length(); i ++){
            if(S.charAt(i) == C){
                leftPointer = rightPointer;
                rightPointer = i;
                for(int j = previousTarget; j < rightPointer; j ++){
                    if(j - leftPointer < rightPointer - j){
                        result[j] = j -leftPointer;
                    }else{
                        result[j] = rightPointer - j;
                    }
                }
                previousTarget = i;
            }
        }

        for(int i = previousTarget;i < S.length(); i ++){
            result[i] = i - previousTarget;
        }
        return result;
    }

    /**
     * You have a list of points in the plane. Return the area of the larget triangle that can be formed by any
     * of 3 the points
     *
     * Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
     * Output 2
     */

    /**combination = order doesnt matter. Permutation = order matters. Combination lock should be permutation lock
     *  Number of combination = n!/r!(n-r)!  , permutation = n!/(n-r)!
     *
     *  triagle area
     */

    public double largestTriangleArea(int[][] points){
        double maxArea = 0;
        for(int i = 0; i < points.length; i ++){
            for(int j = i + 1; j < points.length;j ++){
                for(int k = j + 1; k <points.length;k++){
                    int aX = points[i][0];
                    int aY = points[i][1];
                    int bX = points[j][0];
                    int bY = points[j][1];
                    int cX = points[k][0];
                    int cY = points[k][1];

                    //https://www.mathopenref.com/coordtrianglearea.html
                    //|A x ( B y − C y ) + B x ( C y − A y ) + C x ( A y − B y ) / 2|\
                    double math = aX * (bY - cY) + bX * (cY - aY) + cX * (aY - bY);
                    double area = Math.abs(math)/2; //be careful Math.asb maybe take int return int
                    if(area > maxArea){
                        maxArea = area;
                    }
                }
            }
        }
        return maxArea;
    }

    /**
     * Given an array of integers, find if the array contains any duplicates
     *
     * Your function should return true if any value appears at least twice in the array, and it should return
     * false if every element is distinct
     *
     * Bit solution exists i think
     */

    public boolean containDuplicate(int[] input){
        HashSet<Integer> temp= new HashSet<>();
        for(int i : input){
            if(temp.contains(i)){
                return true;
            }else{
                temp.add(i);
            }
        }
        return false;
    }

    /**
     * Given a non-empty integer array of size n, find the minimum number of moves required to make
     * all array elements equal where a move is incrementing n -1 elements by 1
     *
     * [1,2,3] => [2,3,3] => [3,4,3] => [4,4,4]
     *  [1, integer max]
     */

    public int minMoves(int[] nums){

        if(nums.length == 0){
            return 0;
        }

        boolean allSame= false;
        int index = 0;

        while(!allSame){
            allSame  = true;
            int maxIndex = 0;
            int previous = nums[0];
            for(int i = 0; i < nums.length; i ++){
                if(nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                }
                if(nums[i] != previous){
                    allSame = false;
                }
            }
            if(allSame){
                break;
            }
            for(int i = 0; i < nums.length; i ++){
                if(i != maxIndex){
                    nums[i] ++;
                }
            }
            index ++;
        }
        return index;
    }

    /**
     * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific
     * target number
     *
     * The function twoSum should return indices of the two numbers such that they add up to the target
     * where index 1 must be less than index 2
     *
     * non zero-base solution
     * each input owuld have exactly one solution and you may not use the same element twice
     *
     * [2,7,11,15]
     */

    public int[] twoSum(int[] numbers, int target){
        int big = numbers.length - 1;
        int small = 0;
        boolean found = false;

        while(!found && small <= big){

           if(numbers[big] > target){
               big --;
           }else if(numbers[big] + numbers[small] == target){
               found = true;
           } else{
               int tempSum = numbers[big] + numbers[small];
               if(tempSum > target){
                   big --;
               }else{
                   small ++;
               }
           }
        }
        return new int[]{small,big};
    }

    /** Givena a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum
     * frequency of any one of its elements
     *
     * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same
     * degree as nums
     *
     * Input: [1,2,2,3,1]
     * Output: 2
     *
     * [2,2] vs [1,2,2] vs [2,2,3] vs [2,2,3,1] vs [1,2,2,3] vs [1,2,2,3,1]
     *
     * Input: [1,2,2,3,1,4,2]
     * Output: 6
     *
     */

    public int findShortestSubArray(int[] nums){

        //first put all elements and index in hashmap

        HashMap<Integer, ArrayList<Integer>> maxFreq = new HashMap<>();
        for(int i = 0; i < nums.length; i ++){
            if(maxFreq.containsKey(nums[i])){
                maxFreq.get(nums[i]).add(i);
            }else{
                ArrayList<Integer> indexs = new ArrayList<>();
                indexs.add(i);
                maxFreq.put(nums[i], indexs);
            }
        }

        //find the max frequent
        int currentMinDisatnce = nums.length;
        Iterator it = maxFreq.entrySet().iterator();
        int max = 0;

        while(it.hasNext()){
            Map.Entry<Integer,ArrayList<Integer>> next = (Map.Entry)it.next(); //hashmap iterator
            if(next.getValue().size() >= max){
                ArrayList<Integer> currentArrayList = next.getValue();
                max = currentArrayList.size();
                int thisSubArrayMin = currentArrayList.get(currentArrayList.size() - 1) - currentArrayList.get(0) + 1;
                if(thisSubArrayMin < currentMinDisatnce){
                    currentMinDisatnce = thisSubArrayMin;
                }
            }
        }
        return currentMinDisatnce;
    }
}

