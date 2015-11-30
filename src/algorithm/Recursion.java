package algorithm;

/**
 * Created by chenxiaoxue on 11/30/15.
 */
public class Recursion {
    static int a[]={1,2,3,4,5,6,7,8,9,10,11,12,13,14};
    static int array[]={2,5,2,6,3,8,9,3};
    static int workSpace[];
    public static void main(String[] args) {

        System.out.println(recFind(2, 0, a.length - 1));
    }


    private static int recFind(long searchKey, int lowerBound, int upperBound){

        int middle;
        middle = (lowerBound + upperBound ) / 2;
        if(a[middle]==searchKey)
            return middle; // found it
        else if(lowerBound > upperBound)
            return -1; // can't find it
        else { // divide range
            if(a[middle] < searchKey) // it's in upper half
                return recFind(searchKey, middle+1, upperBound);
            else // it's in lower half
                return recFind(searchKey, lowerBound, middle-1);
        } // end else divide range

} // end recFind()



    public static void mergeSort(int left, int right){
        int mid = (left + right) /2; // computes midpoint
        if(left==right) //base case
            return;
        mergeSort(left, mid);
        mergeSort(mid+1, right);
        for(int i=left; i<=right; i++)
            workSpace[i]=array[i]; //copies entire array into workspace
        int i1=left;
        int i2=mid+1;
        for(int curr=left; curr<=right; curr++){ //merge workspace
            if(i1>mid){ //copies all remnants in
                array[curr]=workSpace[i2++];
            }else if(i2>right){ //copies all remnants in
                array[curr] = workSpace[i1++];
            }else if(workSpace[i1]>workSpace[i2]){
                array[curr]=workSpace[i1++]; //merge
            }else{
                array[curr]=workSpace[i2++]; //merge
            }
        }
    }

}
class TowersApp {

    static int nDisks = 3;
    public static void main(String[] args) {
        doTowers(nDisks, 'A', 'B', 'C');
    }
    public static void doTowers(int topN,
                                char src, char inter, char dest) {
        if(topN==1)
            System.out.println("Disk 1 from " + src + " to "+ dest);
        else {
            doTowers(topN-1, src, dest, inter); // src to inter
            System.out.println("Disk " + topN + // move bottom
                    " from " + src + " to "+ dest);
            doTowers(topN-1, inter, src, dest); // inter to dest
        }
    }
}