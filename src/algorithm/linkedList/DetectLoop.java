package algorithm.linkedList;

/**
 * Created by chenxiaoxue on 1/11/16.
 */
public class DetectLoop {


    public static String hasLoop(LinkedList myList){
        Link slow = myList.first;
        Link fast = myList.first;

        if(myList.first!=null){
            while(slow.next !=null&&fast.next!=null&&fast.next.next!=null ){
                slow = slow.next;
                fast = fast.next.next;
                if(slow == fast){
                    return "TRUE";
                }
            }
        }else{
            return "isEmpty";
        }

        return "FALSE";
    }


    public static int looplenth(LinkedList myList){
        int loopLength =0;
        Link slow = myList.first;
        Link fast = myList.first;

        if(myList.first!=null){
            while(slow.next !=null&&fast.next!=null&&fast.next.next!=null ){
                slow = slow.next;
                fast = fast.next.next;
                if(slow == fast){
                    Link temp = slow;
                    while(true){
                        temp=temp.next;
                        loopLength++;
                        if(temp==fast){
                            break;
                        }
                    }
                }
            }
        }else{
            return 0;
        }

        return loopLength;
    }
}
