//package algorithm.linkedList;
//
//import java.util.Scanner;
//
///**
// * Created by chenxiaoxue on 1/11/16.
// */
//public class WhichLinkCauseLoop {
//
//
//    public static String findloop(LinkedList mylist) {
//        if(mylist.isEmpty()) {
//            return ("empty");
//        }
//
//        Link[] checklist = new Link[100];
//        int counter = 0;
//        Link forwards = mylist.first;
//        while(forwards.next != null) {
//            checklist[counter] = forwards;
//            Link currentLinkInList = forwards;
//            forwards = forwards.next;
//            for(int i = 0; i < counter; i++) {
//                if(forwards == checklist[i]) {
//                    return (currentLinkInList.data);
//                }
//            }
//            counter++;
//        }
//        return "OK";
//    }
//
//    public static void main(String args[]) throws Exception {
//        Scanner myscanner = new Scanner(System.in);
//        int num = Integer.parseInt(myscanner.nextLine());
//        Link[] array = new Link[num];
//        for(int i = 0; i < num; i++) {
//            array[i] = new Link(myscanner.nextLine());
//        }
//        while(myscanner.hasNext()) {
//            int select = myscanner.nextInt();
//            int next = myscanner.nextInt();
//            if(next != -1) {
//                array[select].next = array[next];
//            }
//        }
//        LinkedList mylist = new LinkedList();
//        if(num > 0) {
//            mylist.first = array[0];
//        }
//        System.out.println(findloop(mylist));
//    }
//
//
//    /**
//     * same findloop but with a lot comments;
//     * @param mylist
//     * @return
//     */
////    public static String findloop(LinkedList mylist){
////        if(mylist.isEmpty()){
////            return("empty");
////        }
////        // Create an array to essentially store our current list so we can keep checking it
////
////        Link[] checklist = new Link[100];
////
////        int counter=0;
////        // This is essentially our 'counter' to move up and down the List
////
////        Link forwards = mylist.first;
////
////        // Stay in the while loop as long as we are not at the end of the list
////
////        while(forwards.next!=null){
////            // store the current item in the list.
////            checklist[counter]=forwards;
////
////            Link currentLinkInList =forwards;
////            // get the data from the current item
////
////            //move to the next item in the list.
////            forwards=forwards.next;
////            // we need to check back over the rest of the list to see if a loop is introduced
////            for(int i=0;i<counter;i++){
////                // if we look back at the rest of the list in checklist
////                // and find that forwards is equal to it.
////                // Then we have found the problem. This is a loop
////
////                if(forwards==checklist[i]){
////                    // so it's actually the current item which is causing the issue. Remember
////                    // it's a Link object - so use the .data property to access it's value
////                    // Use the return statement here to exit the while loop and the method.
////                    return(currentLinkInList.data);
////                }
////            } // end for loop
////            counter++; // increment the counter for the checkList
////        }
////        return "OK";
////    }
//
//}
//
//class Link {
//    public String data;
//    public Link next;
//
//    public Link(String input) {
//        data = input;
//        next = null;
//    }
//}
//
//class LinkedList {
//    public Link first;
//
//    public LinkedList() {
//        first = null;
//    }
//
//    public boolean isEmpty() {
//        return (first == null);
//    }
//
//    public void insertHead(Link insert) {
//        if(isEmpty()) {
//            first = insert;
//        } else {
//            insert.next = first;
//            first = insert;
//        }
//    }
//}
