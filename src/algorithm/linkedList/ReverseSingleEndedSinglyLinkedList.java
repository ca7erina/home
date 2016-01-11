package algorithm.linkedList;

/**
 * Created by chenxiaoxue on 1/11/16.
 *
 * Design an algorithm for reversing the contents of a single-ended singly-linked list.
 */
public class ReverseSingleEndedSinglyLinkedList {

    public static void main(String args[]) {
        LinkedList ll = new LinkedList();
        for(int i = 30; i >=0; i--) {
            ll.insertHead(i);
        }
        ll.display();
        ll=reverse(ll);
        ll.display();
    }

//doesnt work, to be continued:
//    public static void reverse(LinkedList ll) {
//
//        //empty or only has one link
//        if (ll.isEmpty()||ll.first.next==null){
//            return;
//        }
//
//        Link first =ll.first;
//        Link second = ll.first.next;
//        Link third = ll.first.next.next;
//
//        //only 2 link
//        if (third == null){
//            first.next=null;
//            ll.insertHead(second);
//        }
//
//        Link curr = second;
//        Link previous = first;
//        while(curr.next!=null){
//            Link temp = curr.next;
//
//            curr.next=previous;
//
//            //travese
//            curr = temp;
//
//
//        }
//
//
//    }


    public static LinkedList reverse(LinkedList ll){
        //empty or only has one link
        if (ll.isEmpty()||ll.first.next==null){
            return ll;
        }

        LinkedList newList = new LinkedList();

        Link curr = ll.first;
        while(curr.next!=null){
            Link temp = curr.next;
            curr.next = null;
            newList.insertHead(curr);

            //increment
            curr = temp;
        }
        newList.insertHead(curr);
        return newList;
    }

}
