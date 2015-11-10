package algorithm;

import java.util.Arrays;

/**
 * Created by chenxiaoxue on 11/06/15.
 */
public class Sort {


	public static void main(String[] args) {

		int a[] = new int[15];
		initalArray(a);
		
		
		// bubble sort
		for (int i = a.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (a[j] > a[j + 1]) {
					swap(a, j, j + 1);
				}
			}
		}
		
		System.out.println("after bubble sort:" + Arrays.toString(a));
		
		initalArray(a);
		
		//select sort;
		int min;
		for(int i=0;i<a.length;i++){
			min=i;
			for(int j=i+1;j<a.length-1;j++){
				if(a[j]<a[min]){
					min =j;
				}
				
			}
			swap(a,i,min);
		}
		System.out.println("after select sort:" + Arrays.toString(a));
		
		initalArray(a);
		//insert sort;
		for(int i=1;i<a.length-1;i++){
			int temp = a[i];
			int j=i;
			while(j>0&&a[j-1]>temp){
				a[j]=a[j-1];
				j--;
			}
			a[j]=temp;
		}
		
		System.out.println("after insertion sort:" + Arrays.toString(a));
	}

	public static void initalArray(int a[]) {
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 100);
		}
		System.out.println(Arrays.toString(a));
	}

	public static void swap(int array[], int i, int j) {
		int temp;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
