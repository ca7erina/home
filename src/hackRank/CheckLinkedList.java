package hackRank;


import java.util.Scanner;

/**
 * is the linked list properly linked
 *
 * check the double-ended doubly-linked list to see if it is properly linked up.
 *
 * input format:
 * no input
 *
 * output format:
 * output "TRUE" if the linked list is properly connected and "FALSE" otherwise. If the linked list is empty output "empty"
 *
 * constraints:
 * 0<= links<=100
 *
 *
 * build your own testcase
 * fist line is the number of links n
 * the next n lines are the strings held in each link
 * the next lines give the connections between the links. Each of these links consists of 3 numbers separated by a space. The first is which number link you are talking about
 *  the second is the previous link it points to and third is the next link it points to. A -1 is used for null.
 *
 *  4
 *  Data1
 *  Data2
 *  Data3
 *  Data4
 *  0 -1 1
 *  1 0 2
 *  2 1 3
 *  3 2 -1
 *
 *
 */
public class CheckLinkedList {

    public static void main(String args[]) {
        Scanner myscanner = new Scanner(System.in);
        int num = Integer. parseInt(myscanner.nextLine());
        Link[] array = new Link[num];

        for(int i =0; i< num;i++){
            array[i] = new Link(myscanner.nextLine());
        }

        while(myscanner.hasNext()){
            int select = myscanner.nextInt();
            int previous = myscanner.nextInt();
            int next = myscanner.nextInt();
            if(previous!=-1){
                array[select].previous = array[previous];
            }
            if(next!=-1){
                array[select].next = array[next];
            }
        }

        LinkedList2 mylist = new LinkedList2();
        if(num>0){
            mylist.first = array[0];
            mylist.last = array[num-1];
        }
        System.out.println(check(mylist));
    }

    public static String check(LinkedList2 mylist){

        if(mylist.isEmpty()){
            return "empty";
        }

        //   Link slow= mylist.first;
        //  Link fast = mylist.first;

        //      while((slow.next!=null)&&(fast.next.next!=null)){
        //         slow = slow.next;
        //         fast = fast.next.next;
        //         if(slow == fast){
        //             return "FALSE";
        //         }
        //     }
        // }




        Link forwards = mylist.first;
        int i =0;
        while(forwards.next!=null){
            forwards = forwards.next;
            if(i>99){
                return "FALSE";
            }
            i++;
        }
        Link backwards = mylist.last;
        int j =0;
        while (backwards.previous!=null){
            backwards = backwards.previous;
            if(j>99){
                return "FALSE";
            }
            j++;
        }

        if((forwards == mylist.last)&&(backwards == mylist.first)&&(i==j)){
            return "TRUE";
        }else{
            return "FALSE";
        }



    }


}

class Link{
    public String data;
    public Link next;
    public Link previous;

    public Link(String data){
        this.data = data;// "next" is automatically set to null;
        next = null;
        previous = null;
    }


}
 class LinkedList2 {
    public Link first;
    public Link last;

    public LinkedList2() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertHead(Link insert) {

        if(isEmpty()) {
            first = insert;
            last = insert;
        } else {
            first.previous = insert;
            insert.next = first;
            first = insert;

        }
    }
}