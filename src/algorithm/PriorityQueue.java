package algorithm;

/**
 * Created by chenxiaoxue on 11/16/15.
 * A priority queue is a queue where items don’t just join
 * at the rear, they are slotted into the queue according to
 * their priority
 */
public class PriorityQueue {

    private int maxSize;
    private long[] queArray;
    private int front;
//    private int rear;
    private int nItems;


    public PriorityQueue(int size){
        maxSize = size;
        queArray = new long[maxSize];
        front =0;
//        rear=-1;
        nItems =0;
    }

    public boolean insert(long item){
        if(isFull()) return false;

        if (nItems==0){
            queArray[0]=item;

        }else {
            int i=nItems-1; //lastItem index
            while(i>=0&&queArray[i]>item){
                queArray[i+1]=queArray[i];
                i--;
            } //i is the index of the item <= item
            queArray[i+1] = item;
        }

        nItems++;
        return true;



    }

    public long remove(){
        if(isEmpty()){
            return Long.parseLong(null);
        }
        long temp = queArray[front];
        front++;
        if(front ==maxSize){
            front =0;
        }
        nItems--;
        return temp;
    }


    public boolean isFull(){
        return nItems==maxSize;
    }

    public boolean isEmpty(){
        return nItems==0;
    }

    public int size(){
        return nItems;
    }


}

class TestPQ{

    public static void main(String args[]){
        int length = 10;
        PriorityQueue pq = new PriorityQueue(length);

        for(int i=0;i<length;i++){
            int random = (int)(Math.random()*100);

            pq.insert(random);
            System.out.println("insert:"+random);
        }

        while(!pq.isEmpty()){
            System.out.println("remove:"+pq.remove());

        }


    }

}
