package algorithm;

/**
 * Created by chenxiaoxue on 11/10/15.
 */
public class QueueTest {
    public static void main(String args[]){
        Queue q = new Queue(10);
        System.out.println(q.insert(5));
        System.out.println(q.insert(3));
        System.out.println(q.remove());
        System.out.println(q.insert(7));
        System.out.println(q.remove());
        System.out.println(q.peekFront());
        System.out.println(q.remove());
        System.out.println(q.isEmpty());
//        System.out.println(q.remove()); // error
        System.out.println(q.insert(9));
        System.out.println(q.insert(7));
        System.out.println(q.size());
        System.out.println(q.insert(3));
        System.out.println(q.insert(5));
        System.out.println(q.remove());
    }



}
