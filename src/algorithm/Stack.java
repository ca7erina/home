package algorithm;

import org.junit.Test;

/**
 * Created by chenxiaoxue on 11/10/15.
 */
public class Stack {
    private int maxSize;
    private long[] stackArray;
    private int top;


    public Stack(int size){
        maxSize = size;
        stackArray = new long[size];
        top =-1;
    }

    public void push(long j){
        top++;
        stackArray[top]=j;
    }

    public long pop(){
        return stackArray[top--];
    }

    public long peek(){
        return stackArray[top];
    }

    public boolean isEmpty(){
        return(top == -1);
    }

    public boolean isFull(){
        return top == (maxSize-1);
    }

    public void makeEmpty(){
        top = -1;
    }


}
