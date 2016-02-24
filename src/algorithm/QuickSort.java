package quickSort;
/*
 * O(n*logn) quicker than mergesort
 * 
 * )
 */

import java.util.Arrays;

public class QuickSort {
	static int[] theArray = {7,8,2,5,1,9,3,6};

	public static void main(String[] args) {
		recQuickSort(0,theArray.length-1);
		System.out.println(Arrays.toString(theArray));

	}
	
	public static void recQuickSort(int left, int right){
		if((right-left)<=0){
			return;
		}else{
			int pivot = theArray[right];
			System.out.println("pivot:"+pivot);
			System.out.println("left:"+theArray[left]);
			System.out.println("right:"+theArray[right]);
			int partition = partitionIt(left, right, pivot);
			recQuickSort(left,partition-1);
			recQuickSort(partition+1,right);
		}
	}
	
	public static int partitionIt(int left, int right, int pivot){
		int leftPtr = left-1;
		int rightPtr = right;
		
		while(true){
			while(theArray[++leftPtr]<pivot){//left==pivot if all the numbers before pivot is smaller
				
			}
			while(rightPtr>0&&theArray[--rightPtr]>pivot){}
			if(leftPtr >= rightPtr){
				break;
			}else{
				System.out.println("swap1:"+theArray[leftPtr]+" "+theArray[rightPtr]);
				swap(leftPtr, rightPtr);
				
			}
			
		}
		System.out.println("swap2:"+theArray[leftPtr]+" "+theArray[right]);
		swap(leftPtr,right);
		return leftPtr;
		
	}
	
	public static void swap(int leftPtr,int rightPtr){
		int temp=theArray[leftPtr];
		theArray[leftPtr] = theArray[rightPtr];
		theArray[rightPtr] = temp;
	}
	

}
