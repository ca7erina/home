import java.util.Arrays;

public class PriorityQueue {
	
	 private int maxSize;
	    private long[] queArray;
	    private int front;
	    private int nItems;

	    public PriorityQueue(int size){
	        maxSize = size;
	        queArray = new long[maxSize];
	        front =0;
	        nItems =0;
	    }

	    public boolean insert(long item){
	        if(isFull()) return false;
	        if(nItems==0){
	        	queArray[front]=item;
	        }if(nItems+front>=maxSize&&item>queArray[nItems+front-1]){ //array is full,but removed at the begainning, 0,0,60, 80 and then insert 90
	        	int j = front;
	        	while(j<=(nItems+front-1)){
	        		queArray[j-1]=queArray[j];
	        		j++;
	        	}
	        	front--;
	        	queArray[j-1]=item;
	        	
	        }else{
	        	int j=nItems;
	        	while(j>0&&queArray[j-1]>item){
	        		queArray[j]=queArray[j-1];
	        		j--;
	        	}
	        	queArray[j]=item;
	        }
	       
	        nItems++;
	        return true;
	    }

	    public long remove(){
	        if(isEmpty()){
	            return Long.parseLong(null);
	        }
	        long temp = queArray[front];
	        front++;
	        if(front ==maxSize){
	            front =0;
	        }
	        nItems--;
	        return temp;
	    }

	    public long peekFront(){
	        return queArray[front];
	    }

	    public boolean isFull(){
	        return nItems==maxSize;
	    }

	    public boolean isEmpty(){
	        return nItems==0;
	    }
	    public int size(){
	        return nItems;
	    }
	
	    public void print(){
	       System.out.println(Arrays.toString(queArray));
	    }
	
	

	
}

class PriorityQueueTest{
	public static void main(String[] args) {
		
		PriorityQueue pq = new PriorityQueue(4);
		pq.insert(60);
		pq.insert(20);
		pq.insert(80);
		pq.insert(40);
		pq.print();//20,40,60,80
		
	    pq.remove();
	    pq.remove();
		
	    pq.print();//0,0,60,80
	    pq.insert(90);
	    pq.print();
	    while(!pq.isEmpty()){
	    	System.out.println(pq.remove());
	    }
	    
	    pq.print();
	}

}
