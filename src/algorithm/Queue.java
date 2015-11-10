package algorithm;

/**
 * Created by chenxiaoxue on 11/10/15.
 */
public class Queue {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public Queue(int size){
        maxSize = size;
        queArray = new long[maxSize];
        front =0;
        rear=-1;
        nItems =0;
    }

    public boolean insert(long j){
        if(isFull()) return false;
        if (rear == maxSize-1){
            rear = -1;
        }
        rear++;
        queArray[rear] = j;
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

    public long peekFront(){
        return queArray[front];
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
