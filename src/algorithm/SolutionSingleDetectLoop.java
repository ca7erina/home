package algorithm; /**

 Solution Code - Peter Mooney - November 2015

 Text of problem statement from Phil Maguire

 What link causes the loop?

 Problem Statement

 A loop in a linked list occurs when following the linked list chain brings you back where you started. For example, link 1 points to link 2 which points to link 3 which points to link 1, to link 2, to link 3, to link 1...and you go round in an infinite loop. Each test case here involves a single-ended singly-linked list with a potential loop. Output the data of the link whose pointer has caused the linked list to loop back to a previous point. In the above example, it would be whatever data is stored in link 3.

 Input Format

 There is no input. The linked list is created automatically.

 Output Format

 Output the String stored in the link which causes the list to loop back to a previous link. If there is no loop output "OK". If the linked list is empty output "empty".


 Constraints

 0<=(links)<=100
 */


import java.util.*;

public class SolutionSingleDetectLoop {
    public static void main(String args[] ) throws Exception {

        // This version uses System.out.println() to provide you with an easy to use
        // example of where the input is taken from the Scanner and then used in the code.
        // You will not need these in HackerRank and can be commented out.

        Scanner myscanner = new Scanner(System.in);
        System.out.println("Enter the number of links in this linked list >>");
        int num = Integer.parseInt(myscanner.nextLine());
        // Create an array of Link objects to actually hold the data for the linked list.

        if ((num < 0) || (num > 100))
        {
            System.out.println("You have violated the input constraints - linked list must be 0 <= links <= 100");
            System.exit(0);
        }

        Link[] array = new Link[num];

        // We can add any string data we like
        for(int i=0;i<num;i++){
            System.out.println("Add item at element " + i);
            array[i]=new Link(myscanner.nextLine());
        }
        // create the linked list conceptually by making the linkages between 
        // each of the link objects in the array next link
        for (int i = 1; i < num; i++)
        {
            // The .next property of link is used to point to the Next link or element in the list
            array[i-1].next = array[i];
        }

        System.out.println("Select any element (by index value) in the array of links");
        int select=myscanner.nextInt();

        System.out.println("Select any element (by index value) for this link to cause a loop. Type -1 for no link");
        int next=myscanner.nextInt();

        if(next!=-1){
            array[select].next=array[next]; // using the .next property we create the loop
        }

        // Now create the actual LinkedList object by simply telling the LinkedList object where the first Link in the array is.

        LinkedList mylist = new LinkedList();
        if(num>0){
            mylist.first=array[0];
        }

        System.out.println("The element which caused the loop is " + findloop(mylist));
    }

    public static String findloop(LinkedList mylist){
        if(mylist.isEmpty()){
            return("empty");
        }
        // Create an array to essentially store our current list so we can keep checking it

        Link[] checklist = new Link[100];

        int counter=0;
        // This is essentially our 'counter' to move up and down the List

        Link forwards = mylist.first;

        // Stay in the while loop as long as we are not at the end of the list

        while(forwards.next!=null){
            // store the current item in the list. 
            checklist[counter]=forwards;

            Link currentLinkInList =forwards;
            // get the data from the current item

            //move to the next item in the list. 
            forwards=forwards.next;
            // we need to check back over the rest of the list to see if a loop is introduced
            for(int i=0;i<counter;i++){
                // if we look back at the rest of the list in checklist
                // and find that forwards is equal to it.
                // Then we have found the problem. This is a loop

                if(forwards==checklist[i]){
                    // so it's actually the current item which is causing the issue. Remember
                    // it's a Link object - so use the .data property to access it's value
                    // Use the return statement here to exit the while loop and the method.
                    return(currentLinkInList.data);
                }
            } // end for loop
            counter++; // increment the counter for the checkList
        }
        return("OK");
    }
}


class Link{
    public String data;
    public Link next;

    public Link(String input){
        data=input;
        next=null;
    }
}

class LinkedList {
    public Link first;

    public LinkedList( ){
        first=null;
    }

    public boolean isEmpty( ){
        return (first==null);
    }
    public void insertHead(Link insert){
        if(isEmpty()){
            first=insert;
        }else{
            insert.next=first;
            first=insert;
        }
    }
}



