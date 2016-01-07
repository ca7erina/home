package algorithm;

/**
 * Created by chenxiaoxue on 1/7/16.
 */
public class OrderedLinkedList {
    public Link first;

    public OrderedLinkedList() {
        first = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }


    public void insertOrdered(Link newlink) { // insert (in order)
        if(isEmpty()) {
            first = newlink;
        } else {
            if(newlink.data <= first.data) {
                //insert head;
                newlink.next = first;
                first = newlink;
                return;
            }

            Link previous = null;
            Link current = first;
            while(current != null && newlink.data > current.data) {
                previous = current;
                current = current.next;
            }
            // current ==null, new link bigger than all links, so insert at the last: 1 2 3 newlink
            // or  newlink < current now, need to insert between previous and current : 1 4 newlink 9 10
            previous.next = newlink;
            newlink.next = current;

        }

    }


    public Link deleteHead() {
        Link temp = first;
        first = first.next;
        return temp;
    }

    public Link delete(int number) {
        Link current = first;
        Link previous = first;

        while(current.data != number) {
            if(current.next == null) {
                return null;// didn't find it
            } else {
                previous = current;
                current = current.next;
            }
        }
        //find it
        if(current == first) {
            first = first.next;
        } else {
            previous.next = current.next;
        }

        return current;
    }

    public void display() {
        Link current = first;
        while(current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }
}

class OrderedLinkedListTest {

    public static void main(String[] args) {


        OrderedLinkedList ll = new OrderedLinkedList();

        for(int i = 0; i < 10; i++) {
            int num = (int) (Math.random() * 100);
            ll.insertOrdered(new Link(num));//0-99
        }

//        ll.insertOrdered(new Link(9));
//        ll.display();
//        ll.insertOrdered(new Link(3));
//        ll.display();
//        ll.insertOrdered(new Link(1));
//        ll.deleteHead();
//        ll.insertOrdered(new Link(4));
        ll.display();


    }
}