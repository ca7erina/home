package algorithm;

import java.util.Arrays;

/**
 * Write a Java program that uses a Monte Carlo algorithm to
 * calculate the probability that next week’s lottery draw won’t have
 * any consecutive pairs of numbers. Six numbers are drawn from
 * 1 to 45. Provide comments which explain how the algorithm
 * works.
 *
 *
 * Monte Carlo methods (or Monte Carlo experiments) are a broad class of computational algorithms
 * that rely on repeated random sampling to obtain numerical results.
 * a Monte Carlo simulation uses repeated sampling to determine the properties of some phenomenon
 */
public class MonteCarloLottery {

    public static void main(String[] args) {
        int randomSize = 1000000000; // repeated random sample
        int consecutivePairs = 0;
        int[] tickets;
        for(int i = 0; i < randomSize; i++) {
            tickets = initializeTickets();
            if(includeConsecutiveNum(tickets)) {
                consecutivePairs++;
            }
        }
        System.out.println("probability lottery won't have any consecutive pairs: "+(1-((double)consecutivePairs/randomSize)));//(1-probability that will have consecutive paires of number);
//        System.out.println(1-((double)consecutiveResult/(double)sampleSize));


    }

    /**
     * check if there are consecutive numbers in the array.
     *
     * @param ticket as an int array;
     * @return result of whether there is consecutive num in this array.
     */
    public static boolean includeConsecutiveNum(int[] ticket) {
        //sort the ticket number asc
        Arrays.sort(ticket);

        // check if the number is consecutive with the next number in this ticket array.
        for(int i = 0; i < ticket.length - 1; i++) {
            if(ticket[i] - ticket[i + 1] == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Generate an int array that has 6 random number (which from 1 to 45) in it.
     *
     * @return generated array;
     */
    public static int[] initializeTickets() {
        int[] result = new int[6];
        for(int i = 0; i < result.length; i++) {
            result[i] = 1 + (int) (Math.random() * 45);
        }
        return result;
    }

}
