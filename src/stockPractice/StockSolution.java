package stockPractice;


import java.io.*;
import java.util.Arrays;

/**

 *
 */
public class StockSolution {
    static String[] companyIndex;
    static String[] dateIndex;
    public static double array[][];
    public static double price[];
    public static double volatility[][];
    private static final double TOTAL = 38496*3+2953*10;//1000000.00
    private static int numcols;
    private static int numrows;
    private static double optimizedOneRoundPrice;

    public static void main(String[] args) {
        FileIO2 io = new FileIO2();
        String[] original = io.load("src" + File.separator + "stockPractice" + File.separator + "stockdata2.txt");
        numrows = original.length; // how many dates; 881
        numcols = original[0].split("\t").length; //how many companies 466
        array = new double[numrows][numcols];
        // get array[][] : all data stored in it;
        for(int i = 0; i < numrows; i++) {
            for(int j = 0; j < numcols; j++) {
                array[i][j] = Double.parseDouble(original[i].split("\t")[j]);
            }
        }

        //get volatility array
        volatility = new double[numcols][2];
        for(int i = 0; i < numcols; i++) {
            volatility[i][0] = i;//index
            volatility[i][1] = getVotality(array, i);
            //  System.out.println(i+" "+volatility[i]);
        }
        // sort by volatility
        sortVolativity(volatility); //sorted
        // print2DArray(volatility);

        //get companyname array
        String[] companyNameList = new String[numcols];
        String[] names = io.load("src" + File.separator + "stockPractice" + File.separator + "companyNames.txt");
        for(int i = 0; i < numcols; i++) {
            companyNameList[i] = names[0].split("\t")[i];
//            System.out.println(i+" "+companyNameList[i]);
        }

        //get price array lowest 319
        price = new double[numcols];
        String[] pricestr = io.load("src" + File.separator + "stockPractice" + File.separator + "stockprice.txt");
        for(int i = 0; i < numcols; i++) {
            price[i] = Double.parseDouble(pricestr[0].split("\t")[i]);
//            System.out.println(i+" "+price[i]);
        }


        //get the optimized stock options
        double[][] option = generateCandicateStockList();//option {index,volatility,price}
//
//        double total = TOTAL;
//        int n= (int)total/(int)optimizedOneRoundPrice;
//        if (n>0){
//            setprice();
//
//        }
//        double left = ?;
//


        //set the result
        int result[] = new int[numcols];
        double total = TOTAL;
        int index = 0;
        while(true) {
            total -= option[index][2];
            if(total == 0 ){
                result[(int) option[index][0]] = result[(int) option[index][0]] + 1;
                break;
            }
            if(total < 0) {
                total += option[index][2];//rollback
                index = 0;//total <0 set index to the smallest price
                total -= option[index][2];
                System.out.println(":" + total);
                System.out.println(":" + option[index][2]);
                if(total < 0) {// total smaller than the lowest price in the option array.
                    total = total + option[index][2];//rollback
                    System.out.println("total< lowest price, total :" + total + "break here");
                    break;
                } else {
                    result[(int) option[index][0]] = result[(int) option[index][0]] + 1;
                }
            } else {
                result[(int) option[index][0]] = result[(int) option[index][0]] + 1;
            }
            index++;
            if(index > (option.length - 1)) { // if index > 7 ,assign index 0, from beginning start another round
                index = 0;
            }
        }
        System.out.println("total left: " + total);
        System.out.println("overall votality: "+getOverAllVotality(result));
        printResult(result);

        //pick up any stock left money able to buy in the list
        while(total >= 319) { //319 is the lowest price of the stock
            double price1 = 500;
            for(int i = 0; i < volatility.length; i++) {
                price1 = total - price[(int) volatility[i][0]];
                if(price1 < 0) { //find the price can buy
                  //  System.out.println("price:"+price1+" i"+i);
                    continue;

                } else {
                    total = price1;
                    result[(int) volatility[i][0]] = result[(int) volatility[i][0]] + 1;
                 //   System.out.println("bought more:"+total);
                    break;// go back from beginning to pick the lowest volatility to buy
                }
            }
        }

        System.out.println("total left: " + total);
        System.out.println("overall votality: " + getOverAllVotality(result));
        printResult(result);
        System.out.println("result: " + Arrays.toString(result));




        //test
//        result = new int[numcols];
//        result[236] = 3;
//        result[140] = 3;
//        result[381] = 3;
//        result[278] = 3;
//        result[184] = 3;
//        result[439] = 7;
//        result[245] = 3;
//        result[87] = 3;
//        System.out.println(getOverAllVotality(result)); //buy cheapeast in the list more, lower the votality
//
//        result = new int[numcols];
//        result[236] = 3;
//        result[140] = 3;
//        result[381] = 6;
//        result[278] = 3;
//        result[184] = 3;
//        result[439] = 3;
//        result[245] = 3;
//        result[87] = 3;
//
//        System.out.println(getOverAllVotality(result));
        //after bought one round ,continue to buy is better than go inside round buy
    }

    


    public static void printResult(int result[]){
        System.out.println("Result is:");
        int index=0;
        for(int i:result){
            if(i>0){
                System.out.println("index:"+index+" , bought:"+i);
            }
            index++;
        }
        System.out.println();
    }

    public static double[][] generateCandicateStockList() {
        //get the list that keep the volatility the lowest, and sort by price.
        double total = TOTAL;
        int result[] = new int[numcols];
        double totalVolatility = volatility[0][1];
        boolean flag = true;
        double currentVolativity;
        double currentPrice;
        int record = 0;
        while(flag) {
            for(int i = 0; i < volatility.length; i++) {
                result[(int) volatility[i][0]] = 1;
                currentVolativity = getOverAllVotality(result);
                currentPrice = total - price[(int) volatility[i][0]];

                //debug
//                System.out.println("i" + i);
//                System.out.println("currentVolativity " + currentVolativity);
//                System.out.println("total " + total);
//                System.out.println("price " + price[(int) volatility[i][0]]);
//                System.out.println("currentPrice " + currentPrice);
//                System.out.println("");

                if(currentVolativity > totalVolatility) { // this stock make total volativity get up, stop here fisrtly
                    result[(int) volatility[i][0]] = 0;//rollback
                    i--;//rollback
                    record = i;
                    System.out.println("current volativity is up, stop here " + i);
                    System.out.println("total volatility:" + totalVolatility);
                    flag = false;
                    break;
                }

                if(currentPrice < 0) { //no more money to buy stocks

                    result[(int) volatility[i][0]] = 0; //rollback
                    System.out.println("no money buy next" + total);
                    continue; // go check next price see if can buy
                }



                if(currentPrice >= 0 && currentVolativity <= totalVolatility) { //add one more stock to result, volativity drop
                    result[(int) volatility[i][0]] = 1;
                    total = currentPrice;
                    totalVolatility = currentVolativity;
//                    System.out.println("total change to:" + total);
                }
            }

        }

//        System.out.println(total);
//        System.out.println(getOverAllVotality(result));
        double option[][] = new double[record + 1][3];//option index,volatility,price (record  index is 7, size is 8)
        double optionTotalPrice = 0.0;

        //generate optimized options
        for(int i = 0; i < option.length; i++) {
            option[i][0] = volatility[i][0];
            option[i][1] = volatility[i][1];
            option[i][2] = price[(int) volatility[i][0]];
            optionTotalPrice += option[i][2];
        }
        optimizedOneRoundPrice = optionTotalPrice;
        System.out.println("option one round price:" + optionTotalPrice);

        //sort option by price
        sortOptionsByPrice(option);
        print2DArray(option);
        return option;

    }


    public static void sortVolativity(double[][] volatility) {
        for(int i = volatility.length - 1; i > 0; i--) {// bubble sort
            for(int j = 0; j < i; j++) {
                if(volatility[j][1] > volatility[j + 1][1]) {
                    //swap
                    double[] temp = volatility[j];
                    volatility[j] = volatility[j + 1];
                    volatility[j + 1] = temp;
                }
            }
        }
    }

    public static void sortOptionsByPrice(double[][] option) {
        for(int i = option.length - 1; i > 0; i--) {// bubble sort
            for(int j = 0; j < i; j++) {
                if(option[j][2] > option[j + 1][2]) {
                    //swap
                    double[] temp = option[j];
                    option[j] = option[j + 1];
                    option[j + 1] = temp;
                }
            }
        }
    }


    public static double getAvg(double array[][], int companyindex) {
        double sum = 0.0;
        for(int j = array.length - 1; j >= 0; j--) {
            sum += array[j][companyindex];
        }
        return sum / (array.length - 1);
    }

    public static double getAvg(double overAllCol[]) { //for overAlllist
        double sum = 0.0;
        for(int j = overAllCol.length - 1; j >= 0; j--) {
            sum += overAllCol[j];
        }
        return sum / (overAllCol.length - 1);
    }

    public static double getVotality(double overAllCol[]) {
        double sum = 0.0;
        for(int j = overAllCol.length - 1; j >= 0; j--) {//(1,array.length-1)
            sum += Math.pow(overAllCol[j] - getAvg(overAllCol), 2);
        }

        double result = Math.sqrt(sum / (overAllCol.length - 1 - 1));
        return result;
    }

    public static double getVotality(double array[][], int companyindex) {
        double sum = 0.0;
        for(int j = array.length - 1; j >= 0; j--) {//(1,array.length-1)
            sum += Math.pow(array[j][companyindex] - getAvg(array, companyindex), 2);
        }

        double result = Math.sqrt(sum / (array.length - 1 - 1));
        return result;
    }


    /**
     * @param result
     */
    public static double getOverAllVotality(int[] result) {
        double overAlldailyChange[] = new double[881]; //number of dates 881
        for(int j = 0; j < overAlldailyChange.length; j++) {
            double fomularA = 0.0;
            double fomularB = 0.0;
            for(int i = 0; i < result.length; i++) {
                if(result[i] == 0) {
                    continue;
                }
                fomularA += price[i] * result[i] * array[j][i]; //i is company index; j is date
                fomularB += price[i] * result[i];
            }
            overAlldailyChange[j] = fomularA / fomularB; //j is date;//(A's price * A's dailyChange +B's price*B's dailyChange)/(A'price + B'sPrice)
//                System.out.println(j+" "+dailyChange[j]);
        }

        return getVotality(overAlldailyChange);

    }


    public static void print2DArray(double[][] a) {
        for(int i = 0; i < a.length; i++) {//last index no  no data
            System.out.print(Arrays.toString(a[i]) + price[(int) a[i][0]]);
            System.out.println();
        }
    }


}


class FileIO2 {

    public String[] load(String file) {
        File aFile = new File(file);
        StringBuffer contents = new StringBuffer();
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(aFile));
            String line = null;
            int i = 0;
            while((line = input.readLine()) != null) {
                contents.append(line);
                i++;
                contents.append(System.getProperty("line.separator"));
            }
        } catch(FileNotFoundException ex) {
            System.out.println("Can't find the file - are you sure the file is in this location: " + file);
            ex.printStackTrace();
        } catch(IOException ex) {
            System.out.println("Input output exception while processing file");
            ex.printStackTrace();
        } finally {
            try {
                if(input != null) {
                    input.close();
                }
            } catch(IOException ex) {
                System.out.println("Input output exception while processing file");
                ex.printStackTrace();
            }
        }
        String[] array = contents.toString().split("\n");
        for(int i = 0; i < array.length; i++) {
            array[i] = array[i].trim();
        }
        return array;
    }


    public void save(String file, String[] array) throws FileNotFoundException, IOException {
        File aFile = new File(file);
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(aFile));
            for(int i = 0; i < array.length; i++) {
                output.write(array[i]);
                output.write(System.getProperty("line.separator"));
            }
        } finally {
            if(output != null) output.close();
        }
    }


}