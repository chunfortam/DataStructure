/**
 * Created by ctam on 2/23/18.
 */
public class LinkedNode {

    private int value;
    private LinkedNode tail;

    public LinkedNode(){
        this.value = 0;
        this.tail = null;
    }

    public LinkedNode(int value, LinkedNode tail){
        this.value = value;
        this.tail = tail;
    }

    public LinkedNode(int value){
        this.value = value;
        this.tail = null;
    }

    public void setValue(int value){

        this.value = value;
    }

    public void setLinkedNode(LinkedNode tail){

        this.tail = tail;
    }

    public int getValue(){
        return value;
    }

    public LinkedNode getTail(){
        return tail;
    }

    public void  setTail(LinkedNode tail){
        this.tail = tail;
    }

    public boolean hasTail(){
        if(this.tail == null){
            return false;
        }
        return true;
    }

    public int compareTo(LinkedNode node){
        return Integer.compare(value,node.getValue());
    }
}
