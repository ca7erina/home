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
		if (isEmpty()) {
			first = newLink;
			last = newLink;
		} else {
			newLink.next = first;
			first = newLink;
		}
    }

    public void insertLast(int number){
      Link newLink = new Link(number);
		if (isEmpty()) {
			first = newLink;
		} else {
			
			last.next = newLink;
		}
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
        System.out.println();

    }

}
class DoubleEndedLinkedListTest{

    public static void main(String[] args){

        DoubleEndedLinkedList  ll = new DoubleEndedLinkedList();
        ll.insertHead(3);
        ll.insertHead(1);
        ll.insertHead(4);
        ll.display();
        ll.deleteFirst();
        ll.display();
        ll.insertLast(6);
        ll.display();
        ll.deleteLast();
        ll.display();
        ll.insertLast(5);
        ll.display();
    }
}
