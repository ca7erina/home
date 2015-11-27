package algorithm;

/**
 * Created by chenxiaoxue on 11/23/15.
 */
public class DoubleEndedDoubleLinkedList {

   	private Link first;
	private Link last;

	public DoubledEndedDoublyLinkedList() {
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
			first.previous = newLink; // newLink <-- old first
			newLink.next = first;
			first = newLink;
		}

	}

	public void insertLast(int number) {
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
		Link current = first;
		while (current.next != last) {
			current = current.next;
		}
		current.next = null;
		temp.previous=null;
		last = current;
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

class DoubleEndedDoubledLinkedListTest {

	public static void main(String[] args) {

		DoubledEndedDoublyLinkedList ll = new DoubledEndedDoublyLinkedList();
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
	}
}
