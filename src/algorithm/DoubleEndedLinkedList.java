package algorithm;

/**
 * Created by chenxiaoxue on 11/20/15.
 */
public class DoubleEndedLinkedList {
    private Link first;
    private Link last;
    public DoubleEndedLinkedList(){
        first = null;
        last = null;
    }
    public boolean isEmpty(){
        return(first == null);
    }

    public void insertHead(int number){
        Link newLink = new Link(number);
        newLink.next=first;
        first = newLink;
    }

    public void insertLast(int number){
        Link newLink = new Link(number);
        Link current = first;
        while(current.next!=null){
            current = current.next;
        }
        current.next=newLink;
        last = newLink;
    }

    public Link deleteFirst(){
        Link temp = first;
        first=first.next;
        return temp;
    }

    public Link deleteLast(){
        Link temp = last;
        Link current = first;
        while(current.next!=last){
            current = current.next;
        }
        current.next=null;
        last = current;
        return temp;
    }

    public Link delete(int number){
        Link current  = first;
        Link previous  = first;

        while(current.data!=number){
            if(current.next==null){
                return null;// didn't find it
            }else{
                previous = current;
                current=current.next;
            }
        }
        //find it
        if(current == first){
            first = first.next;
        }else{
            previous.next = current.next;
        }

        return current;
    }

    public void display(){
        Link current = first;
        while(current!=null){
            current.displayLink();
            current = current.next;
        }

    }



}
class DoubleEndedLinkedListTest{

    public static void main(String[] args){

        DoubleEndedLinkedList  ll = new DoubleEndedLinkedList();
        ll.insertHead(3);
        ll.insertHead(1);
        ll.insertHead(4);
        ll.deleteFirst();
        ll.insertLast(6);
        ll.deleteLast();
        ll.insertLast(5);

        ll.display();
    }
}
