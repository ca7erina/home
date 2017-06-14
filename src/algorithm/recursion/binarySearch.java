package algorithm.recursion;

/**
 * Created by chenxiaoxue on 12/2/15.
 */
public class binarySearch {
    static int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

    public static void main(String[] args) {

        System.out.println(recFind(2, 0, a.length - 1));
    }

    private static int recFind(long searchKey, int lowerBound, int upperBound) {

        int middle;
        middle = (lowerBound + upperBound) / 2;
        if(a[middle] == searchKey) {
            return middle; // found it
        }else if(lowerBound > upperBound) {
            return -1; // can't find it
        }else { // divide range
            if(a[middle] < searchKey) { // it's in upper half
                return recFind(searchKey, middle + 1, upperBound);
            }else { // it's in lower half
                return recFind(searchKey, lowerBound, middle - 1);
            }
        } // end else divide range

    } // end recFind()
}
