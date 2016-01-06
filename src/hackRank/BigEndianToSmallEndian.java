package hackRank;

import java.util.Scanner;

/**
 * the goal is to convert a 32 bit integer from little endian to big endian encoding
byte1->byte4
byte2->byte3
byte3->byte2
byte4->byte1

input : aninteger which represents n in 32bit little endian format eg. 3


ouput : an integer which represnts n in 32-bit bg endian format eg. 50331648
 *
 */
public class BigEndianToSmallEndian {
	
	public static void main(String args[]){
		Scanner myScanner = new Scanner(System.in);
        int num1 = myScanner.nextInt();
        int result =num1<<24; //get first8 bits; xxxxxxxx 0000000000 000000000 000000
        result=result|((((num1>>>8)<<24))>>>8);
        result=result|((((num1>>>16)<<24))>>>16);
        result=result|(num1>>>24);
        System.out.println(result);
	}

}
