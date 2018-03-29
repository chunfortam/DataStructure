/**
 * Created by ctam on 3/19/18.
 */
import java.util.Random;
public class Tree {


    Node head ;

    public Tree(int value){
        head = new Node(value);
    }
    public Tree(Node node){
        head = node;
    }

    public void setNode(Node node){
        head = node;
    }

    public void addNode(Node[] list){
        Node temp = head;
        for(Node i : list){
            while(temp!= null){
                if(temp.getLeft() == null){
                    temp.setLeft(i);
                }else if(temp.getRight() == null){
                    temp.setRight(i);
                }else{
                    Random rand = new Random();
                    if(rand.nextBoolean()){
                        temp = temp.getLeft();
                    }else{
                        temp=temp.getRight();
                    }
                }
            }
        }
    }

    public Node getNode(){
        return head;
    }

    class Node {

        private Node left = null;
        private Node right = null;

        public Node(int value, int left, int right){
            this.value = value;
            this.left = new Node(left);
            this.right = new Node(right);
        }

        public Node(){

        }

        private int value = 0;

        public void setLeft(Node left){
            this.left = left;
        }
        public void setRight(Node right){
            this.right = right;
        }
        public Node getLeft(){
            return left;
        }
        public Node getRight(){
            return right;
        }
        public boolean hasLeft(){
            if(left != null){
                return true;
            }else{
                return false;
            }
        }

        public boolean hasRight(){
            if(right != null){
                return true;
            }else{
                return false;
            }
        }
        public Node(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }

        @Override
        public String toString(){
            return Integer.toString(value);
        }

        public Boolean compareTo(Node b){
            return  this.value == b.getValue();
        }

    }


}
