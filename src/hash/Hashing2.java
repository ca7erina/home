package cs211.lab7;


import java.io.*;

public class Hashing2 {
    static int size = 433099;//this is the size of the hash table - a prime number is best
    static String[] hashTable = new String[size];//create the hash table
    static String[] array = new String[216555]; //make sure your String array is big enough to hold all the data



    public static void main(String[] args) throws IOException {
    	  File testFile = new File( "dictionary.txt");     //this is where the file to be sorted is loaded from. enter the location where you have saved the file of words
    	  getContents(testFile);
    	  int min3=Integer.MAX_VALUE;
    	int min2=Integer.MAX_VALUE;
    	int min1=Integer.MAX_VALUE;
    	int index=0;
        for(int i = 433109;i>300000;i--){
        	size =i;
        	int c1=0;
        	int c2=0;
        	int c3=0;
        	
        	if(isPrime(i)){
        		System.out.print((index++)+" ");
        		   
        	        
        	         c1= fillLinearProbing();
        	         
        	        
        	       
//        	         c2= fillQuadraticProbing();
//        	
//        	         c3= fillDoubleHash();
//        	         
        	         
        	         if(c1<min1){
         	        	 min1 = c1; 
         	        	System.out.println("size: "+ size+" linner coll:"+c1);
//       	        	 System.out.println("size: "+ size+" Quadratic coll:"+c2);
//       	        	 System.out.println("size: "+ size+" Doublehash coll:"+c3);
         	         }
        	         
        	         
        	         
        	         
        	         if((c1<90000)&&(c2<90000)&&(c3<90000)){
        	        	 System.out.println("----------------------------------------------");
        	        	 System.out.println("size: "+ size+" linner coll:"+c1);
        	        	 System.out.println("size: "+ size+" Quadratic coll:"+c2);
        	        	 System.out.println("size: "+ size+" Doublehash coll:"+c3);
        	        	 
        	         }
        	         
//        	         System.out.println("size: "+ size+" linner coll:"+c1+" Quadratic coll:"+c2+" Doublehash coll:"+c3);
        	         
        	}
        	
        }
         
        
   
    }
    
    public static boolean isPrime(int num){
    	boolean isPrime = true;
    	for(int i=2;i<num;i++){
    		if(num%i==0){
    			isPrime = false;
    			break;
    		}
    	}
    	return isPrime;
    }

  

    /**
     * this is the primary hash key function - it should return a number which is a slot in the hash table
     * for words, a good strategy is to raise each character to successive powers of the alphabet size
     * assume that the alphabet is ASCII characters - a total of 256 possibilities
     * each successive character value should be raised to successive powers of 256
     * the whole thing is added together and then moduloed to create a hash table index
     *
     * @param word
     * @return
     */
    public static int getHashKey(String word) {
       long uniqueNumber = uniqueNumber(word);
        long result =  (uniqueNumber%size);
        return (int)result;

    }



    /**
     * Get uniqueNumeber: cats = 3*27^0+1*27^1+20*27^2 + 19*27^3 = 388,587
     *
     * @param word
     * @return
     */
    public static long uniqueNumber(String word){
        int slot = size;
        long uniqueNumber=0l;
        int power=0;
        for(char c:word.trim().toCharArray()){
            if(power>word.length()){
                System.err.println("error -------------------------------------------------------------------------");
            }
            uniqueNumber+=(c-96)*modPow(27,power,slot)*(c)^power;
            power++;
        }
        //System.out.println("uniqueNumber for the word "+ uniqueNumber);

        return uniqueNumber;
    }





    /**
     * this method should be different to the primary hash function
     * it should return a different number for words which generated the same primary hash key value
     * for example, you could just add up all of the letters in the word
     *
     * @param word
     * @return
     */
    public static int getDoubleHashKey(String word) {
        int max = 61;
        long uniquenumber =uniqueNumber(word);
        int secondHash = (int)(max-(uniquenumber%max));

        return secondHash;
    }


    public static int fillLinearProbing() {
        int totalcollisions = 0;
        //this variable stores the total number of collisions that have occurred for every word
        for(int i = 0; i < array.length; i++) {
            //go through all words
            int collisions = 0;
            int index = getHashKey(array[i]);
            //generate a hash key
            while(hashTable[index] != null) {
            //if that slot is already filled move onto the next slot and increment the collisions
                collisions++;
                index++;
                index = index % size;
            //make sure you don't go off the edge of the hash table
            }
            hashTable[index] = array[i];
//            if(i % 100 == 0) {
//                System.out.println(array[i] + " was placed in slot " + index + " of the hash table after " + collisions + " collisions");
//            }
            totalcollisions += collisions;
            //print out the information for the last 1,000 words only, otherwise it takes quite long and gets annoying
        }
      //  System.out.println("The total number of collisions was " + totalcollisions);
        hashTable = new String[size];//create the hash table
   
        return totalcollisions;
    }

    public static int fillQuadraticProbing() {
        int totalcollisions = 0;
        for(int i = 0; i < array.length; i++) {
            int collisions = 0;
            int index = getHashKey(array[i]);
            int queries = 1;
            while(hashTable[index] != null) {
                collisions++;
                index = index + (queries * queries);
                index = index % size;
                if(queries>46000){
                	System.out.println(queries+" "+size+" "+index);
                	
                }
                queries++;
            }
            hashTable[index] = array[i];
//            if(i % 100 == 0) {
//                System.out.println(array[i] + " was placed in slot " + index + " of the hash table after " + collisions + " collisions");
//            }
            totalcollisions += collisions;
        }
     
        //System.out.println("The total number of collisions was " + totalcollisions);
        hashTable = new String[size];//create the hash table
   
         return totalcollisions;
    }

    public static int fillDoubleHash() {
        int totalcollisions = 0;
        for(int i = 0; i < array.length; i++) {
            int collisions = 0;
            int index = getHashKey(array[i]);
            int doubleHash = getDoubleHashKey(array[i]);
            while(hashTable[index] != null) {
                collisions++;
                index = index + doubleHash;
                index = index % size;
            }
            hashTable[index] = array[i];
//            if(i % 100 == 0) {
//                System.out.println(array[i] + " was placed in slot " + index + " of the hash table after " + collisions + " collisions");
//            }
            totalcollisions += collisions;
        }
//        System.out.println("The total number of collisions was " + totalcollisions);
        hashTable = new String[size];//create the hash table
  
         return totalcollisions;
    }

    /**
     * raises a number to a power with the given modulus
     * when raising a number to a power, the number quickly becomes too large to handle
     * you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
     * however you want the algorithm to work quickly - having a multiplication loop would result in an O(n) algorithm!
     * the trick is to use recursion - keep breaking the problem down into smaller pieces and use the modMult method to join them back together
     *
     * example 7^29 mod 11
     * modPow(7,29,11) = 8
     *
     * @param number
     * @param power
     * @param modulus
     * @return
     */
    public static int modPow(int number, int power, int modulus) {

        if(power == 0)
            return 1;
        else if(power % 2 == 0) {
            int halfpower = modPow(number, power / 2, modulus);
            return modMult(halfpower, halfpower, modulus);
        } else {
            int halfpower = modPow(number, power / 2, modulus);
            int firstbit = modMult(halfpower, halfpower, modulus);
            return modMult(firstbit, number, modulus);
        }
    }

    /**
     * multiplies the first number by the second number with the given modulus
     * a long can have a maximum of 19 digits. Therefore, if you're multiplying two ten digits numbers the usual way, things will go wrong
     * you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
     * however you want the algorithm to work quickly - having an addition loop would result in an O(n) algorithm!
     * the trick is to use recursion - keep breaking down the multiplication into smaller pieces and mod each of the pieces individually
     * @param first
     * @param second
     * @param modulus
     * @return
     */
    public static int modMult(int first, int second, int modulus) {

        if(second == 0)
            return 0;
        else if(second % 2 == 0) {
            int half = modMult(first, second / 2, modulus);
            return (half + half) % modulus;
        } else {
            int half = modMult(first, second / 2, modulus);
            return (half + half + first) % modulus;
        }
    }


    /**
     * Fetch the entire contents of a text file, and return it in a String.
     * This style of implementation does not throw Exceptions to the caller.
     *
     * @param aFile is a file which already exists and can be read.
     */
    public static String getContents(File aFile) {
        //...checks on aFile are elided
        StringBuffer contents = new StringBuffer();

        //declared here only to make visible to finally clause
        BufferedReader input = null;
        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            input = new BufferedReader(new FileReader(aFile));
            String line = null; //not declared within while loop
      /*
      * readLine is a bit quirky :
      * it returns the content of a line MINUS the newline.
      * it returns null only for the END of the stream.
      * it returns an empty String if two newlines appear in a row.
      */
            int i = 0;
            while((line = input.readLine()) != null) {
                array[i] = line;
                i++;
                contents.append(System.getProperty("line.separator"));
            }
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if(input != null) {
                    //flush and close both "input" and its underlying FileReader
                    input.close();
                }
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        return contents.toString();
    }


    public static void setContents(File aFile)
            throws FileNotFoundException, IOException {

        //declared here only to make visible to finally clause; generic reference
        Writer output = null;
        try {
            //use buffering
            //FileWriter always assumes default encoding is OK!
            output = new BufferedWriter(new FileWriter(aFile));
            int i = 0;
            while(array[i] != null) {
                output.write(array[i]);
                output.write(System.getProperty("line.separator"));
                i++;
            }
        } finally {
            //flush and close both "output" and its underlying FileWriter
            if(output != null) output.close();
        }
    }
}
