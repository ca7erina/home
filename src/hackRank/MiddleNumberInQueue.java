package hackRank;
import java.util.Scanner;
/**
 * middle number in the queue
 * @author hdit1505
 *
 */
public class MiddleNumberInQueue {

	public static void main(String[] args) {
		  Scanner myScanner = new Scanner(System.in);
	        Queue q = new Queue(20);
	        while(myScanner.hasNextLine()){
	            String cmd = myScanner.nextLine().trim();
	            String[] cmds = cmd.split(" ");
	            
	            
	            
	            if (cmds[0].equalsIgnoreCase("insert")){
	                q.insert(cmds[1]);
	            }else{
	                if (q.size()!=0){
	                    q.remove();
	                }
	                
	            }
	        }
	            int size = q.size();
	            if(size==0){
	                System.out.println("empty");
	                return;
	            }
	           // System.out.println(size);
	            
	            int target=-1;
	            if(size%2==1){
	                target = size/2;
	            }else{
	                target = (size/2)-1;
	            }

	          //  System.out.println(target);

	            int j=0;
	            while(!q.isEmpty()){
	                if(j==target){
	                System.out.println(q.remove());
	                }else{
	                    q.remove();
	                }
	                j++;
	            }

	}

}
class Queue{
    
    private int maxSize;
    private String[] queArray;
    private int front;
    private int rear;
    private int nItems;
    
    public Queue(int s){
        maxSize = s;
        queArray = new String[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }
    
    public boolean insert(String item){ 
        if(!isFull()){
            rear++;
            queArray[rear]=item;
            nItems++;
            return true;
        }else{
            return false;
        }
    }
      
    public String remove(){
        String temp = queArray[front];
        front++;
        if(front == maxSize)
        front = 0;
        nItems--;
        return temp;
    } 

    public boolean isEmpty(){ 
        return (nItems==0);
    }
        
    public boolean isFull(){
        return (nItems==maxSize);
    }
        
    public int size(){ 
        return nItems;
    } 
}
