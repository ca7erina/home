package algorithm.linkedList;

/**
 * Created by chenxiaoxue on 1/7/16.
 */
public class SortedDoubleEndedDoublyLinkedList {

    private Link first;
    private Link last;

    public SortedDoubleEndedDoublyLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertOrdered(int data){ //inserts data in order
        Link current = first; // start at beginning
        while(current!=null && data > current.data) {
            current = current.next; // move to next link
        }
        Link newLink = new Link(data); // make new link
        if(current==first) { // if insertion at head
            insertHead(data);
        }else if(current==last){ //if insertion at tail
            insertLast(data);
        } else { // somewhere in middle
            newLink.previous = current.previous; // step 1
            current.previous.next = newLink; // step 2
            newLink.next = current; // step 3
            current.previous = newLink; // step 4
        }
    }

    private void insertHead(int number) {
        Link newLink = new Link(number);
        if (isEmpty()) {
            first = newLink;
            last = newLink;
        } else { // link list not empty
            first.previous = newLink;
            newLink.next = first;
            first = newLink;
        }

    }

    private void insertLast(int number) {
        Link newLink = new Link(number);

        if (isEmpty()) {
            first = newLink;
        } else {

            last.next = newLink;
            newLink.previous = last;
        }
        last = newLink;

    }

    public Link deleteFirst() {
        Link temp = first;
        first = first.next;
        first.previous = null;
        return temp;
    }


    public Link deleteLast() {
        Link temp = last;
        last.previous.next=null;
        last.previous=null;
        return temp;
    }

    public Link delete(int number) {
        Link current = first;
        Link previous = first;

        while (current.data != number) {
            if (current.next == null) {
                return null;// didn't find it
            } else {
                previous = current;
                current = current.next;
            }
        }
        // find it
        if (current == first) {
            first = first.next;
            first.previous = null;
        } else {
            previous.next = current.next;
            current.next.previous=previous;
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
class SortedDoubleEndedDoublyLinkedListTest {

    public static void main(String[] args) {

        SortedDoubleEndedDoublyLinkedList ll = new SortedDoubleEndedDoublyLinkedList();
        ll.insertOrdered(6);
        ll.insertOrdered(3);
        ll.insertOrdered(5);
        ll.deleteFirst();
        ll.insertOrdered(7);
        ll.insertOrdered(8);

        ll.insertOrdered(10);
        ll.insertOrdered(12);
        ll.insertOrdered(1);
        ll.deleteLast();
        ll.deleteFirst();
        ll.display();
    }
}