package algorithm.linkedList;

/**
* Created by chenxiaoxue on 11/23/15.
*/
public class DoubleEndedDoubleLinkedList {

   	private Link first;
	private Link last;

	public DoubleEndedDoubleLinkedList() {
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public void insertHead(int number) {
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

	public void insertLast(int number) {
		Link newLink = new Link(number);
		if (isEmpty()) {
			first = newLink;
			last = newLink;
		} else {
			last.next = newLink;
			newLink.previous = last;
			last = newLink;
		}


	}

	public Link deleteFirst() {
		Link temp = first;
		if(first.next!=null){
			first = first.next;
			first.previous = null;
		}else{
			first = null;//last element : first.next= =null
		}
		return temp;
	}


	public Link deleteLast() {
		Link temp = last;
		Link newlast = last.previous;
		last.previous.next=null;
		last.previous=null;
		last = newlast;
		return temp;
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
		} else if(current == last){
			//delete last
			last.previous.next=null;
			last.previous=null;
			last = current.previous;
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

class DoubleEndedDoubledLinkedListTest {

	public static void main(String[] args) {

        DoubleEndedDoubleLinkedList ll = new DoubleEndedDoubleLinkedList();
		ll.insertHead(1);
		ll.insertHead(3);
		ll.insertHead(5); //5 3 1
		ll.deleteFirst(); // 3 1
		ll.insertHead(7); //7 3 1
		ll.insertLast(8); // 7 3 1 8

		ll.insertHead(10);// 10 7 3 1 8
		ll.insertLast(12); // 10 7 3 1 8 12
		ll.insertHead(1);// 1 10 7 3 1 8 12
		ll.deleteLast(); // 1 10 7 3 1 8
		ll.deleteFirst(); // 10 7 3 1 8
		ll.display();
		ll.delete(7);
		ll.display();
	}
}
