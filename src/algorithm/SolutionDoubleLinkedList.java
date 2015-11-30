package algorithm; /**
 *
 * PETER MOONEY
 * NOVEMBER 2015
 * EXAMPLE FROM TUTORIAL
 * WITH EXTRA COMMENTS AND SYSTEM.OUT.PRINTLN FOR ILLUSTRATION PURPOSES. 
 *
 Is the linked list properly linked?

 Problem Statement

 Check the double-ended doubly-linked list to see if it is properly linked up. The main method is already completed, you simply have to write the check() method which reports to the main method.

 Input Format

 There is no input. The linked list is created automatically.

 Output Format

 Output "TRUE" if the linked list is properly connected and "FALSE" otherwise. If the linked list is empty output "empty".


 Constraints

 0<=(links)<=100

 */

import java.util.*;

public class SolutionDoubleLinkedList {
    public static void main(String args[] ) throws Exception {
        Scanner myscanner = new Scanner(System.in);
        System.out.println("Enter the number of links in this doubly linked list (choose 5 for this example) >>");
        int num = Integer.parseInt(myscanner.nextLine());
        // Create an array of Link objects to actually hold the data for the linked list.

        if ((num < 0) || (num > 100))
        {
            System.out.println("You have violated the input constraints - linked list must be 0 <= links <= 100");
            System.exit(0);
        }

        Link[] array = new Link[num];


        for (int i = 0; i < num; i++){
            // Create the link at position i in the Link Array
            // Put some string data from scanner into it.
            System.out.println("Enter in another piece of data for another link");
            array[i] = new Link(myscanner.nextLine());
        }
        // Setup the double linked list - next and previous.
        // some extra information just to make it clear how the list is being linked

        for (int i = 0; i < num; i++)
        {
            int nextI = i + 1;
            int prevI = i - 1;

            if (nextI <= num -1)
            {
                array[i].next = array[nextI];
                System.out.println("\t Link[" + i + "] forward to [" + nextI + "]");
            }
            if (prevI >= 0)
            {
                array[i].previous = array[prevI];
                System.out.println("\t Link[" + i + "] backward to [" + prevI + "]");
            }


        }

        // HERE WE MAKE THE LOOP IN THE DOUBLE LINKED LIST

        // we create a NEXT/forward problem.
        array[3].next = array[0]; // connect Link[4] forward/next to the Link[0]

        // Setup the double linked list data structure here. 
        LinkedList mylist = new LinkedList();
        if(num>0){
            mylist.first=array[0];
            mylist.last=array[num-1];
        }
        System.out.println(check(mylist));
    }

    public static String check(LinkedList mylist){
        if(mylist.isEmpty()){
            return("empty");
        }
        Link[] checklist = new Link[100];
        Link forwards = mylist.first;

        int counter1=0;
        while(forwards.next!=null){

            checklist[counter1]=forwards;
            forwards=forwards.next;
            for(int i=0;i<counter1;i++){

                if(forwards==checklist[i]){
                    System.out.println("Link forward  to Link[" + i + "," + checklist[i].data + "] is incorrect");;
                    return("FALSE");
                }
            }
            counter1++;
        }
        Link[] checklist2 = new Link[100];
        int counter2=0;
        Link backwards = mylist.last;

        while(backwards.previous!=null){
            checklist2[counter2]=backwards;
            backwards=backwards.previous;
            for(int i=0;i<counter2;i++){

                if(backwards==checklist2[i]){
                    System.out.println("Link backwards  to Link[" + i + "," + checklist[i].data  + "] is incorrect");
                    return("FALSE");
                }
            }
            counter2++;
        }

        return("TRUE");

    }
}

class Link{
    public String data;
    public Link next;
    public Link previous;

    public Link(String input){
        data=input;
        next=null;
        previous=null;
    }
    public String print()
    {
        String printStr = "";
        if (next == null)
            printStr = printStr + " ->(null)";

        if (previous == null)
            printStr = printStr + " <-(null)";

        printStr = printStr + "[" + data + "]";

        return printStr;
    }

}

class LinkedList {
    public Link first;
    public Link last;

    public LinkedList( ){
        first=null;
        last=null;
    }

    public boolean isEmpty( ){
        return (first==null);
    }

    public void insertHead(Link insert){
        if(isEmpty()){
            first=insert;
            last=insert;
        }else{
            first.previous=insert;
            insert.next=first;
            first=insert;
        }
    }
}











