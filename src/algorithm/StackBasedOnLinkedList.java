package algorithm;

import algorithm.linkedList.LinkedList;

/**
 * Created by chenxiaoxue on 1/7/16.
 */
public class StackBasedOnLinkedList {

    private LinkedList list;

    public StackBasedOnLinkedList() { // constructor
        list = new LinkedList();
    }

    public void push(int data) { // put item on top of stack
        list.insertHead(data);
    }

    public int pop() { // take item from top of stack
        return list.deleteHead().data;
    }
}
