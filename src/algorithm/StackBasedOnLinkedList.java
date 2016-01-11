//package algorithm;
//
//import algorithm.linkedList.LinkedList;
//
///**
//* Created by chenxiaoxue on 1/7/16.
//*/
//public class StackBasedOnLinkedList {
//
//    public LinkedList list;
//
//    public StackBasedOnLinkedList() { // constructor
//        list = new LinkedList();
//    }
//
//    public void push(int data) { // put item on top of stack
//        list.insertHead(data);
//    }
//
//    public int pop() { // take item from top of stack
//        return list.deleteHead().data;
//    }
//
//
//}
//
//class StackBasedOnLinkedListTest {
//
//    public static void main(String args[]){
//        StackBasedOnLinkedList theStack = new StackBasedOnLinkedList(10);
//        theStack.push(20);
//        theStack.push(40);
//        theStack.push(60);
//        theStack.push(80);
//        while(!theStack.isEmpty()){
//            System.out.println(theStack.pop());
//
//        }
//    }
//}