package algorithm.linkedList;

/**
 * Write a Java method that takes in a reference to the head of a
 * single-ended doubly-linked list and deletes every third link,
 * starting with the deletion of the head. Provide comments which
 * explain how the algorithm works.
 */
public class DeleteEvery3rdLink {
    public static void main(String args[]) {
        SingleEndedDoublyLinkedList ll = new SingleEndedDoublyLinkedList();
        for(int i = 30; i >=0; i--) {
            ll.insertHead(i);
        }
        ll.display();
        deleteEvery3rd(ll);
        ll.display();
    }



    public static void deleteEvery3rd(SingleEndedDoublyLinkedList ll){

        //empty linked list
        if (ll.getHead()==null){
            return;
        }


        Link curr = ll.getHead();
        while(curr!=null&&curr.next!=null&&curr.next.next!=null&&curr.next.next.next!=null){
            curr = curr.next.next.next;
            Link temp = curr;
            //delete temp which is the last element
            if(curr.next==null){
                curr.previous.next=null;
                temp.previous=null;
            }else{
                //delete temp which is in the middle
                curr.previous.next=curr.next;
                curr.next.previous = curr.previous;

            }
        }

        //first step: delete head
       ll.deleteHead();

    }
}
