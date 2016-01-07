package algorithm;

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

	/**
	 * tested ok
	 */
//	    public boolean insert(long item){
//	        if(isFull()) return false;
//	        if(nItems==0){
//	        	queArray[front]=item;
//	        }if(nItems+front>=maxSize&&item>queArray[nItems+front-1]){ //array is full,but removed at the begainning, 0,0,60, 80 and then insert 90
//	        	int j = front;
//	        	while(j<=(nItems+front-1)){
//	        		queArray[j-1]=queArray[j];
//	        		j++;
//	        	}
//	        	front--;
//	        	queArray[j-1]=item;
//
//	        }else{
//	        	int j=nItems;
//	        	while(j>0&&queArray[j-1]>item){
//	        		queArray[j]=queArray[j-1];
//	        		j--;
//	        	}
//	        	queArray[j]=item;
//	        }
//
//	        nItems++;
//	        return true;
//	    }

	public void insert(long item) {            // insert item
		if(nItems==0){                         // if no items,
			queArray[0] = item;             // insert at 0
		}else{                                 // if some items,
			int j = nItems;         // start at end
			while(j > 0 && queArray[j-1] > item){   // while new item larger
				queArray[j] = queArray[j-1];    // shift upward
				j--;                 // decrement j
			}
			queArray[j] = item;                // insert it
		}
		nItems++;                  // increase items
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

		//every time remove an front element, all element move forward by one position; front always ==0;
//		public long remove(){
//			if(isEmpty()){
//	            return Long.parseLong(null);
//	        }
//			long temp = queArray[0];
//
//			int j = 0;
//			while(j<nItems-1){
//				queArray[j]=queArray[j+1];
//				j++;
//			}
//			queArray[j]=0;
//			nItems--;
//			return temp;
//		}




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
		pq.print();
	    pq.remove();
		pq.print();

	    pq.insert(90);
	    pq.print();
	    while(!pq.isEmpty()){
	    	System.out.println(pq.remove());
	    }
	    
	    pq.print();
	}

}
