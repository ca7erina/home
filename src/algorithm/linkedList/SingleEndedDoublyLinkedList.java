package algorithm.linkedList;

/**
 * Created by chenxiaoxue on 1/10/16.
 */
public class SingleEndedDoublyLinkedList {
    private Link first;

    public SingleEndedDoublyLinkedList() {
        first = null;
    }

    public boolean isEmpty() {

        return (first == null);
    }

    public void insertHead(int number) {
        Link newLink = new Link(number);
        if (isEmpty()) {
            first = newLink;
        } else { // link list not empty
            first.previous = newLink;
            newLink.next = first;
            first = newLink;
        }
    }

    public Link deleteHead() {
            Link temp = first;
            if(first.next!=null){
                first = first.next;
                first.previous = null;
            }else{
                first = null; //last element : first.next= =null
            }


            return temp;

    }
    public Link getHead(){
        return first;
    }
    public void setHead(Link node){
         first=node;
    }

    public Link delete(int number) {
        if(isEmpty()){
            return null; //no elements cannot delete;
        }
        Link current = first;
        while (current!=null&&current.data != number) {
            current = current.next;
        }
        //didn't find
        if (current == null){
            return null;
        }
        // find it
        if (current == first) {
            //delete first
            first = first.next;
            first.previous = null;
        } else {
            current.previous.next = current.next;
            current.next.previous=current.previous;
        }
        return current;
    }

    public void display() {
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }
}

class SingleEndedDoublyLinkedListTest {

    public static void main(String[] args) {

        SingleEndedDoublyLinkedList ll = new SingleEndedDoublyLinkedList();
        ll.insertHead(1);
        ll.insertHead(3);
        ll.insertHead(5); //5 3 1
        ll.insertHead(7); //7 5 3 1
        ll.deleteHead(); //5 3 1
        ll.insertHead(10);// 10 5 3 1
        ll.delete(3); //10 5 1
        ll.display();
        ll.deleteHead();//5 1
        ll.deleteHead();//5
        ll.display();
        ll.deleteHead();
        ll.display();
    }
}