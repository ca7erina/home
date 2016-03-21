package cryptography.AliceBob;

/**
 * Alice public key(generater, primeNumber,g^x mod p);
 * x = private key  [0,p]
 *
 * Bob encryption:(g^y mod p, (m* g^y )mod p)
 *
 * Alice receive: (g^y, m*g^y)=(c1,c2)
 * m = c2/c1 = (1/c1 *c2) mod p
 * m = c1 ^(p-1-x) mod P
 *
 */
public class Solution {
    static int privateKey[] = new int[100];

    public static void main(String[] args) {
        //Alice’s public key is (24852977, 2744, 8414508).
        //Bob send her the cipher (15268076, 743675)
        // what is the private key x?
        long p = 24852977l;
        long g = 2744l;
        long publicNum = 8414508l;


        long c1 = 15268076l;
        long c2 = 743675l;

        getPrivate(p, g, publicNum);
        for(int i : privateKey) {
            if(privateKey[i] > 0) {
                System.out.println("msg: " + getMessage(c1, c2, p, privateKey[i]));
            }

        }

    }

    public static long getMessage(long c1, long c2, long primeNumber, long privateKey) {
        long c1Result = modPow(c1, primeNumber - 1 - privateKey, primeNumber);//c1^(p-1-x) mod p
        return (c1Result * c2) % primeNumber; //c1Result*c2 mod p
    }

    public static void getPrivate(long p, long g, long result) { //result =g^x mod p

        int index = 0;
        for(int x = 0; x <= p; x++) { //x[0,p]
            if(modPow(g, x, p) == result) {
                privateKey[index++] = x;
                System.out.println("x:" + x);
            }
        }

    }

    /**
     * raises a number to a power with the given modulus
     * when raising a number to a power, the number quickly becomes too large to handle
     * you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
     * however you want the algorithm to work quickly - having a multiplication loop would result in an O(n) algorithm!
     * the trick is to use recursion - keep breaking the problem down into smaller pieces and use the modMult method to join them back together
     *
     *
     * example 7^29 mod 11
     * modPow(7,29,11) = 8
     *
     * @param number  the base
     * @param power   the power number
     * @param modulus the modulus number
     * @return return the result of number^power mod modulus
     */
    public static long modPow(long number, long power, long modulus) {
        if(power == 0)
            return 1;
        else if(power % 2 == 0) {
            long halfpower = modPow(number, power / 2, modulus);
            return modMult(halfpower, halfpower, modulus);
        } else {
            long halfpower = modPow(number, power / 2, modulus);
            long firstbit = modMult(halfpower, halfpower, modulus);
            return modMult(firstbit, number, modulus);
        }
    }

    /**
     * multiplies the first number by the second number with the given modulus
     * a long can have a maximum of 19 digits. Therefore, if you're multiplying two ten digits numbers the usual way, things will go wrong
     * you need to multiply numbers in such a way that the result is consistently moduloed to keep it in the range
     * however you want the algorithm to work quickly - having an addition loop would result in an O(n) algorithm!
     * the trick is to use recursion - keep breaking down the multiplication into smaller pieces and mod each of the pieces individually
     *
     * @param first the first number
     * @param second the second second number
     * @param modulus the modulus number
     * @return result of first* second mod modulus
     */
    public static long modMult(long first, long second, long modulus) {

        if(second == 0)
            return 0;
        else if(second % 2 == 0) {
            long half = modMult(first, second / 2, modulus);
            return (half + half) % modulus;
        } else {
            long half = modMult(first, second / 2, modulus);
            return (half + half + first) % modulus;
        }
    }
}
