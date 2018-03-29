import java.util.ArrayList;
/**
 * Created by ctam on 2/23/18.
 */
public class LinkedLists {


    private LinkedNode head;

    public LinkedLists(){
        this.head = null;
    }

    public LinkedLists(LinkedNode head){
        this.head = head;
    }

    public LinkedLists(int[] input){

        this.head = new LinkedNode(input[0]);
        LinkedNode temp = this.head;
        for(int i = 1; i < input.length -1; i++){
            temp.setTail(new LinkedNode(input[i]));
            temp = temp.getTail();
        }
    }

    public ArrayList<Integer> getValues(){
        LinkedNode temp = head;
        ArrayList<Integer> result = new ArrayList<Integer>();
        while(temp.hasTail()){
            result.add(temp.getValue());
            temp = temp.getTail();
        }
        return result;
    }

    public void DeleteNode(LinkedNode node){
        if(head != node){
            LinkedNode tempPrev = head;
            while(tempPrev.hasTail()){
                LinkedNode tempHead = tempPrev.getTail();
                if(tempHead == node){
                    if(tempHead.hasTail()){
                        tempPrev.setTail(tempHead.getTail());
                    }else{
                        tempPrev.setTail(null);
                    }
                    break;
                }else{
                    tempPrev = tempHead;
                }
            }
        }else{
            head = head.getTail();
        }
    }

    public void AddNode(LinkedNode node){
        LinkedNode temp = head;

        while(temp.hasTail()){
            temp = temp.getTail();
        }
        temp.setTail(node);
    }


    public String toString(){
        String result = "";
        LinkedNode temp = head;
        while(temp.hasTail()){
            result += temp.getValue() + " -> ";
            temp = temp.getTail();
        }
        result += temp.getValue();
        return result;
    }
}
