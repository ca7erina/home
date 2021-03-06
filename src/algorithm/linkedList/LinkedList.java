package algorithm.linkedList;

/**
* Created by chenxiaoxue on 11/20/15.
*/
public class LinkedList {
    public Link first;
    public LinkedList(){
        first = null;
    }
    public boolean isEmpty(){
        return(first == null);
    }

    public void insertHead(int number){
        Link newLink = new Link(number);
        newLink.next=first;
        first = newLink;
    }

    public void insertHead(Link insert){ //insert before first
        if(isEmpty()){
            first=insert;
        }else{
            insert.next=first;
            first=insert; //update first;
        }
    }

    public Link deleteHead(){
        Link temp = first;
        first=first.next;
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

    //test
    public Link get3rdLast() {
        Link current = first;
        while (current.next!=null&&current.next.next!=null){
            if(current.next.next.next==null){
                return current;
            }
        }
        return null;
    }
}
class Link{
    public int data;
    public Link next;
    public Link previous;//doubly linked

    public Link(int data){
        this.data = data;// "next" is automatically set to null;
    }


    public void displayLink(){
        System.out.print(this.data + " ");
    }

}

class LinkedListTest{

    public static void main(String[] args){


        LinkedList ll = new LinkedList();
        ll.insertHead(1);
        ll.insertHead(3);
        ll.insertHead(5);
        ll.deleteHead();
       ll.insertHead(7);
        ll.display();
        System.out.println(ll.get3rdLast().data);



    }
}
