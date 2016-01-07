//package algorithm;
///**
//How long is the linked list loop?
//
//Problem Statement
//
//A loop in a linked list occurs when following the linked list chain brings you back where you started. For example, link 1 points to link 2 which points to link 3 which points to link 1, to link 2, to link 3, to link 1...and you go round in an infinite loop. Each test case here involves a single-ended singly-linked list with a potential loop. Output the size of the loop, or 0 if there is no loop. In the above example, the loop is of size 3 (e.g. link 1, link 2, link 3...repeating forever).
//
//Input Format
//
//There is no input. The linked list is created automatically.
//
//
//Output Format
//
//Output an integer, either 0 if the linked list is empty or has no loop, or else >0 corresponding to the length of the loop in the linked list.
//
//
//Constraints
//
//0<=(links)<=100
//*/
//
//
//import java.util.*;
//
//public class SolutionLoopLength {
//    public static void main(String args[] ) throws Exception {
//        // This version uses System.out.println() to provide you with an easy to use
//        // example of where the input is taken from the Scanner and then used in the code.
//        // You will not need these in HackerRank and can be commented out.
//
//        Scanner myscanner = new Scanner(System.in);
//        System.out.println("Enter the number of links in this linked list >>");
//        int num = Integer.parseInt(myscanner.nextLine());
//        // Create an array of Link objects to actually hold the data for the linked list.
//
//        if ((num < 0) || (num > 100))
//        {
//            System.out.println("You have violated the input constraints - linked list must be 0 <= links <= 100");
//            System.exit(0);
//        }
//
//        Link[] array = new Link[num];
//
//        // We can add any string data we like
//        for(int i=0;i<num;i++){
//            System.out.println("Add item at element " + i);
//            array[i]=new Link(myscanner.nextLine());
//        }
//        // create the linked list conceptually by making the linkages between
//        // each of the link objects in the array next link
//        for (int i = 1; i < num; i++)
//        {
//            // The .next property of link is used to point to the Next link or element in the list
//            array[i-1].next = array[i];
//        }
//
//        System.out.println("Select any element (by index value) in the array of links");
//        int select=myscanner.nextInt();
//
//        System.out.println("Select any element (by index value) for this link to cause a loop. Type -1 for no link");
//        int next=myscanner.nextInt();
//
//        if(next!=-1){
//            array[select].next=array[next]; // using the .next property we create the loop
//        }
//
//        // Now create the actual LinkedList object by simply telling the LinkedList object where the first Link in the array is.
//
//        LinkedList mylist = new LinkedList();
//        if(num>0){
//            mylist.first=array[0];
//        }
//
//        if (next != -1)
//            System.out.println("The length of the loop is calculated as " + findLoopLength(mylist));
//        else {
//            System.out.println("0");
//            System.out.println("There as no loop specified by the user");
//        }
//    }
//
//    public static int findLoopLength(LinkedList mylist){
//        if(mylist.isEmpty()){
//            return(0);
//        }
//        Link slow=mylist.first;
//        Link fast = mylist.first;
//        int loopLength=0;
//        while(true){
//            if(slow.next!=null){
//                slow = slow.next;
//            }else{
//                loopLength=0;
//                break; //no loop
//            }
//
//            if(fast.next!=null&&fast.next.next!=null){
//                fast = fast.next.next;
//            }else{
//                loopLength=0;
//                break; //no loop
//            }
//
//            if(fast == slow){
//                Link temp = slow;
//                while(true){
//                    temp=temp.next;
//                    loopLength++;
//                    if(temp==fast){
//                        break;
//                    }
//                }
//                break; //got loop
//            }
//
//        }
//        return loopLength;
//    }
//
//
//    public static int findLoopLength(LinkedList mylist){
//        if(mylist.isEmpty()){
//            return(0);
//        }
//        // Create a large enough array to hold the Links from our Linked List
//
//        Link[] checklist = new Link[100];
//        // Use this counter to say where we are in the checkList array
//
//        int counter=0;
//
//        // go to the start of the LinkedList - get the first element/Link
//        Link forwards = mylist.first;
//        // Stay in the while loop provided we do not reach the end of the List
//        // so we keep accessing the .next property
//
//        while(forwards.next!=null){
//            // forwards currently holds the current item in the LinkedList
//            // that we are looking at.
//            // store it in the checkList.
//
//            checklist[counter]=forwards;
//
//            forwards=forwards.next;
//            // Now go back over the checkList and see if
//            for(int i=0;i<counter;i++){
//                // check the checklist to see if you can see forwards in the checklist.
//                // This means as before that there is a loop
//                if(forwards==checklist[i]){
//                    return(counter+1);
//                }
//            }
//
//            counter++;
//        }
//        return(0);
//    }
//}
//
//
//class Link{
//    public String data;
//    public Link next;
//
//    public Link(String input){
//        data=input;
//        next=null;
//    }
//}
//
//class LinkedList {
//    public Link first;
//
//    public LinkedList( ){
//        first=null;
//    }
//
//    public boolean isEmpty( ){
//        return (first==null);
//    }
//
//    public void insertHead(Link insert){
//        if(isEmpty()){
//            first=insert;
//        }else{
//            insert.next=first;
//            first=insert;
//        }
//    }
//}
//
//
//
